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
import net.minecraft.text.Style;
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

    private static void setGameSoundVolume(MinecraftClient client, SoundCategory soundCategory, int volNormal, int volToggled, boolean isToggled) {
        String isToggledTxt;
        if (isToggled) {
            client.options.setSoundVolume(soundCategory, (float)volNormal / 100);
            isToggledTxt = "Untoggled";
        }
        else {
            client.options.setSoundVolume(soundCategory, (float)volToggled * 100);
            isToggledTxt = "Toggled";
        }
        int newVol = (int)(client.options.getSoundVolume(soundCategory) * 100);
        Text overlayText = Text.literal(isToggledTxt + " sound " + soundCategory.name() + ". Set to: " + newVol);
        client.inGameHud.getChatHud().addMessage(overlayText);
    }

    @Override
    public void onInitializeClient() {

        AtomicBoolean normalVolumeSet = new AtomicBoolean(false);

        ModConfigs.registerConfigs();


        ClientTickEvents.END_CLIENT_TICK.register(client -> {
            if (!normalVolumeSet.get()) {
                setNormalVolumeConfigOnInit();
                normalVolumeSet.set(true);
            }
            while (configMenuKeybind.wasPressed()) {
                client.setScreen(new VolubindClothConfig().create(client.currentScreen));
            }

            //*
            // Toggle volume binds.
            //*
            while (toggleMasterVolume.wasPressed()) {
                ModConfigs.saveConfigs();
                setGameSoundVolume(
                        client,
                        SoundCategory.MASTER,
                        ModConfigs.MASTER_VOL_NORMAL,
                        ModConfigs.MASTER_VOL_TOGGLED,
                        ModConfigs.isMasterToggled
                );
                ModConfigs.isMasterToggled = !ModConfigs.isMasterToggled;
                ModConfigs.saveConfigs();
            }
            while (toggleMusicVolume.wasPressed()) {
                ModConfigs.saveConfigs();
                setGameSoundVolume(
                        client,
                        SoundCategory.MUSIC,
                        ModConfigs.MUSIC_VOL_NORMAL,
                        ModConfigs.MUSIC_VOL_TOGGLED,
                        ModConfigs.isMusicToggled
                );
                ModConfigs.isMusicToggled = !ModConfigs.isMusicToggled;
                ModConfigs.saveConfigs();
            }
            while (toggleRecordsVolume.wasPressed()) {
                ModConfigs.saveConfigs();
                setGameSoundVolume(
                        client,
                        SoundCategory.RECORDS,
                        ModConfigs.RECORDS_VOL_NORMAL,
                        ModConfigs.RECORDS_VOL_TOGGLED,
                        ModConfigs.isRecordsToggled
                );
                ModConfigs.isRecordsToggled = !ModConfigs.isRecordsToggled;
                ModConfigs.saveConfigs();
            }
            while (toggleWeatherVolume.wasPressed()) {
                ModConfigs.saveConfigs();
                setGameSoundVolume(
                        client,
                        SoundCategory.WEATHER,
                        ModConfigs.WEATHER_VOL_NORMAL,
                        ModConfigs.WEATHER_VOL_TOGGLED,
                        ModConfigs.isWeatherToggled
                );
                ModConfigs.isWeatherToggled = !ModConfigs.isWeatherToggled;
                ModConfigs.saveConfigs();
            }
            while (toggleBlockVolume.wasPressed()) {
                ModConfigs.saveConfigs();
                setGameSoundVolume(
                        client,
                        SoundCategory.BLOCKS,
                        ModConfigs.BLOCK_VOL_NORMAL,
                        ModConfigs.BLOCK_VOL_TOGGLED,
                        ModConfigs.isBlockToggled
                );
                ModConfigs.isBlockToggled = !ModConfigs.isBlockToggled;
                ModConfigs.saveConfigs();
            }
            while (toggleHostileVolume.wasPressed()) {
                ModConfigs.saveConfigs();
                setGameSoundVolume(
                        client,
                        SoundCategory.HOSTILE,
                        ModConfigs.HOSTILE_VOL_NORMAL,
                        ModConfigs.HOSTILE_VOL_TOGGLED,
                        ModConfigs.isHostileToggled
                );
                ModConfigs.isHostileToggled = !ModConfigs.isHostileToggled;
                ModConfigs.saveConfigs();
            }
            while (toggleNeutralVolume.wasPressed()) {
                ModConfigs.saveConfigs();
                setGameSoundVolume(
                        client,
                        SoundCategory.NEUTRAL,
                        ModConfigs.NEUTRAL_VOL_NORMAL,
                        ModConfigs.NEUTRAL_VOL_TOGGLED,
                        ModConfigs.isNeutralToggled
                );
                ModConfigs.isNeutralToggled = !ModConfigs.isNeutralToggled;
                ModConfigs.saveConfigs();
            }
            while (togglePlayersVolume.wasPressed()) {
                ModConfigs.saveConfigs();
                setGameSoundVolume(
                        client,
                        SoundCategory.PLAYERS,
                        ModConfigs.PLAYERS_VOL_NORMAL,
                        ModConfigs.PLAYERS_VOL_TOGGLED,
                        ModConfigs.isPlayersToggled
                );
                ModConfigs.isPlayersToggled = !ModConfigs.isPlayersToggled;
                ModConfigs.saveConfigs();
            }
            while (toggleAmbientVolume.wasPressed()) {
                ModConfigs.saveConfigs();
                setGameSoundVolume(
                        client,
                        SoundCategory.AMBIENT,
                        ModConfigs.AMBIENT_VOL_NORMAL,
                        ModConfigs.AMBIENT_VOL_TOGGLED,
                        ModConfigs.isAmbientToggled
                );
                ModConfigs.isAmbientToggled = !ModConfigs.isAmbientToggled;
                ModConfigs.saveConfigs();
            }
            while (toggleVoiceVolume.wasPressed()) {
                ModConfigs.saveConfigs();
                setGameSoundVolume(
                        client,
                        SoundCategory.VOICE,
                        ModConfigs.VOICE_VOL_NORMAL,
                        ModConfigs.VOICE_VOL_TOGGLED,
                        ModConfigs.isVoiceToggled
                );
                ModConfigs.isVoiceToggled = !ModConfigs.isVoiceToggled;
                ModConfigs.saveConfigs();
            }
        });

    }
}
