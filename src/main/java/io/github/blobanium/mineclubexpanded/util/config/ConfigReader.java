package io.github.blobanium.mineclubexpanded.util.config;

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
	public static int debugAsyncTickThreads = 2;
	public static boolean hideSerialID = false;
	public static String serialColor = "";
	public static boolean showDigits = false;
	public static String overrideKey = "";
	public static boolean hidemcBetsData = false;

    public static final Logger LOGGER = LogManager.getLogger("Mineclub Expanded");

    public static void configRegister(boolean initialize){
    	LOGGER.debug("Registering config..");
    	SimpleConfig CONFIG = SimpleConfig.of("MineclubExpanded").provider(ConfigReader::ltProvider).request();

		if(initialize) {
			outbidNotification = CONFIG.getOrDefault("outbid_notification", outbidNotification);
			autogg = CONFIG.getOrDefault("auto_gg", autogg);
			outbidVolume = CONFIG.getOrDefault("outbid_volume", outbidVolume);
			richPresence = CONFIG.getOrDefault("rich_presence", richPresence);
			autoReconnect = CONFIG.getOrDefault("auto_reconnect", autoReconnect);
			autoReconnectAttempts = CONFIG.getOrDefault("auto_reconnect_attempts", autoReconnectAttempts);
			autoReconnectSeconds = CONFIG.getOrDefault("auto_reconnect_seconds", autoReconnectSeconds);
			expressConnect = CONFIG.getOrDefault("express_connect", expressConnect);
			rpCustomDetails = CONFIG.getOrDefault("rich_presence_detail", rpCustomDetails);
			debugAsyncTicks = CONFIG.getOrDefault("debug_async_ticks", debugAsyncTicks);
			debugAsyncTickThreads = CONFIG.getOrDefault("debug_async_tick_threads", debugAsyncTickThreads);
			hideSerialID = CONFIG.getOrDefault("hide_serial_id", hideSerialID);
			serialColor = CONFIG.getOrDefault("serial_color", serialColor);
			showDigits = CONFIG.getOrDefault("serial_id_digits", showDigits);
			overrideKey = CONFIG.getOrDefault("debug_override_key", overrideKey);
			hidemcBetsData = CONFIG.getOrDefault("hide_mcbets_data", hidemcBetsData);
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
				+ "\nrich_presence_detail=" + rpCustomDetails
				+ "\ndebug_async_ticks=" + debugAsyncTicks
				+ "\ndebug_async_tick_threads=" + debugAsyncTickThreads
				+ "\nhide_serial_id=" + hideSerialID
				+ "\nserial_color=" + serialColor
				+ "\nserial_id_digits=" + showDigits
				+ "\ndebug_override_key=" + overrideKey
				+ "\nhide_mcbets_data=" + hidemcBetsData;
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
			configRegister(false);
			refreshingConfig = false;
		}
	}

	public static void onConfigSave(){
		needsConfigRefresh = true;
	}
}
