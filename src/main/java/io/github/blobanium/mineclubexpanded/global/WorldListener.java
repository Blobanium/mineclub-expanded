package io.github.blobanium.mineclubexpanded.global;

import io.github.blobanium.mineclubexpanded.MineclubExpanded;
import io.github.blobanium.mineclubexpanded.games.tabletop.RichPresenceTabletopChatListener;
import io.github.blobanium.mineclubexpanded.housing.HousingRichPresenceListener;
import io.github.blobanium.mineclubexpanded.util.discord.DiscordRP;
import net.fabricmc.loader.api.FabricLoader;
import net.minecraft.client.MinecraftClient;
import net.minecraft.client.world.ClientWorld;

public class WorldListener {
    private static String worldName;
    public static String housingName;

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
        if(world.equals("overworld")){
            DiscordRP.updateStatus("In The Lobby", "Playing On Mineclub");
        }

        //Battle Dome
        if(world.equals("gamemap_battle_dome")){
            DiscordRP.updateStatus("Currently In Battle Dome", "Playing On Mineclub");
        }

        //Slime Walls
        if(world.equals("gamemap_slime_walls")){
            DiscordRP.updateStatus("Currently In Slime Walls", "Playing On Mineclub");
        }

        //Speedtag
        if(world.startsWith("master")){
            DiscordRP.updateStatus("Currently Playing Speed Tag", "Playing On Mineclub");
        }

        //Laser Tag
        if(world.equals("gamemap_laser_tag")){
            DiscordRP.updateStatus("Currently In Laser Tag", "Playing On Mineclub");
        }

        //Dodgeball
        if(world.equals("gamemap_dodge_ball")){
            DiscordRP.updateStatus("Currently In Dodge Ball", "Playing On Mineclub");
        }

        //ANY Housing Map
        if(world.startsWith("housing")){
            if(FabricLoader.getInstance().isModLoaded("advancedchat")){
                DiscordRP.updateStatus("Currently In Housing", "Playing On Mineclub");
            } else {
                HousingRichPresenceListener.sendHousingPresence();
            }
        }

        //Connect4
        if(world.startsWith("connect4")){
            DiscordRP.updateStatus("Playing Connect 4 against " + RichPresenceTabletopChatListener.matchedUsername, "Playing On Mineclub");
        }

        //Match5
        if(world.startsWith("match5")){
            DiscordRP.updateStatus("Playing Match 5 against " + RichPresenceTabletopChatListener.matchedUsername, "Playing On Mineclub");
        }

        //Lucky Shot
        if(world.startsWith("luckyshot")){
            DiscordRP.updateStatus("Playing Lucky Shot against " + RichPresenceTabletopChatListener.matchedUsername, "Playing On Mineclub");
        }

        //Tic Tac Toe
        if(world.startsWith("ttt")){
            DiscordRP.updateStatus("Playing Tic Tac Toe against " + RichPresenceTabletopChatListener.matchedUsername, "Playing On Mineclub");
        }

        //Sumo
        if(world.startsWith("sumo")){
            DiscordRP.updateStatus("Playing Sumo against " + RichPresenceTabletopChatListener.matchedUsername, "Playing On Mineclub");
        }

        //Minesweep
        if(world.startsWith("ms")){
            DiscordRP.updateStatus("Playing Minesweep against " + RichPresenceTabletopChatListener.matchedUsername, "Playing On Mineclub");
        }
    }
}
