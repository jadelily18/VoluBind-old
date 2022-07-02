package com.lilydev.volubind.mixin;

import com.lilydev.volubind.Volubind;
import com.lilydev.volubind.config.ModConfigs;
import net.minecraft.client.gui.widget.SoundSliderWidget;
import net.minecraft.sound.SoundCategory;
import org.spongepowered.asm.mixin.Final;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Shadow;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;

@Mixin(SoundSliderWidget.class)
public class SoundSliderWidgetMixin {
    @Shadow @Final private SoundCategory category;

    @Inject(method = "applyValue", at = @At("HEAD"))
    private void injectConfigUpdate(CallbackInfo callbackInfo) {
        SoundSliderWidget thisInstance = ((SoundSliderWidget) (Object)this);

        double value = ((SliderWidgetAccessor) thisInstance).getValue();

        switch (category.name()) {
            case "MASTER" -> {
                ModConfigs.MASTER_VOL_NORMAL = valueConversion(value);
                ModConfigs.isMasterToggled = false;
            }
            case "MUSIC" -> {
                ModConfigs.MUSIC_VOL_NORMAL = valueConversion(value);
                ModConfigs.isMusicToggled = false;
            }
            case "RECORDS" -> {
                ModConfigs.RECORDS_VOL_NORMAL = valueConversion(value);
                ModConfigs.isRecordsToggled = false;
            }
            case "WEATHER" -> {
                ModConfigs.WEATHER_VOL_NORMAL = valueConversion(value);
                ModConfigs.isWeatherToggled = false;
            }
            case "BLOCK" -> {
                ModConfigs.BLOCK_VOL_NORMAL = valueConversion(value);
                ModConfigs.isBlockToggled = false;
            }
            case "HOSTILE" -> {
                ModConfigs.HOSTILE_VOL_NORMAL = valueConversion(value);
                ModConfigs.isHostileToggled = false;
            }
            case "NEUTRAL" -> {
                ModConfigs.NEUTRAL_VOL_NORMAL = valueConversion(value);
                ModConfigs.isNeutralToggled = false;
            }
            case "PLAYER" -> {
                ModConfigs.PLAYERS_VOL_NORMAL = valueConversion(value);
                ModConfigs.isPlayersToggled = false;
            }
            case "AMBIENT" -> {
                ModConfigs.AMBIENT_VOL_NORMAL = valueConversion(value);
                ModConfigs.isAmbientToggled = false;
            }
            case "VOICE" -> {
                ModConfigs.VOICE_VOL_NORMAL = valueConversion(value);
                ModConfigs.isVoiceToggled = false;
            }
        }
    }

    private int valueConversion(double value) {
        return (int) (value * 100);
    }

}
