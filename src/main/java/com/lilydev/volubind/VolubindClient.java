package com.lilydev.volubind;

import com.lilydev.volubind.config.ModConfigs;
import com.lilydev.volubind.config.VolubindClothConfig;
import net.fabricmc.api.ClientModInitializer;
import net.fabricmc.fabric.api.client.event.lifecycle.v1.ClientTickEvents;
import net.fabricmc.fabric.api.client.keybinding.v1.KeyBindingHelper;
import net.minecraft.client.MinecraftClient;
import net.minecraft.client.option.KeyBinding;
import net.minecraft.client.util.InputUtil;
import net.minecraft.sound.SoundCategory;
import net.minecraft.text.Text;
import org.lwjgl.glfw.GLFW;

import java.util.concurrent.atomic.AtomicBoolean;

public class VolubindClient implements ClientModInitializer {




    private static final KeyBinding configMenuKeybind = KeyBindingHelper.registerKeyBinding(new KeyBinding(
            "key.volubind.config",
            InputUtil.Type.KEYSYM,
            GLFW.GLFW_KEY_BACKSLASH,
            "key.category.volubind.volubind"
    ));
    private static final KeyBinding toggleMasterVolume = KeyBindingHelper.registerKeyBinding(new KeyBinding(
            "key.volubind.masterToggle",
            InputUtil.Type.KEYSYM,
            GLFW.GLFW_KEY_UNKNOWN,
            "key.category.volubind.volubind"
    ));
    private static final KeyBinding toggleMusicVolume = KeyBindingHelper.registerKeyBinding(new KeyBinding(
            "key.volubind.musicToggle",
            InputUtil.Type.KEYSYM,
            GLFW.GLFW_KEY_UNKNOWN,
            "key.category.volubind.volubind"
    ));
    private static final KeyBinding toggleRecordsVolume = KeyBindingHelper.registerKeyBinding(new KeyBinding(
            "key.volubind.recordsToggle",
            InputUtil.Type.KEYSYM,
            GLFW.GLFW_KEY_UNKNOWN,
            "key.category.volubind.volubind"
    ));
    private static final KeyBinding toggleWeatherVolume = KeyBindingHelper.registerKeyBinding(new KeyBinding(
            "key.volubind.weatherToggle",
            InputUtil.Type.KEYSYM,
            GLFW.GLFW_KEY_UNKNOWN,
            "key.category.volubind.volubind"
    ));
    private static final KeyBinding toggleBlockVolume = KeyBindingHelper.registerKeyBinding(new KeyBinding(
            "key.volubind.blockToggle",
            InputUtil.Type.KEYSYM,
            GLFW.GLFW_KEY_UNKNOWN,
            "key.category.volubind.volubind"
    ));
    private static final KeyBinding toggleHostileVolume = KeyBindingHelper.registerKeyBinding(new KeyBinding(
            "key.volubind.hostileToggle",
            InputUtil.Type.KEYSYM,
            GLFW.GLFW_KEY_UNKNOWN,
            "key.category.volubind.volubind"
    ));
    private static final KeyBinding toggleNeutralVolume = KeyBindingHelper.registerKeyBinding(new KeyBinding(
            "key.volubind.neutralToggle",
            InputUtil.Type.KEYSYM,
            GLFW.GLFW_KEY_UNKNOWN,
            "key.category.volubind.volubind"
    ));
    private static final KeyBinding togglePlayersVolume = KeyBindingHelper.registerKeyBinding(new KeyBinding(
            "key.volubind.playersToggle",
            InputUtil.Type.KEYSYM,
            GLFW.GLFW_KEY_UNKNOWN,
            "key.category.volubind.volubind"
    ));
    private static final KeyBinding toggleAmbientVolume = KeyBindingHelper.registerKeyBinding(new KeyBinding(
            "key.volubind.ambientToggle",
            InputUtil.Type.KEYSYM,
            GLFW.GLFW_KEY_UNKNOWN,
            "key.category.volubind.volubind"
    ));
    private static final KeyBinding toggleVoiceVolume = KeyBindingHelper.registerKeyBinding(new KeyBinding(
            "key.volubind.voiceToggle",
            InputUtil.Type.KEYSYM,
            GLFW.GLFW_KEY_UNKNOWN,
            "key.category.volubind.volubind"
    ));

//    public static void toggleVolume(MinecraftClient client, SoundCategory soundCategory) {
//        switch (soundCategory.name()) {
//            case "MASTER": {
//
//            }
//            case "MUSIC": {
//
//            }
//            case "RECORDS": {
//
//            }
//            case "WEATHER": {
//
//            }
//            case "BLOCK": {
//
//            }
//            case "HOSTILE": {
//
//            }
//            case "NEUTRAL": {
//
//            }
//            case "PLAYERS": {
//
//            }
//            case "AMBIENT": {
//
//            }
//            case "VOICE": {
//
//            }
//        }
//    }

