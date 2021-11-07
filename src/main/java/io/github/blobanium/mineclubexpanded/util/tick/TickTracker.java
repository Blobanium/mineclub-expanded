package io.github.blobanium.mineclubexpanded.util.tick;

public class TickTracker {
    public static int tickNo = 0;
    private static int tickTarget;
    private static boolean hasNotified = false;

    public static void onTick(){
        tickNo = tickNo + 1;
        checkReminder();
    }

    public static void setReminder(int seconds){
        tickTarget = tickNo + (seconds * 20);
    }

    private static void checkReminder(){
        if(tickNo >= tickTarget){
            if(!hasNotified){
                //Coming soon: Housing Check Notifier
                hasNotified = true;
            }
        }
    }
}
