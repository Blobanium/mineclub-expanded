package io.github.blobanium.mineclubexpanded.housing;

import io.github.blobanium.mineclubexpanded.MineclubExpanded;
import io.github.blobanium.mineclubexpanded.util.discord.DiscordRP;
import net.minecraft.client.MinecraftClient;

public class HousingRichPresenceListener {
    public static String playerheadName;

    public static void sendHousingPresence(){
        String message = MineclubExpanded.lastChatField;
        if(message.startsWith("/home")) {
            String finalMessage = message.replaceAll("/home", "");
            if (finalMessage.equals("")) {
                DiscordRP.updateStatus("Currently In " + MinecraftClient.getInstance().getSession().getUsername() + "'s Home", DiscordRP.defaultDetails);
            } else {
                DiscordRP.updateStatus("Currently In " + finalMessage + "'s Home", DiscordRP.defaultDetails);
            }
        } else if(playerheadName != null){
            DiscordRP.updateStatus("Currently In " + playerheadName + "'s Home", DiscordRP.defaultDetails);
        } else {
            DiscordRP.updateStatus("Currently In Housing", DiscordRP.defaultDetails);
        }
        MineclubExpanded.lastChatField = "";
    }
}
