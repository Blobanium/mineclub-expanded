package io.github.blobanium.mineclubexpanded;

import io.github.blobanium.mineclubexpanded.util.config.ConfigReader;
import io.github.blobanium.mineclubexpanded.util.discord.DiscordRP;
import net.fabricmc.api.ModInitializer;
import net.fabricmc.loader.api.FabricLoader;
import net.minecraft.client.MinecraftClient;
import net.minecraft.client.network.ServerInfo;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

public class MineclubExpanded implements ModInitializer {
	// This logger is used to write text to the console and the log file.
	// It is considered best practice to use your mod id as the logger's name.
	// That way, it's clear which mod wrote info, warnings, and errors.
	public static final Logger LOGGER = LogManager.getLogger("Mineclub Expanded");
	public static boolean isChatOpen = false;
    public static String lastChatField;
	public static ServerInfo mineclub = new ServerInfo("Mineclub", "play.mineclub.com", false);
	public static boolean hasInitialized = false;
	public static String modVersion = String.valueOf(FabricLoader.getInstance().getModContainer("mineclubexpanded").get().getMetadata().getVersion());

    @Override
	public void onInitialize() {
		// This code runs as soon as Minecraft is in a mod-load-ready state.
		// However, some things (like resources) may still be uninitialized.
		// Proceed with mild caution.

		ConfigReader.configRegister();
		if(!MinecraftClient.IS_SYSTEM_MAC) {
			try {
				DiscordRP.startRP();
			} catch (Exception e) {
				LOGGER.error("Failed to start rich presence, Your Device/Install may not support rich presence! \n" + e);
				DiscordRP.discordRPErrorcode = 1;
			}
		} else {
			LOGGER.error("Rich Presence doesn't support macOS.");
			DiscordRP.discordRPErrorcode = 2;
		}
		mineclub.setResourcePackPolicy(ServerInfo.ResourcePackPolicy.ENABLED);
		hasInitialized = true;
		LOGGER.info("Mineclub Expanded Initialized!");
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
