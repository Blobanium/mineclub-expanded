package io.github.blobanium.mineclubexpanded.util.tick;

import io.github.blobanium.mineclubexpanded.MineclubExpanded;
import io.github.blobanium.mineclubexpanded.global.WorldListener;
import io.github.blobanium.mineclubexpanded.housing.HousingRichPresenceListener;
import net.minecraft.client.MinecraftClient;

public class TickTracker {
    public static int tickNo = 0;
    private static int tickTarget;
    private static boolean hasNotified = true;
    public static boolean cancelHousingUpdate;

    public static void onTick(){
        tickNo = tickNo + 1;
        checkReminder();
        WorldListener.listenWorld();
    }

    public static void setReminder(int seconds){
        tickTarget = tickNo + (seconds * 20);
        hasNotified = false;
    }

    private static void checkReminder(){
        if(tickNo >= tickTarget){
            if(!hasNotified){
                if (WorldListener.worldName.equals(MinecraftClient.getInstance().world.getRegistryKey().getValue().getPath())) {
                    if(cancelHousingUpdate) {
                        MineclubExpanded.LOGGER.debug("Canceling Housing Update");
                    } else {
                        HousingRichPresenceListener.sendHousingPresence();
                    }
                }
                cancelHousingUpdate = false;
                hasNotified = true;
            }
        }
    }
}
