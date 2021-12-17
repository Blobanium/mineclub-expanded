package io.github.blobanium.mineclubexpanded.util.feature;

import io.github.blobanium.mineclubexpanded.MineclubExpanded;
import io.github.blobanium.mineclubexpanded.util.tick.TickTracker;
import net.minecraft.client.MinecraftClient;
import net.minecraft.client.gui.screen.ConnectScreen;
import net.minecraft.client.gui.screen.Screen;
import net.minecraft.client.network.ServerAddress;
import net.minecraft.client.network.ServerInfo;
import org.apache.commons.lang3.ObjectUtils;

public class Autoreconnect {
    private static int tickTarget;
    private static boolean hasNotified = true;
    private static Screen lastScreen;
    public static boolean cancelAutoReconnect = true;

    public static void setReminder(int seconds, Screen screen){
        lastScreen = screen;
        tickTarget = TickTracker.tickNo + (seconds * 20);
        cancelAutoReconnect = false;
        hasNotified = false;
    }

    public static void checkReminder(){
        if(TickTracker.tickNo >= tickTarget && !hasNotified){
            if(!cancelAutoReconnect) {
                ConnectScreen.connect(lastScreen, MinecraftClient.getInstance(), new ServerAddress("play.mineclub.com", 25565), MineclubExpanded.mineclub);
            }
            hasNotified = true;
        }
    }
}
