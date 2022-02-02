package io.github.blobanium.mineclubexpanded.housing;

import io.github.blobanium.mineclubexpanded.MineclubExpanded;
import io.github.blobanium.mineclubexpanded.util.discord.DiscordRP;
import net.minecraft.client.MinecraftClient;
import net.minecraft.text.Text;

public class HousingRichPresenceListener {
    public static String playerheadName;
    public static String usernameFromServerIP;

    public static void sendHousingPresence(boolean fromServerIP){
        if(fromServerIP){
            DiscordRP.updateStatus("Currently In " + usernameFromServerIP + "'s Home", DiscordRP.defaultDetails());
            checkBlobaniumsHome(usernameFromServerIP);
        } else {
            String message = MineclubExpanded.lastChatField;
            if (message.startsWith("/home")) {
                String finalMessage = message.replaceAll("/home", "");
                if (finalMessage.equals("")) {
                    DiscordRP.updateStatus("Currently In " + MinecraftClient.getInstance().getSession().getUsername() + "'s Home", DiscordRP.defaultDetails());
                } else {
                    DiscordRP.updateStatus("Currently In " + finalMessage + "'s Home", DiscordRP.defaultDetails());
                    checkBlobaniumsHome(finalMessage);
                }
            } else if (playerheadName != null) {
                DiscordRP.updateStatus("Currently In " + playerheadName + "'s Home", DiscordRP.defaultDetails());
                checkBlobaniumsHome(playerheadName);
            } else {
                DiscordRP.updateStatus("Currently In Housing", DiscordRP.defaultDetails());
            }
            MineclubExpanded.lastChatField = "";
        }
    }

    private static void checkBlobaniumsHome(String username){
        if(username.matches("(B|b)lobanium")){
            MinecraftClient.getInstance().player.sendMessage(Text.Serializer.fromJson("[\"\",{\"text\":\"Welcome to my house! Do you like my housing? \"},{\"text\":\"Click here to like!\",\"color\":\"light_purple\",\"clickEvent\":{\"action\":\"run_command\",\"value\":\"/like\"}}]"), false);
        }
    }
}
