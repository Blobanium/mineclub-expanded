package io.github.blobanium.mineclubexpanded.util.config;

import com.google.common.collect.Lists;
import io.github.blobanium.mineclubexpanded.MineclubExpanded;
import me.shedaniel.clothconfig2.api.ConfigBuilder;
import me.shedaniel.clothconfig2.api.ConfigCategory;
import me.shedaniel.clothconfig2.api.ConfigEntryBuilder;
import net.minecraft.client.gui.screen.Screen;
import net.minecraft.text.LiteralText;
import net.minecraft.text.TranslatableText;
import net.minecraft.world.gen.feature.LakeFeature;

public class ClothConfigScreen {
    //Yes, this class needs some cleanup, but idk how im going to do it though... if you do however, feel free to make a pull request.

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
        ConfigCategory serial = builder.getOrCreateCategory(new TranslatableText("mineclub-expanded.category.serial"));


        general.addEntry(entryBuilder.startBooleanToggle(DynamicModMenuTranslatable.getDiscordRPTranslatable(), ConfigReader.richPresence).setDefaultValue(false).setTooltip(DynamicModMenuTranslatable.getDiscordRPDescriptionTranslatable()).requireRestart().setSaveConsumer(newValue -> ConfigReader.richPresence = newValue).build());
        presence.addEntry(entryBuilder.startStringDropdownMenu(new TranslatableText("mineclub-expanded.config.presencedetail"), ConfigReader.rpCustomDetails).setDefaultValue("Compact").setSuggestionMode(false).setSelections(Lists.newArrayList("Compact", "ServerIP", "Username", "Mod Version")).setTooltip(new TranslatableText("mineclub-expanded.config.presencedetail.description")).setSaveConsumer(newValue -> ConfigReader.rpCustomDetails = newValue).build());
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

        general.addEntry(entryBuilder.startBooleanToggle(new TranslatableText("mineclub-expanded.config.hideserialid"), ConfigReader.hideSerialID).setDefaultValue(false).setTooltip(new TranslatableText("mineclub-expanded.config.hideserialid.description")).setSaveConsumer(newValue -> ConfigReader.hideSerialID = newValue).build());
        serial.addEntry(entryBuilder.startStringDropdownMenu(new TranslatableText("mineclub-expanded.config.serialcolor"),  ConfigReader.serialColor).setDefaultValue("White").setSuggestionMode(false).setSelections(Lists.newArrayList("White", "Dark Blue", "Dark Green", "Dark Aqua", "Dark Red", "Dark Purple", "Gold", "Gray", "Dark Gray", "Blue", "Green", "Aqua", "Red", "Light Purple", "Yellow")).setTooltip(new TranslatableText("mineclub-expanded.config.serialcolor.description")).setSaveConsumer(newValue ->  ConfigReader.serialColor = newValue).build());
        serial.addEntry(entryBuilder.startBooleanToggle(new TranslatableText("mineclub-expanded.config.showdigits"), ConfigReader.showDigits).setDefaultValue(false).setTooltip(new TranslatableText("mineclub-expanded.config.showdigits.description")).setSaveConsumer(newValue -> ConfigReader.showDigits = newValue).build());


        general.addEntry(entryBuilder.startBooleanToggle(new TranslatableText("mineclub-expanded.config.hidemcbetsdata"), ConfigReader.hidemcBetsData).setDefaultValue(false).setTooltip(new TranslatableText("mineclub-expanded.config.hidemcbetsdata.description")).setSaveConsumer(newValue -> ConfigReader.hidemcBetsData = newValue).build());

        general.addEntry(entryBuilder.startBooleanToggle(new TranslatableText("mineclub-expanded.config.admineventnotif"), ConfigReader.adminEventNotification).setDefaultValue(false).setTooltip(new TranslatableText("mineclub-expanded.config.admineventnotif.description")).setSaveConsumer(newValue -> ConfigReader.adminEventNotification = newValue).build());


        if(MineclubExpanded.debugmode){
            ConfigCategory debug = builder.getOrCreateCategory(new TranslatableText("mineclub-expanded.category.debug"));
            debug.addEntry(entryBuilder.startBooleanToggle(new TranslatableText("mineclub-expanded.config.debug.asyncticks"), ConfigReader.debugAsyncTicks).setDefaultValue(false).setTooltip(new TranslatableText("mineclub-expanded.config.debug.asyncticks.description")).setSaveConsumer(newValue -> ConfigReader.debugAsyncTicks = newValue).build());
            debug.addEntry(entryBuilder.startIntSlider(new TranslatableText("mineclub-expanded.config.debug.asynctickthreads"), ConfigReader.debugAsyncTickThreads, 1, Runtime.getRuntime().availableProcessors()).setDefaultValue(2).setTooltip(new TranslatableText("mineclub-expanded.config.debug.asynctickthreads.description")).setSaveConsumer(newValue -> ConfigReader.debugAsyncTickThreads = newValue).build());
            debug.addEntry(entryBuilder.startStrField(new TranslatableText("mineclub-expanded.config.debug.overridekey"), ConfigReader.overrideKey).setDefaultValue("").setSaveConsumer(newValue -> ConfigReader.overrideKey = newValue).build());
        }

        return builder.build();
    }
}
