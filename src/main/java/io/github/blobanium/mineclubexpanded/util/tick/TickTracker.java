package io.github.blobanium.mineclubexpanded.util.tick;

import io.github.blobanium.mineclubexpanded.global.WorldListener;

public class TickTracker {
    public static int tickNo = 0;
    private static int tickTarget;
    private static boolean hasNotified = false;

    public static void onTick(){
        tickNo = tickNo + 1;
        checkReminder();
        WorldListener.listenWorld();
    }

    public static void setReminder(int seconds){
        tickTarget = tickNo + (seconds * 20);
    }

    private static void checkReminder(){
        if(tickNo >= tickTarget){
            if(!hasNotified){

                hasNotified = true;
            }
        }
    }
}
