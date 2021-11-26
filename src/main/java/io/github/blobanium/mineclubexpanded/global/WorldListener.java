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
    public static boolean isAlreadyInStaffHQ = false;

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
        //Lobby, AFK Lounge
        checkWorld(0, world, "overworld", "In The Lobby", "Playing On Mineclub");

        //Main Games
        checkWorld(0, world, "gamemap_battle_dome", "Currently In Battle Dome", "Playing On Mineclub");
        checkWorld(0, world, "gamemap_slime_walls", "Currently In Slime Walls", "Playing On Mineclub");
        checkWorld(1, world, "master", "Currently Playing Speed Tag", "Playing On Mineclub");
        checkWorld(0, world, "gamemap_laser_tag", "Currently In Laser Tag", "Playing On Mineclub");
        checkWorld(0, world, "gamemap_dodge_ball", "Currently In Dodge Ball", "Playing On Mineclub");

        //Tabletop Games
        checkWorld(1, world, "connect4", "Playing with " + RichPresenceTabletopChatListener.matchedUsername, "Currently Playing Connect 4");
        checkWorld(1, world, "match5", "Playing with " + RichPresenceTabletopChatListener.matchedUsername, "Currently Playing Match 5");
        checkWorld(1, world, "luckyshot", "Playing with " + RichPresenceTabletopChatListener.matchedUsername, "Currently Playing Lucky Shot");
        checkWorld(1, world, "ttt", "Playing with " + RichPresenceTabletopChatListener.matchedUsername, "Currently Playing Tic Tac Toe");
        checkWorld(1, world, "sumo", "Playing with " + RichPresenceTabletopChatListener.matchedUsername, "Currently Playing Sumo");
        checkWorld(1, world, "ms", "Playing with " + RichPresenceTabletopChatListener.matchedUsername, "Currently Playing Minesweep");
        checkWorld(1, world, "snowball", "Playing with " + RichPresenceTabletopChatListener.matchedUsername, "Currently Playing Snowball Fight");

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
        isAlreadyInStaffHQ = false;
    }

    private static void checkHousing(String world){
        if(!cancelHousingUpdate) {
            if (world.startsWith("housing")) {
                isInHousing = true;
                if (FabricLoader.getInstance().isModLoaded("advancedchat")) {
                    DiscordRP.updateStatus("Currently In Housing", "Playing On Mineclub");
                } else {
                    HousingRichPresenceListener.sendHousingPresence();
                }
            } else {
                isInHousing = false;
            }
        } else {
            cancelHousingUpdate = false;
        }
    }
}
