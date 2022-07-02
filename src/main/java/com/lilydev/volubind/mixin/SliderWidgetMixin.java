package com.lilydev.volubind.mixin;

import com.lilydev.volubind.config.ModConfigs;
import net.minecraft.client.gui.widget.SliderWidget;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;

@Mixin(SliderWidget.class)
public class SliderWidgetMixin {

    @Inject(method = "onRelease", at = @At("HEAD"))
    private void injectVolumeSave(double mouseX, double mouseY, CallbackInfo callbackInfo) {
        ModConfigs.saveConfigs();
    }
}