package io.github.blobanium.mineclubexpanded.global;

import io.github.blobanium.mineclubexpanded.MineclubExpanded;
import io.github.blobanium.mineclubexpanded.util.discord.DiscordRP;
import net.minecraft.client.MinecraftClient;

public class WorldListener {
    private static String worldName;

    public static void listenWorld(){
        if(MineclubExpanded.isOnMineclub()) {
            try {
                if (!MinecraftClient.getInstance().world.getRegistryKey().getValue().getPath().equals(worldName)) {
                    worldName = MinecraftClient.getInstance().world.getRegistryKey().getValue().getPath();
                    System.out.println(worldName);
                    worldCheck(worldName);
                }
            } catch (NullPointerException e) {
                //Supress NullPointerException
            }
        }
    }

    private static void worldCheck(String world){
        if(world.equals("overworld")){
            DiscordRP.updateStatus("In The Lobby", "Playing On Mineclub");
        }

        if(world.equals("gamemap_battle_dome")){
            DiscordRP.updateStatus("Currently In Battle Dome", "Playing On Mineclub");
        }

        if(world.equals("gamemap_slime_walls")){
            DiscordRP.updateStatus("Currently In Slime Walls", "Playing On Mineclub");
        }

        if(world.equals("gamemap_laser_tag")){
            DiscordRP.updateStatus("Currently In Laser Tag", "Playing On Mineclub");
        }

        if(world.equals("gamemap_dodge_ball")){
            DiscordRP.updateStatus("Currently In Dodge Ball", "Playing On Mineclub");
        }

        if(world.startsWith("housing")){
            DiscordRP.updateStatus("Currently In Housing", "Playing On Mineclub");
        }
    }
}
