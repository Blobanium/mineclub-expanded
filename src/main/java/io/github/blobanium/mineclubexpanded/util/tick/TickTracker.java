package io.github.blobanium.mineclubexpanded.util.tick;

import io.github.blobanium.mineclubexpanded.MineclubExpanded;
import io.github.blobanium.mineclubexpanded.global.WorldListener;
import io.github.blobanium.mineclubexpanded.housing.HousingRichPresenceListener;
import io.github.blobanium.mineclubexpanded.housing.HousingRichPresenceTickTracker;
import net.minecraft.client.MinecraftClient;

public class TickTracker{
    public static int tickNo = 0;

    public static void onTick(){
        tickNo = tickNo + 1;
        checkReminder();
        WorldListener.listenWorld();
    }

    private static void checkReminder(){
        HousingRichPresenceTickTracker.checkReminder();
    }

    //public static class TickTrackerExample{
    //    private static int tickTarget;
    //    private static boolean hasNotified = true;

    //    public static void setReminder(int seconds){
    //        tickTarget = TickTracker.tickNo + (seconds * 20);
    //        hasNotified = false;
    //    }

    //    public static void checkReminder(){
    //        if(TickTracker.tickNo >= tickTarget && !hasNotified){
    //            //Resulting code goes here
    //            hasNotified = true;
    //        }
    //    }
    //}
}


