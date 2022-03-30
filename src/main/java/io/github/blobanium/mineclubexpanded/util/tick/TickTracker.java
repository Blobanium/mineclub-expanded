package io.github.blobanium.mineclubexpanded.util.tick;

import io.github.blobanium.mineclubexpanded.games.adminevent.AdminEventNotifier;
import io.github.blobanium.mineclubexpanded.global.WorldListener;
import io.github.blobanium.mineclubexpanded.housing.HousingRichPresenceTickTracker;
import io.github.blobanium.mineclubexpanded.util.config.ConfigReader;
import io.github.blobanium.mineclubexpanded.util.feature.Autoreconnect;
import io.github.blobanium.mineclubexpanded.util.mixinhelper.ChatListener;

import java.util.concurrent.ForkJoinPool;
import java.util.concurrent.ForkJoinTask;

public class TickTracker{
    public static int tickNo = 0;
    public static ForkJoinPool tickProcessor = new ForkJoinPool(ConfigReader.debugAsyncTickThreads);

    private static Runnable runTick(){
        return () -> {
            tick();
        };
    }

    public static void onTick(){
        if(ConfigReader.debugAsyncTicks){
            tickProcessor.execute(ForkJoinTask.adapt(runTick()));
        } else {
            tick();
        }
    }

    private static void tick(){
        tickNo = tickNo + 1;
        checkReminder();
        WorldListener.listenWorld();
        ChatListener.onTick();
        AdminEventNotifier.checkEvent();
    }

    private static void checkReminder(){
        HousingRichPresenceTickTracker.checkReminder();
        Autoreconnect.checkReminder();
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


