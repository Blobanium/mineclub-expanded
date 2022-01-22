package io.github.blobanium.mineclubexpanded.housing;

import io.github.blobanium.mineclubexpanded.MineclubExpanded;
import io.github.blobanium.mineclubexpanded.global.WorldListener;
import io.github.blobanium.mineclubexpanded.util.tick.TickTracker;
import net.minecraft.client.MinecraftClient;

public class HousingRichPresenceTickTracker{
    public static boolean cancelHousingUpdate = false;
    private static int tickTarget;
    private static boolean hasNotified = true;

    public static void setReminder(int seconds){
        tickTarget = TickTracker.tickNo + (seconds * 20);
        hasNotified = false;
    }

    public static void checkReminder(){
        if(TickTracker.tickNo >= tickTarget && !hasNotified){
            if (WorldListener.worldName.equals(MinecraftClient.getInstance().world.getRegistryKey().getValue().getPath())) {
                if(cancelHousingUpdate) {
                    MineclubExpanded.LOGGER.debug("Canceling Housing Update");
                } else {
                    HousingRichPresenceListener.sendHousingPresence(false);
                }
            }
            cancelHousingUpdate = false;
            hasNotified = true;
        }
    }
}