    private static void setNormalVolumeConfigOnInit() {
        MinecraftClient client = MinecraftClient.getInstance();

        ModConfigs.MASTER_VOL_NORMAL   = (int) (client.options.getSoundVolume(SoundCategory.MASTER)  * 100);
        ModConfigs.MUSIC_VOL_NORMAL    = (int) (client.options.getSoundVolume(SoundCategory.MUSIC)   * 100);
        ModConfigs.RECORDS_VOL_NORMAL  = (int) (client.options.getSoundVolume(SoundCategory.RECORDS) * 100);
        ModConfigs.WEATHER_VOL_NORMAL  = (int) (client.options.getSoundVolume(SoundCategory.WEATHER) * 100);
        ModConfigs.BLOCK_VOL_NORMAL    = (int) (client.options.getSoundVolume(SoundCategory.BLOCKS)  * 100);
        ModConfigs.HOSTILE_VOL_NORMAL  = (int) (client.options.getSoundVolume(SoundCategory.HOSTILE) * 100);
        ModConfigs.NEUTRAL_VOL_NORMAL  = (int) (client.options.getSoundVolume(SoundCategory.NEUTRAL) * 100);
        ModConfigs.PLAYERS_VOL_NORMAL  = (int) (client.options.getSoundVolume(SoundCategory.PLAYERS) * 100);
        ModConfigs.AMBIENT_VOL_NORMAL  = (int) (client.options.getSoundVolume(SoundCategory.AMBIENT) * 100);
        ModConfigs.VOICE_VOL_NORMAL    = (int) (client.options.getSoundVolume(SoundCategory.VOICE)   * 100);
        ModConfigs.saveConfigs();

        Volubind.LOGGER.info("Normal volume set on client initialization!");
    }

