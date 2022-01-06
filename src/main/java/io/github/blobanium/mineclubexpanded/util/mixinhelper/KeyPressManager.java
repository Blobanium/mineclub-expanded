package io.github.blobanium.mineclubexpanded.util.mixinhelper;

import io.github.blobanium.mineclubexpanded.MineclubExpanded;
import io.github.blobanium.mineclubexpanded.games.AutoGG;
import io.github.blobanium.mineclubexpanded.global.WorldListener;
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

        if(key == 257 && MineclubExpanded.lastChatField != null){
            onReturn();
        }
    }

    private static void onReturn(){
        if(MineclubExpanded.lastChatField.startsWith("/home") && WorldListener.isInHousing){
                HousingRichPresenceTickTracker.setReminder(3);
        }

        if(MineclubExpanded.lastChatField.startsWith("/spectate")){
            AutoGG.isSpectatorMode = true;
        }
    }

}
