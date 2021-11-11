package io.github.blobanium.mineclubexpanded.games.tabletop;

import net.minecraft.text.Text;

public class RichPresenceTabletopChatListener {
    public static String matchedUsername;

    public static void onChatMessage(Text message){
        String textMessage = message.getString().replaceAll("\\[","\\\\[");
        if(textMessage.startsWith("ꌄ§8\\[§dGames§8] §8Matched with player")){
            matchedUsername = textMessage.substring(44);
        }
    }
}
