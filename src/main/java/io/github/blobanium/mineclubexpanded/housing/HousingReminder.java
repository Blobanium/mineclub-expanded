package io.github.blobanium.mineclubexpanded.housing;

import io.github.blobanium.mineclubexpanded.util.tick.TickTracker;

public class HousingReminder {
    public static void onHousingCheck(){
        //TODO Tell that the user has checked the homes.
        TickTracker.setReminder(3600);
    }

    public static void onReminder(){
        //TODO Notify the user that it is time to check the homes.
    }
}
