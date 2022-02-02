package io.github.blobanium.mineclubexpanded;

import io.github.blobanium.mineclubexpanded.util.command.CommandParser;
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
		//Registers the config
		ConfigReader.configRegister();

		//Start Discord IPC.
		DiscordRP.startRP();

		//Set Resource Pack Policy for mineclub to Enabled as Mineclub requires a resource pack.
		mineclub.setResourcePackPolicy(ServerInfo.ResourcePackPolicy.ENABLED);

		//Parse all commands
		CommandParser.registerCommand();

		//Finish initializing.
		hasInitialized = true;
		LOGGER.info("Mineclub Expanded Initialized!");
	}

	public static boolean isOnMineclub() {
		if(MinecraftClient.getInstance().getCurrentServerEntry() != null) {
			String currentServerAddress = MinecraftClient.getInstance().getCurrentServerEntry().address;
			return currentServerAddress.endsWith("mineclub.com") || currentServerAddress.endsWith(".mineclub.house");
		}else {
			return  false;
		}
	}
}
