package io.github.blobanium.mineclubexpanded.housing;

import io.github.blobanium.mineclubexpanded.MineclubExpanded;
import io.github.blobanium.mineclubexpanded.global.WorldListener;

public class HousingRichPresenceListener {


    public static void onChatReturn(){
        String message = MineclubExpanded.lastChatField;
        System.out.println(MineclubExpanded.lastChatField);
        if(message.startsWith("/home")) {
           String finalMessage = message.replaceAll("/home", "");
            System.out.println(message);
            WorldListener.housingName = finalMessage;
        }
    }
}
