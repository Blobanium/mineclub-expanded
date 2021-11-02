package io.github.blobanium.mineclubexpanded.util.config;

import net.fabricmc.loader.api.FabricLoader;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.io.IOException;
import java.nio.file.Files;


public class ConfigReader {
	//variables
	public static boolean refreshingConfig = false;

	//configs
	public static boolean outbidNotification = false;
	public static boolean autogg = false;

    public static final Logger LOGGER = LogManager.getLogger("Mineclub Expanded");

    public static void configRegister(){
    	LOGGER.debug("Registering config..");
    	SimpleConfig CONFIG = SimpleConfig.of("MineclubExpanded").provider(namespace -> ConfigReader.ltProvider(namespace)).request();
    	final boolean outbidConfig = CONFIG.getOrDefault("outbid_notification", outbidNotification);
		final boolean autoggConfig = CONFIG.getOrDefault("auto_gg", autogg);

		if(outbidConfig){
			outbidNotification = true;
		}
		if(autoggConfig){
			autogg = true;
		}
    }

    private static String ltProvider(String filename) {
    	return "#Mineclub Expanded Config File."
    	+ "\noutbid_notification=" + outbidNotification
		+ "\nauto_gg=" + autogg;
    }


	public static void refreshConfig(){
		refreshingConfig = true;
		try {
			if(!Files.deleteIfExists(FabricLoader.getInstance().getConfigDir().resolve("MineclubExpanded.properties"))){
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
