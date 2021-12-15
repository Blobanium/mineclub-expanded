package io.github.blobanium.mineclubexpanded.util.mixinhelper;

import io.github.blobanium.mineclubexpanded.MineclubExpanded;
import io.github.blobanium.mineclubexpanded.global.WorldListener;
import io.github.blobanium.mineclubexpanded.housing.HousingRichPresenceListener;
import io.github.blobanium.mineclubexpanded.housing.HousingRichPresenceTickTracker;
import io.github.blobanium.mineclubexpanded.util.feature.Autoreconnect;

public class KeyPressManager {
    public static void keyPress(int key){
        //TODO Figure out keypresses
        //ESC = 256, Enter/Return = 257
        if(key == 256){
            MineclubExpanded.isChatOpen = false;
            Autoreconnect.cancelAutoReconnect = true;
        }

        if(key == 257){
            if(WorldListener.isInHousing){
                if(HousingRichPresenceListener.lastChatField.startsWith("/home")) {
                    HousingRichPresenceTickTracker.setReminder(3);
                }
            }
        }
    }
}
