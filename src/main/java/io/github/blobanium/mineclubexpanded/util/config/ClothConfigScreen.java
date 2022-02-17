package io.github.blobanium.mineclubexpanded.util.config;

import com.google.common.collect.Lists;
import io.github.blobanium.mineclubexpanded.MineclubExpanded;
import me.shedaniel.clothconfig2.api.ConfigBuilder;
import me.shedaniel.clothconfig2.api.ConfigCategory;
import me.shedaniel.clothconfig2.api.ConfigEntryBuilder;
import net.minecraft.client.gui.screen.Screen;
import net.minecraft.text.TranslatableText;

public class ClothConfigScreen {
    public static Screen createConfig(Screen parent){

        final ConfigBuilder builder = ConfigBuilder.create()
                .setParentScreen(parent)
                .setTitle(new TranslatableText("mineclub-expanded.config"));

        // Serialise the config into the config file. This will be called last after all variables are updated.
        builder.setSavingRunnable(ConfigReader::onConfigSave);

        ConfigEntryBuilder entryBuilder = builder.entryBuilder();

        ConfigCategory general = builder.getOrCreateCategory(new TranslatableText("mineclub-expanded.category.main"));
        ConfigCategory presence = builder.getOrCreateCategory(new TranslatableText("mineclub-expanded.category.presence"));
        ConfigCategory outbid = builder.getOrCreateCategory(new TranslatableText("mineclub-expanded.category.outbid"));
        ConfigCategory autoreconnect = builder.getOrCreateCategory(new TranslatableText("mineclub-expanded.category.autoreconnect"));


        general.addEntry(entryBuilder.startBooleanToggle(DynamicModMenuTranslatable.getDiscordRPTranslatable(), ConfigReader.richPresence).setDefaultValue(false).setTooltip(DynamicModMenuTranslatable.getDiscordRPDescriptionTranslatable()).requireRestart().setSaveConsumer(newValue -> ConfigReader.richPresence = newValue).build());
        presence.addEntry(entryBuilder.startStringDropdownMenu(new TranslatableText("mineclub-expanded.config.presencedetail"), ConfigReader.rpCustomDetails).setDefaultValue("ServerIP").setSuggestionMode(false).setSelections(Lists.newArrayList("ServerIP", "Username", "Mod Version")).setTooltip(new TranslatableText("mineclub-expanded.config.presencedetail.description")).setSaveConsumer(newValue -> ConfigReader.rpCustomDetails = newValue).build());
        presence.addEntry(entryBuilder.startTextDescription(new TranslatableText("mineclub-expanded.configtext.presence")).build());

        general.addEntry(entryBuilder.startBooleanToggle(new TranslatableText("mineclub-expanded.config.outbidsound"), ConfigReader.outbidNotification).setDefaultValue(false).setTooltip(new TranslatableText("mineclub-expanded.config.outbidsound.description")).setSaveConsumer(newValue -> ConfigReader.outbidNotification = newValue).build());
        outbid.addEntry(entryBuilder.startIntSlider(new TranslatableText("mineclub-expanded.config.outbidvolume"), ConfigReader.outbidVolume, 0, 200).setDefaultValue(100).setTooltip(new TranslatableText("mineclub-expanded.config.outbidvolume.description")).setSaveConsumer(newValue -> ConfigReader.outbidVolume = newValue).build());
        outbid.addEntry(entryBuilder.startTextDescription(new TranslatableText("mineclub-expanded.configtext.outbid")).build());

        general.addEntry(entryBuilder.startBooleanToggle(new TranslatableText("mineclub-expanded.config.autogg"), ConfigReader.autogg).setDefaultValue(false).setTooltip(new TranslatableText("mineclub-expanded.config.autogg.description")).setSaveConsumer(newValue -> ConfigReader.autogg = newValue).build());

        general.addEntry(entryBuilder.startBooleanToggle(new TranslatableText("mineclub-expanded.config.autoreconnect"), ConfigReader.autoReconnect).setDefaultValue(false).setTooltip(new TranslatableText("mineclub-expanded.config.autoreconnect.description")).setSaveConsumer(newValue -> ConfigReader.autoReconnect = newValue).build());
        autoreconnect.addEntry(entryBuilder.startIntSlider(new TranslatableText("mineclub-expanded.config.autoreconnectattempts"), ConfigReader.autoReconnectAttempts, 1, 20).setDefaultValue(3).setTooltip(new TranslatableText("mineclub-expanded.config.autoreconnectattempts.description")).setSaveConsumer(newValue -> ConfigReader.autoReconnectAttempts = newValue).build());
        autoreconnect.addEntry(entryBuilder.startIntSlider(new TranslatableText("mineclub-expanded.config.autoreconnectseconds"), ConfigReader.autoReconnectSeconds, 1, 60).setDefaultValue(5).setTooltip(new TranslatableText("mineclub-expanded.config.autoreconnectseconds.description")).setSaveConsumer(newValue -> ConfigReader.autoReconnectSeconds = newValue).build());
        autoreconnect.addEntry(entryBuilder.startTextDescription(new TranslatableText("mineclub-expanded.configtext.autoreconnect")).build());

        general.addEntry(entryBuilder.startBooleanToggle(new TranslatableText("mineclub-expanded.config.expressconnect"), ConfigReader.expressConnect).setDefaultValue(false).setTooltip(new TranslatableText("mineclub-expanded.config.expressconnect.description")).setSaveConsumer(newValue -> ConfigReader.expressConnect = newValue).build());
        if(MineclubExpanded.debugmode){
            ConfigCategory debug = builder.getOrCreateCategory(new TranslatableText("mineclub-expanded.category.debug"));
            debug.addEntry(entryBuilder.startBooleanToggle(new TranslatableText("mineclub-expanded.config.debug.asyncticks"), ConfigReader.debugAsyncTicks).setDefaultValue(false).setTooltip(new TranslatableText("mineclub-expanded.config.debug.asyncticks.description")).setSaveConsumer(newValue -> ConfigReader.debugAsyncTicks = newValue).build());
            debug.addEntry(entryBuilder.startIntSlider(new TranslatableText("mineclub-expanded.config.debug.asynctickthreads"), ConfigReader.debugAsyncTickThreads, 1, Runtime.getRuntime().availableProcessors()).setDefaultValue(2).setTooltip(new TranslatableText("mineclub-expanded.config.debug.asynctickthreads")).setSaveConsumer(newValue -> ConfigReader.debugAsyncTickThreads = newValue).build());
        }

        return builder.build();
    }
}
