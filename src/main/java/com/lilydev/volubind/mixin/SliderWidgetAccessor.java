package com.lilydev.volubind.mixin;

import net.minecraft.client.gui.widget.SliderWidget;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.gen.Accessor;

@Mixin(SliderWidget.class)
interface SliderWidgetAccessor {
    @Accessor("value")
    double getValue();
}