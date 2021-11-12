package io.github.blobanium.mineclubexpanded;

import io.github.blobanium.mineclubexpanded.util.config.ConfigReader;
import io.github.blobanium.mineclubexpanded.util.discord.DiscordRP;
import net.fabricmc.api.ModInitializer;
import net.fabricmc.loader.api.FabricLoader;
import net.minecraft.client.MinecraftClient;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

public class MineclubExpanded implements ModInitializer {
	// This logger is used to write text to the console and the log file.
	// It is considered best practice to use your mod id as the logger's name.
	// That way, it's clear which mod wrote info, warnings, and errors.
	public static final Logger LOGGER = LogManager.getLogger("Mineclub Expanded");
	public static String lastChatField;

	@Override
	public void onInitialize() {
		// This code runs as soon as Minecraft is in a mod-load-ready state.
		// However, some things (like resources) may still be uninitialized.
		// Proceed with mild caution.

		LOGGER.info("Mineclub Expanded Initialized!");
		ConfigReader.configRegister();
		DiscordRP.startRP();
		DiscordRP.updateStatus("Beta Rich Presence", "v" + String.valueOf(FabricLoader.getInstance().getModContainer("mineclubexpanded").get().getMetadata().getVersion()));
	}

	public static boolean isOnMineclub() {
		try {
			return MinecraftClient.getInstance().getCurrentServerEntry().address.equals("mineclub.com") || MinecraftClient.getInstance().getCurrentServerEntry().address.equals("play.mineclub.com");
		} catch (NullPointerException e) {
			LOGGER.debug("Suppressing Null warning");
			return false;
		}
	}
}
