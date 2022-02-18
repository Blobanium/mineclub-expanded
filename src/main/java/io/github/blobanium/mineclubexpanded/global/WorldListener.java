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
    public static final String adminEventDetails = "Currently in an Admin Event";

    public static void listenWorld(){
        ClientWorld world = MinecraftClient.getInstance().world;
        if(MineclubExpanded.isOnMineclub() && world != null && !world.getRegistryKey().getValue().getPath().equals(worldName)) {
            worldName = world.getRegistryKey().getValue().getPath();
            MineclubExpanded.LOGGER.debug("WorldName=" + worldName);
            worldCheck();
        }
    }

    private static void worldCheck(){
        //Lobby, AFK Lounge, StaffHQ
        checkWorld(0, "overworld", "In The Lobby", DiscordRP.defaultDetails());

        //Main Games
        checkWorld(0, "gamemap_battle_dome", "Currently In Battle Dome", DiscordRP.defaultDetails());
        checkWorld(0, "gamemap_slime_walls", "Currently In Slime Walls", DiscordRP.defaultDetails());
        checkWorld(0, "gamemap_laser_tag", "Currently In Laser Tag", DiscordRP.defaultDetails());
        checkWorld(0, "gamemap_dodge_ball", "Currently In Dodge Ball", DiscordRP.defaultDetails());

        //Tabletop Games
        checkWorld(1, "connect4", "Playing with " + getTableTopUsername(), "Currently Playing Connect 4");
        checkWorld(1, "match5", "Playing with " + getTableTopUsername(), "Currently Playing Match 5");
        checkWorld(1, "luckyshot", "Playing with " + getTableTopUsername(), "Currently Playing Lucky Shot");
        checkWorld(1, "ttt", "Playing with " + getTableTopUsername(), "Currently Playing Tic Tac Toe");
        checkWorld(1, "sumo", "Playing with " + getTableTopUsername(), "Currently Playing Sumo");
        checkWorld(1, "tag", "Playing with " + getTableTopUsername(), "Currently Playing Tag");
        checkWorld(1, "snowball", "Playing with " + getTableTopUsername(), "Currently Playing Snowball Fight");
        checkWorld(1, "shoot", "Playing with " + getTableTopUsername(), "Playing Shoot The Sheep");

        //Admin Events
        checkWorld(0, "gamemap_admin_event_tnt_run", "Playing TNT Run", adminEventDetails);
        checkWorld(0, "gamemap_admin_event_spleef", "Playing Spleef", adminEventDetails);
        checkWorld(0, "gamemap_admin_event_brawl", "Playing Brawl", adminEventDetails);

        //Housing
        checkHousing();
    }

    //
    private static void checkWorld(int targetType, String targetWorldName, String state, String details){
        if(targetType == 0 && worldName.equals(targetWorldName)){
            sendPresence(state, details);
        }

        if(targetType == 1 && worldName.startsWith(targetWorldName)){
            sendPresence(state, details);
        }
    }

    private static void sendPresence(String state, String details){
        DiscordRP.updateStatus(state, details);
    }

    private static void checkHousing(){
        if(!cancelHousingUpdate) {
            if (worldName.startsWith("housing")) {
                isInHousing = true;
                if (FabricLoader.getInstance().isModLoaded("advancedchat")) {
                    DiscordRP.updateStatus("Currently In Housing", DiscordRP.defaultDetails());
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
