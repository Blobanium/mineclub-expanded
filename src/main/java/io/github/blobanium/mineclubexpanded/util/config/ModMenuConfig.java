package io.github.blobanium.mineclubexpanded.util.config;

import com.google.common.collect.Lists;
import com.terraformersmc.modmenu.api.ConfigScreenFactory;
import com.terraformersmc.modmenu.api.ModMenuApi;
import io.github.blobanium.mineclubexpanded.util.discord.DiscordRP;
import me.shedaniel.clothconfig2.api.ConfigBuilder;
import me.shedaniel.clothconfig2.api.ConfigCategory;
import me.shedaniel.clothconfig2.api.ConfigEntryBuilder;
import net.fabricmc.loader.api.FabricLoader;
import net.minecraft.client.gui.screen.Screen;
import net.minecraft.text.LiteralText;
import net.minecraft.text.TranslatableText;


public class ModMenuConfig implements ModMenuApi {

    private static ConfigScreenFactory<?> CONFIG = FabricLoader.getInstance().isModLoaded("cloth-config2")
    ? new LTClothConfig()
    : parent -> null;
    
    @Override
    public ConfigScreenFactory<?> getModConfigScreenFactory() {
            // Return the screen here with the one you created from Cloth Config Builder;
            return CONFIG;
    }

    private static class LTClothConfig implements ConfigScreenFactory<Screen> {

        @Override
        public Screen create(Screen parent) {
            return ClothConfigScreen.createConfig(parent);
        }
    }
}
