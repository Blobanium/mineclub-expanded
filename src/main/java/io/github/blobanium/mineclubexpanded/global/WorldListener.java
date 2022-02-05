package io.github.blobanium.mineclubexpanded.global;

import io.github.blobanium.mineclubexpanded.MineclubExpanded;
import io.github.blobanium.mineclubexpanded.games.tabletop.RichPresenceTabletopChatListener;
import io.github.blobanium.mineclubexpanded.housing.HousingRichPresenceListener;
import io.github.blobanium.mineclubexpanded.util.discord.DiscordRP;
import net.fabricmc.loader.api.FabricLoader;
import net.minecraft.client.MinecraftClient;

public class WorldListener {
    public static String worldName;
    public static boolean isInHousing = false;
    public static boolean cancelHousingUpdate = false;
    public static boolean connectUsingHousingIP = false;
    public static final String adminEventDetails = "Currently in an Admin Event";

    public static void listenWorld(){
        if(MineclubExpanded.isOnMineclub()) {
            try {
                if (!MinecraftClient.getInstance().world.getRegistryKey().getValue().getPath().equals(worldName)) {
                    worldName = MinecraftClient.getInstance().world.getRegistryKey().getValue().getPath();
                    MineclubExpanded.LOGGER.debug("WorldName=" + worldName);
                    worldCheck(worldName);
                }
            } catch (NullPointerException e) {
                //Supress NullPointerException
            }
        }
    }

    private static void worldCheck(String world){
        //Lobby, AFK Lounge, StaffHQ
        checkWorld(0, world, "overworld", "In The Lobby", DiscordRP.defaultDetails());

        //Main Games
        checkWorld(0, world, "gamemap_battle_dome", "Currently In Battle Dome", DiscordRP.defaultDetails());
        checkWorld(0, world, "gamemap_slime_walls", "Currently In Slime Walls", DiscordRP.defaultDetails());
        checkWorld(0, world, "gamemap_laser_tag", "Currently In Laser Tag", DiscordRP.defaultDetails());
        checkWorld(0, world, "gamemap_dodge_ball", "Currently In Dodge Ball", DiscordRP.defaultDetails());

        //Tabletop Games
        checkWorld(1, world, "connect4", "Playing with " + RichPresenceTabletopChatListener.matchedUsername, "Currently Playing Connect 4");
        checkWorld(1, world, "match5", "Playing with " + RichPresenceTabletopChatListener.matchedUsername, "Currently Playing Match 5");
        checkWorld(1, world, "luckyshot", "Playing with " + RichPresenceTabletopChatListener.matchedUsername, "Currently Playing Lucky Shot");
        checkWorld(1, world, "ttt", "Playing with " + RichPresenceTabletopChatListener.matchedUsername, "Currently Playing Tic Tac Toe");
        checkWorld(1, world, "sumo", "Playing with " + RichPresenceTabletopChatListener.matchedUsername, "Currently Playing Sumo");
        checkWorld(1, world, "ms", "Playing with " + RichPresenceTabletopChatListener.matchedUsername, "Currently Playing Minesweep");
        checkWorld(1, world, "snowball", "Playing with " + RichPresenceTabletopChatListener.matchedUsername, "Currently Playing Snowball Fight");
        checkWorld(1, world, "shoot", "Playing with " + RichPresenceTabletopChatListener.matchedUsername, "Playing Shoot The Sheep");

        //Admin Events
        checkWorld(0, world, "gamemap_admin_event_tnt_run", "Playing TNT Run", adminEventDetails);
        checkWorld(0, world, "gamemap_admin_event_spleef", "Playing Spleef", adminEventDetails);

        //Housing
        checkHousing(world);
    }

    //
    private static void checkWorld(int targetType, String world, String targetWorldName, String state, String details){
        if(targetType == 0){
            if(world.equals(targetWorldName)){
                sendPresence(state, details);
            }
        }

        if(targetType == 1){
            if(world.startsWith(targetWorldName)){
                sendPresence(state, details);
            }
        }
    }

    private static void sendPresence(String state, String details){
        DiscordRP.updateStatus(state, details);
    }

    private static void checkHousing(String world){
        if(!cancelHousingUpdate) {
            if (world.startsWith("housing")) {
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
}
