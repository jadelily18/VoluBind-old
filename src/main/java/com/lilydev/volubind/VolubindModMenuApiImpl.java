package com.lilydev.volubind;


import com.lilydev.volubind.config.VolubindClothConfig;
import com.terraformersmc.modmenu.api.ConfigScreenFactory;
import com.terraformersmc.modmenu.api.ModMenuApi;

public class VolubindModMenuApiImpl implements ModMenuApi {
    @Override
    public ConfigScreenFactory<?> getModConfigScreenFactory() {
        return screen -> new VolubindClothConfig().create(screen);
    }
}
