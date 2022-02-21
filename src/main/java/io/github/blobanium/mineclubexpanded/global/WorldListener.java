package io.github.blobanium.mineclubexpanded.global;

import io.github.blobanium.mineclubexpanded.MineclubExpanded;
import io.github.blobanium.mineclubexpanded.games.tabletop.RichPresenceTabletopChatListener;
import io.github.blobanium.mineclubexpanded.housing.HousingRichPresenceListener;
import io.github.blobanium.mineclubexpanded.util.discord.DiscordRP;
import net.fabricmc.loader.api.FabricLoader;
import net.minecraft.client.MinecraftClient;
import net.minecraft.client.world.ClientWorld;

public class WorldListener {
    public static String worldName;
    public static boolean isInHousing = false;
    public static boolean cancelHousingUpdate = false;
    public static boolean connectUsingHousingIP = false;

    public static void listenWorld(){
        ClientWorld world = MinecraftClient.getInstance().world;
        if(MineclubExpanded.isOnMineclub() && world != null && !world.getRegistryKey().getValue().getPath().equals(worldName)) {
            worldName = world.getRegistryKey().getValue().getPath();
            MineclubExpanded.LOGGER.debug("WorldName=" + worldName);
            WorldID.updateWorldID();
            worldCheck();
        }
    }

    private static void worldCheck(){

        WorldID.sendPresence();
        checkHousing();
    }

    //


    private static void checkHousing(){
        if(!cancelHousingUpdate) {
            if (worldName.startsWith("housing")) {
                isInHousing = true;
                if (FabricLoader.getInstance().isModLoaded("advancedchat")) {
                    DiscordRP.updateStatus("Currently In Housing", DiscordRP.Defaults.defaultDetails());
                } else {
                    HousingRichPresenceListener.sendHousingPresence(connectUsingHousingIP);
                    if(connectUsingHousingIP){
                        connectUsingHousingIP = false;
                    }
                }
            } else {
                isInHousing = false;
            }
        } else {
            cancelHousingUpdate = false;
        }
    }
    
    private static String getTableTopUsername(){
        return  RichPresenceTabletopChatListener.matchedUsername;
    }
}
