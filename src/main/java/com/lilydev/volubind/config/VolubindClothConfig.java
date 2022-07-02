package com.lilydev.volubind.config;

import com.terraformersmc.modmenu.api.ConfigScreenFactory;
import me.shedaniel.clothconfig2.api.ConfigBuilder;
import me.shedaniel.clothconfig2.api.ConfigCategory;
import me.shedaniel.clothconfig2.api.ConfigEntryBuilder;
import net.minecraft.client.gui.screen.Screen;
import net.minecraft.text.Text;

public class VolubindClothConfig implements ConfigScreenFactory<Screen> {

    public VolubindClothConfig() {

    }

    @Override
    public Screen create(Screen screen) {
        ConfigBuilder builder = ConfigBuilder.create()
                .setTitle(Text.translatable("title.volubind.config"))
                .setSavingRunnable(ModConfigs::saveConfigs)
                .setParentScreen(screen)
                .transparentBackground();


        ConfigEntryBuilder entryBuilder = builder.entryBuilder();
        ConfigCategory general = builder.getOrCreateCategory(Text.translatable("category.volubind.general"));


        general.addEntry(entryBuilder.startIntSlider(Text.translatable("option.volubind.masterVolToggle"),
                        ModConfigs.MASTER_VOL_TOGGLED, 0, 100)
                .setDefaultValue(0)
                .setSaveConsumer(newValue -> ModConfigs.MASTER_VOL_TOGGLED = newValue)
                .setTooltip(Text.literal(
                        "Toggles master volume to this value when corresponding keybind is pressed."
                ))
                .build());
        general.addEntry(entryBuilder.startIntSlider(Text.translatable("option.volubind.musicVolToggle"),
                        ModConfigs.MUSIC_VOL_TOGGLED, 0, 100)
                .setDefaultValue(0)
                .setSaveConsumer(newValue -> ModConfigs.MUSIC_VOL_TOGGLED = newValue)
                .build());
        general.addEntry(entryBuilder.startIntSlider(Text.translatable("option.volubind.recordsVolToggle"),
                        ModConfigs.RECORDS_VOL_TOGGLED, 0, 100)
                .setDefaultValue(0)
                .setSaveConsumer(newValue -> ModConfigs.RECORDS_VOL_TOGGLED = newValue)
                .build());
        general.addEntry(entryBuilder.startIntSlider(Text.translatable("option.volubind.weatherVolToggle"),
                        ModConfigs.WEATHER_VOL_TOGGLED, 0, 100)
                .setDefaultValue(0)
                .setSaveConsumer(newValue -> ModConfigs.WEATHER_VOL_TOGGLED = newValue)
                .build());
        general.addEntry(entryBuilder.startIntSlider(Text.translatable("option.volubind.blockVolToggle"),
                        ModConfigs.BLOCK_VOL_TOGGLED, 0, 100)
                .setDefaultValue(0)
                .setSaveConsumer(newValue -> ModConfigs.BLOCK_VOL_TOGGLED = newValue)
                .build());
        general.addEntry(entryBuilder.startIntSlider(Text.translatable("option.volubind.hostileVolToggle"),
                        ModConfigs.HOSTILE_VOL_TOGGLED, 0, 100)
                .setDefaultValue(0)
                .setSaveConsumer(newValue -> ModConfigs.HOSTILE_VOL_TOGGLED = newValue)
                .build());
        general.addEntry(entryBuilder.startIntSlider(Text.translatable("option.volubind.neutralVolToggle"),
                        ModConfigs.NEUTRAL_VOL_TOGGLED, 0, 100)
                .setDefaultValue(0)
                .setSaveConsumer(newValue -> ModConfigs.NEUTRAL_VOL_TOGGLED = newValue)
                .build());
        general.addEntry(entryBuilder.startIntSlider(Text.translatable("option.volubind.playersVolToggle"),
                        ModConfigs.PLAYERS_VOL_TOGGLED, 0, 100)
                .setDefaultValue(0)
                .setSaveConsumer(newValue -> ModConfigs.PLAYERS_VOL_TOGGLED = newValue)
                .build());
        general.addEntry(entryBuilder.startIntSlider(Text.translatable("option.volubind.ambientVolToggle"),
                        ModConfigs.AMBIENT_VOL_TOGGLED, 0, 100)
                .setDefaultValue(0)
                .setSaveConsumer(newValue -> ModConfigs.AMBIENT_VOL_TOGGLED = newValue)
                .build());
        general.addEntry(entryBuilder.startIntSlider(Text.translatable("option.volubind.voiceVolToggle"),
                        ModConfigs.VOICE_VOL_TOGGLED, 0, 100)
                .setDefaultValue(0)
                .setSaveConsumer(newValue -> ModConfigs.VOICE_VOL_TOGGLED = newValue)
                .build());

        return builder.build();
    }

}