    @Override
    public void onInitializeClient() {

        AtomicBoolean normalVolumeSet = new AtomicBoolean(false);


        ModConfigs.registerConfigs();



        ClientTickEvents.END_CLIENT_TICK.register(client -> {
            if (client.options != null && !normalVolumeSet.get()) {
                setNormalVolumeConfigOnInit();
                normalVolumeSet.set(true);
            }
            while (configMenuKeybind.wasPressed()) {
                client.setScreen(new VolubindClothConfig().create(client.currentScreen));
            }

            // Toggle volume binds.
            while (toggleMasterVolume.wasPressed()) {
                SoundCategory soundCategory = SoundCategory.MASTER;
                ModConfigs.saveConfigs();
                if (ModConfigs.isMasterToggled) {
                    client.options.setSoundVolume(soundCategory, (float)(ModConfigs.MASTER_VOL_NORMAL / 100));
                    client.inGameHud.getChatHud().addMessage(Text.literal("Untoggled Master volume!"));
                } else {
                    client.options.setSoundVolume(soundCategory, (float)(ModConfigs.MASTER_VOL_TOGGLED * 100));
                    client.inGameHud.getChatHud().addMessage(Text.literal("Toggled Master volume!"));
                }
                ModConfigs.isMasterToggled = !ModConfigs.isMasterToggled;
                ModConfigs.saveConfigs();
            }
            while (toggleMusicVolume.wasPressed()) {
                SoundCategory soundCategory = SoundCategory.MUSIC;
                ModConfigs.saveConfigs();
                if (ModConfigs.isMusicToggled) {
                    client.options.setSoundVolume(soundCategory, (float)(ModConfigs.MUSIC_VOL_NORMAL / 100));
                    client.inGameHud.getChatHud().addMessage(Text.literal("Untoggled Music volume!"));
                } else {
                    client.options.setSoundVolume(soundCategory, (float)(ModConfigs.MUSIC_VOL_TOGGLED * 100));
                    client.inGameHud.getChatHud().addMessage(Text.literal("Toggled Music volume!"));
                }
                ModConfigs.isMusicToggled = !ModConfigs.isMusicToggled;
                ModConfigs.saveConfigs();
            }
            while (toggleRecordsVolume.wasPressed()) {
                SoundCategory soundCategory = SoundCategory.RECORDS;
                ModConfigs.saveConfigs();
                if (ModConfigs.isRecordsToggled) {
                    client.options.setSoundVolume(soundCategory, (float)(ModConfigs.RECORDS_VOL_NORMAL / 100));
                    client.inGameHud.getChatHud().addMessage(Text.literal("Untoggled Jukebox/Note Block volume!"));
                } else {
                    client.options.setSoundVolume(soundCategory, (float)(ModConfigs.RECORDS_VOL_TOGGLED * 100));
                    client.inGameHud.getChatHud().addMessage(Text.literal("Toggled Jukebox/Note Block volume!"));
                }
                ModConfigs.isRecordsToggled = !ModConfigs.isRecordsToggled;
                ModConfigs.saveConfigs();
            }
            while (toggleWeatherVolume.wasPressed()) {
                SoundCategory soundCategory = SoundCategory.WEATHER;
                ModConfigs.saveConfigs();
                if (ModConfigs.isWeatherToggled) {
                    client.options.setSoundVolume(soundCategory, (float)(ModConfigs.WEATHER_VOL_NORMAL / 100));
                    client.inGameHud.getChatHud().addMessage(Text.literal("Untoggled Weather volume!"));
                } else {
                    client.options.setSoundVolume(soundCategory, (float)(ModConfigs.WEATHER_VOL_TOGGLED * 100));
                    client.inGameHud.getChatHud().addMessage(Text.literal("Toggled Weather volume!"));
                }
                ModConfigs.isWeatherToggled = !ModConfigs.isWeatherToggled;
                ModConfigs.saveConfigs();
            }
            while (toggleBlockVolume.wasPressed()) {
                SoundCategory soundCategory = SoundCategory.BLOCKS;
                ModConfigs.saveConfigs();
                if (ModConfigs.isBlockToggled) {
                    client.options.setSoundVolume(soundCategory, (float)(ModConfigs.BLOCK_VOL_NORMAL / 100));
                    client.inGameHud.getChatHud().addMessage(Text.literal("Untoggled Blocks volume!"));
                } else {
                    client.options.setSoundVolume(soundCategory, (float)(ModConfigs.BLOCK_VOL_TOGGLED * 100));
                    client.inGameHud.getChatHud().addMessage(Text.literal("Toggled Blocks volume!"));
                }
                ModConfigs.isBlockToggled = !ModConfigs.isBlockToggled;
                ModConfigs.saveConfigs();
            }
            while (toggleHostileVolume.wasPressed()) {
                SoundCategory soundCategory = SoundCategory.HOSTILE;
                ModConfigs.saveConfigs();
                if (ModConfigs.isHostileToggled) {
                    client.options.setSoundVolume(soundCategory, (float)(ModConfigs.HOSTILE_VOL_NORMAL / 100));
                    client.inGameHud.getChatHud().addMessage(Text.literal("Untoggled Hostile Creatures volume!"));
                } else {
                    client.options.setSoundVolume(soundCategory, (float)(ModConfigs.HOSTILE_VOL_TOGGLED * 100));
                    client.inGameHud.getChatHud().addMessage(Text.literal("Toggled Hostile Creatures volume!"));
                }
                ModConfigs.isHostileToggled = !ModConfigs.isHostileToggled;
                ModConfigs.saveConfigs();
            }
            while (toggleNeutralVolume.wasPressed()) {
                SoundCategory soundCategory = SoundCategory.NEUTRAL;
                ModConfigs.saveConfigs();
                if (ModConfigs.isNeutralToggled) {
                    client.options.setSoundVolume(soundCategory, (float)(ModConfigs.NEUTRAL_VOL_NORMAL / 100));
                    client.inGameHud.getChatHud().addMessage(Text.literal("Untoggled Neutral Creatures volume!"));
                } else {
                    client.options.setSoundVolume(soundCategory, (float)(ModConfigs.NEUTRAL_VOL_TOGGLED * 100));
                    client.inGameHud.getChatHud().addMessage(Text.literal("Toggled Neutral Creatures volume!"));
                }
                ModConfigs.isNeutralToggled = !ModConfigs.isNeutralToggled;
                ModConfigs.saveConfigs();
            }
            while (togglePlayersVolume.wasPressed()) {
                SoundCategory soundCategory = SoundCategory.PLAYERS;
                ModConfigs.saveConfigs();
                if (ModConfigs.isPlayersToggled) {
                    client.options.setSoundVolume(soundCategory, (float)(ModConfigs.PLAYERS_VOL_NORMAL / 100));
                    client.inGameHud.getChatHud().addMessage(Text.literal("Untoggled Player volume!"));
                } else {
                    client.options.setSoundVolume(soundCategory, (float)(ModConfigs.PLAYERS_VOL_TOGGLED * 100));
                    client.inGameHud.getChatHud().addMessage(Text.literal("Toggled Player volume!"));
                }
                ModConfigs.isPlayersToggled = !ModConfigs.isPlayersToggled;
                ModConfigs.saveConfigs();
            }
            while (toggleAmbientVolume.wasPressed()) {
                SoundCategory soundCategory = SoundCategory.AMBIENT;
                ModConfigs.saveConfigs();
                if (ModConfigs.isAmbientToggled) {
                    client.options.setSoundVolume(soundCategory, (float)(ModConfigs.AMBIENT_VOL_NORMAL / 100));
                    client.inGameHud.getChatHud().addMessage(Text.literal("Untoggled Ambient/Environment volume!"));
                } else {
                    client.options.setSoundVolume(soundCategory, (float)(ModConfigs.AMBIENT_VOL_TOGGLED * 100));
                    client.inGameHud.getChatHud().addMessage(Text.literal("Toggled Ambient/Environment volume!"));
                }
                ModConfigs.isAmbientToggled = !ModConfigs.isAmbientToggled;
                ModConfigs.saveConfigs();
            }
            while (toggleVoiceVolume.wasPressed()) {
                SoundCategory soundCategory = SoundCategory.AMBIENT;
                ModConfigs.saveConfigs();
                if (ModConfigs.isVoiceToggled) {
                    client.options.setSoundVolume(soundCategory, (float)(ModConfigs.VOICE_VOL_NORMAL / 100));
                    client.inGameHud.getChatHud().addMessage(Text.literal("Untoggled Voice/Speech volume!"));
                } else {
                    client.options.setSoundVolume(soundCategory, (float)(ModConfigs.VOICE_VOL_TOGGLED * 100));
                    client.inGameHud.getChatHud().addMessage(Text.literal("Toggled Voice/Speech volume!"));
                }
                ModConfigs.isVoiceToggled = !ModConfigs.isVoiceToggled;
                ModConfigs.saveConfigs();
            }
        });

    }
}
