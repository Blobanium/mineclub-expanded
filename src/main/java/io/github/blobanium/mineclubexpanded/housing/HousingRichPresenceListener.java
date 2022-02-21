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
            DiscordRP.updateStatus(rpHousingState(usernameFromServerIP), DiscordRP.Defaults.defaultDetails());
        } else {
            String message = MineclubExpanded.lastChatField;
            if (message.startsWith("/home")) {
                String finalMessage = message.replaceAll("/home", "");
                if (finalMessage.equals("")) {
                    DiscordRP.updateStatus(rpHousingState(MinecraftClient.getInstance().getSession().getUsername()), DiscordRP.Defaults.defaultDetails());
                } else {
                    DiscordRP.updateStatus(rpHousingState(finalMessage), DiscordRP.Defaults.defaultDetails());
                }
            } else if (playerheadName != null) {
                DiscordRP.updateStatus(rpHousingState(playerheadName), DiscordRP.Defaults.defaultDetails());
            } else {
                DiscordRP.updateStatus("Currently In Housing", DiscordRP.Defaults.defaultDetails());
            }
            MineclubExpanded.lastChatField = "";
        }
    }

    private static String rpHousingState(String username){
        if(username.matches("(B|b)lobanium")){
            MinecraftClient.getInstance().player.sendMessage(Text.Serializer.fromJson("[\"\",{\"text\":\"Welcome to my house! Do you like my housing? \"},{\"text\":\"Click here to like!\",\"color\":\"light_purple\",\"clickEvent\":{\"action\":\"run_command\",\"value\":\"/like\"}}]"), false);
        }
        return "Currently In " + username + "'s Home";
    }
}
