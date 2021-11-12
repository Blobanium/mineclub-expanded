package io.github.blobanium.mineclubexpanded.housing;

import io.github.blobanium.mineclubexpanded.MineclubExpanded;
import io.github.blobanium.mineclubexpanded.global.WorldListener;
import io.github.blobanium.mineclubexpanded.util.discord.DiscordRP;
import net.minecraft.client.MinecraftClient;

public class HousingRichPresenceListener {
    public static void sendHousingPresence(){
        String message = MineclubExpanded.lastChatField;
        System.out.println(MineclubExpanded.lastChatField);
        if(message.startsWith("/home")) {
            String finalMessage = message.replaceAll("/home", "");
            if(finalMessage.equals("")){
                DiscordRP.updateStatus("Currently In " + MinecraftClient.getInstance().getSession().getUsername() + "'s Home", "Playing On Mineclub");
            }else {
                DiscordRP.updateStatus("Currently In " + finalMessage + "'s Home", "Playing On Mineclub");
            }
        } else {
            DiscordRP.updateStatus("Currently In Housing", "Playing On Mineclub");
        }
        MineclubExpanded.lastChatField = "";
    }
}
