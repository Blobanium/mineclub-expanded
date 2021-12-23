package io.github.blobanium.mineclubexpanded.util.config;

import com.terraformersmc.modmenu.api.ConfigScreenFactory;
import com.terraformersmc.modmenu.api.ModMenuApi;
import io.github.blobanium.mineclubexpanded.util.discord.DiscordRP;
import me.shedaniel.clothconfig2.api.ConfigBuilder;
import me.shedaniel.clothconfig2.api.ConfigCategory;
import me.shedaniel.clothconfig2.api.ConfigEntryBuilder;
import net.fabricmc.loader.api.FabricLoader;
import net.minecraft.client.gui.screen.Screen;
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

            final ConfigBuilder builder = ConfigBuilder.create()
                .setParentScreen(parent)
                .setTitle(new TranslatableText("mineclub-expanded.config"));

            // Serialise the config into the config file. This will be called last after all variables are updated.
            builder.setSavingRunnable(ConfigReader::refreshConfig);
        
            ConfigEntryBuilder entryBuilder = builder.entryBuilder();

            ConfigCategory general = builder.getOrCreateCategory(new TranslatableText("mineclub-expanded.category.market"));

            if(DiscordRP.supportsRichPresence) {
                general.addEntry(entryBuilder.startBooleanToggle(new TranslatableText("mineclub-expanded.config.richpresence"), ConfigReader.richPresence)
                        .setDefaultValue(false)
                        .setTooltip(new TranslatableText("mineclub-expanded.config.richpresence.description"))
                        .setSaveConsumer(newValue -> ConfigReader.richPresence = newValue)
                        .build());
            } else {
                general.addEntry(entryBuilder.startBooleanToggle(new TranslatableText("mineclub-expanded.config.richpresence.unsupported"), ConfigReader.richPresence)
                        .setDefaultValue(false)
                        .setTooltip(new TranslatableText("mineclub-expanded.config.richpresence.unsupported.description"))
                        .setSaveConsumer(newValue -> ConfigReader.richPresence = newValue)
                        .build());
            }

            general.addEntry(entryBuilder.startBooleanToggle(new TranslatableText("mineclub-expanded.config.outbidsound"), ConfigReader.outbidNotification)
                    .setDefaultValue(false)
                    .setTooltip(new TranslatableText("mineclub-expanded.config.outbidsound.description"))
                    .setSaveConsumer(newValue -> ConfigReader.outbidNotification = newValue)
                    .build());

            general.addEntry(entryBuilder.startIntSlider(new TranslatableText("mineclub-expanded.config.outbidvolume"), ConfigReader.outbidVolume, 0, 200)
                    .setDefaultValue(100)
                    .setTooltip(new TranslatableText("mineclub-expanded.config.outbidvolume.description"))
                    .setSaveConsumer(newValue -> ConfigReader.outbidVolume = newValue)
                    .build());

            general.addEntry(entryBuilder.startBooleanToggle(new TranslatableText("mineclub-expanded.config.autogg"), ConfigReader.autogg)
                    .setDefaultValue(false)
                    .setTooltip(new TranslatableText("mineclub-expanded.config.autogg.description"))
                    .setSaveConsumer(newValue -> ConfigReader.autogg = newValue)
                    .build());

            general.addEntry(entryBuilder.startBooleanToggle(new TranslatableText("mineclub-expanded.config.autoreconnect"), ConfigReader.autoReconnect)
                    .setDefaultValue(false)
                    .setTooltip(new TranslatableText("mineclub-expanded.config.autoreconnect.description"))
                    .setSaveConsumer(newValue -> ConfigReader.autoReconnect = newValue)
                    .build());

            general.addEntry(entryBuilder.startIntSlider(new TranslatableText("mineclub-expanded.config.autoreconnectattempts"), ConfigReader.autoReconnectAttempts, 1, 20)
                    .setDefaultValue(3)
                    .setTooltip(new TranslatableText("mineclub-expanded.config.autoreconnectattempts.description"))
                    .setSaveConsumer(newValue -> ConfigReader.autoReconnectAttempts = newValue)
                    .build());

            general.addEntry(entryBuilder.startIntSlider(new TranslatableText("mineclub-expanded.config.autoreconnectseconds"), ConfigReader.autoReconnectSeconds, 1, 60)
                    .setDefaultValue(5)
                    .setTooltip(new TranslatableText("mineclub-expanded.config.autoreconnectseconds.description"))
                    .setSaveConsumer(newValue -> ConfigReader.autoReconnectSeconds = newValue)
                    .build());

            general.addEntry(entryBuilder.startBooleanToggle(new TranslatableText("mineclub-expanded.config.expressconnect"), ConfigReader.expressConnect)
                    .setDefaultValue(false)
                    .setTooltip(new TranslatableText("mineclub-expanded.config.expressconnect.description"))
                    .setSaveConsumer(newValue -> ConfigReader.expressConnect = newValue)
                    .build());



            return builder.build();
        }
    }
}
