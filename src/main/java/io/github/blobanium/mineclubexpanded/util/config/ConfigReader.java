package io.github.blobanium.mineclubexpanded.util.config;

import io.github.blobanium.mineclubexpanded.util.discord.DiscordRP;
import net.fabricmc.loader.api.FabricLoader;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.io.IOException;
import java.nio.file.Files;



public class ConfigReader {
	//variables
	public static boolean needsConfigRefresh = false;
	public static boolean refreshingConfig = false;

	//configs
	public static boolean outbidNotification = false;
	public static boolean autogg = false;
	public static int outbidVolume = 100;
	public static boolean richPresence = false;
	public static boolean autoReconnect = false;
	public static int autoReconnectAttempts = 3;
	public static int autoReconnectSeconds = 5;
	public static boolean expressConnect = false;
	public static String rpCustomDetails = "";
	public static boolean debugAsyncTicks = false;

    public static final Logger LOGGER = LogManager.getLogger("Mineclub Expanded");

    public static void configRegister(){
    	LOGGER.debug("Registering config..");
    	SimpleConfig CONFIG = SimpleConfig.of("MineclubExpanded").provider(ConfigReader::ltProvider).request();
    	final boolean outbidConfig = CONFIG.getOrDefault("outbid_notification", outbidNotification);
		final boolean autoggConfig = CONFIG.getOrDefault("auto_gg", autogg);
		final int outbidVolumeConfig = CONFIG.getOrDefault("outbid_volume", outbidVolume);
		final boolean richPresenceConfig = CONFIG.getOrDefault("rich_presence", richPresence);
		final boolean autoReconnectConfig = CONFIG.getOrDefault("auto_reconnect", autoReconnect);
		final int autoReconnectAttemptsConfig = CONFIG.getOrDefault("auto_reconnect_attempts", autoReconnectAttempts);
		final int autoReconnectSecondsConfig = CONFIG.getOrDefault("auto_reconnect_seconds", autoReconnectSeconds);
		final boolean expressConnectConfig = CONFIG.getOrDefault("express_connect",	expressConnect);
		final String rpCustomDetailsConfig = CONFIG.getOrDefault("rich_presence_detail", rpCustomDetails);
		final boolean debugAsyncTicksConfig = CONFIG.getOrDefault("express_connect",	debugAsyncTicks);

		if(outbidConfig){
			outbidNotification = true;
		}
		if(autoggConfig){
			autogg = true;
		}
		outbidVolume = outbidVolumeConfig;
		if(richPresenceConfig){
			richPresence = true;
		} else {
			if(DiscordRP.hasRPStarted && !DiscordRP.hasBlankStatus){
				DiscordRP.clearStatus();
			}
		}
		if(autoReconnectConfig){
			autoReconnect = true;
		}
		autoReconnectAttempts = autoReconnectAttemptsConfig;
		autoReconnectSeconds = autoReconnectSecondsConfig;
		if(expressConnectConfig){
			expressConnect = true;
		}
		rpCustomDetails = rpCustomDetailsConfig;
		if(debugAsyncTicksConfig){
			debugAsyncTicks = true;
		}
		LOGGER.debug("Regestering done!");
    }

    private static String ltProvider(String filename) {
		return "#Mineclub Expanded Config File."
				+ "\noutbid_notification=" + outbidNotification
				+ "\nauto_gg=" + autogg
				+ "\noutbid_volume=" + outbidVolume
				+ "\nrich_presence=" + richPresence
				+ "\nauto_reconnect=" + autoReconnect
				+ "\nauto_reconnect_attempts=" + autoReconnectAttempts
				+ "\nauto_reconnect_seconds=" + autoReconnectSeconds
				+ "\nexpress_connect=" + expressConnect
				+ "\nrich_presence_detail=" + rpCustomDetails;
	}

	public static void refreshConfig(){
		if(needsConfigRefresh) {
			refreshingConfig = true;
			try {
				if (!Files.deleteIfExists(FabricLoader.getInstance().getConfigDir().resolve("MineclubExpanded.properties"))) {
					LOGGER.error("Config file not found. Please ensure the path to the config is correct.\n" + FabricLoader.getInstance().getConfigDir().resolve("LoadingTimer.properties"));
				}
			} catch (IOException e) {
				// TODO Auto-generated catch block
				LOGGER.fatal("Config Refresh Failed due to a IOException, please report this on our issues thread.");
				e.printStackTrace();
			}
			configRegister();
			refreshingConfig = false;
		}
	}

	public static void onConfigSave(){
		needsConfigRefresh = true;
	}
}
