package io.github.blobanium.mineclubexpanded.util.mixinhelper;

import io.github.blobanium.mineclubexpanded.games.tabletop.RichPresenceTabletopChatListener;
import io.github.blobanium.mineclubexpanded.global.WorldListener;
import io.github.blobanium.mineclubexpanded.housing.HousingRichPresenceTickTracker;
import io.github.blobanium.mineclubexpanded.market.OutbidNotifier;
import io.github.blobanium.mineclubexpanded.util.config.ConfigReader;
import io.github.blobanium.mineclubexpanded.util.discord.DiscordRP;
import net.minecraft.text.Text;

public class ChatListener {
    private static String lastMessage;
    private static String lastMessage2;
    private static Integer msgCount = 0;
    private static boolean chatBeingCleared = false;

    public static void onChatMessage(Text message){
        //Detect If A Chat message is being deleted on mineclub.
        if(message.getString().matches("ꌄ§7")){
            msgCount = msgCount + 1;
            if(msgCount >= 80){
                chatBeingCleared = true;
            }
        } else if(!chatBeingCleared) {
            lastMessage2 = lastMessage;
            lastMessage = message.getString();
            //Broadcast On Chat Message
            onMessage(message);
        } else {
            if(lastMessage.equals(message.getString())||lastMessage2.equals(message.getString())){
                chatBeingCleared = false;
            }
        }
    }

    private static void onMessage(Text message){
        if(ConfigReader.outbidNotification) {
            OutbidNotifier.onChatMessage(message);
        }

        if(ConfigReader.richPresence){
            RichPresenceTabletopChatListener.onChatMessage(message);

            if(WorldListener.isInHousing){
                if(message.getString().startsWith("ꌄ冈 No player found by name")){
                    HousingRichPresenceTickTracker.cancelHousingUpdate = true;
                }
            }

            if(message.getString().startsWith("ꌄ咀") && !WorldListener.isAlreadyInStaffHQ){
                DiscordRP.updateStatus("Currently in Staff HQ",DiscordRP.defaultDetails);
                WorldListener.isAlreadyInStaffHQ = true;
            }

            if(message.getString().startsWith("ꌄ骐") && WorldListener.isAlreadyInStaffHQ){
                DiscordRP.updateStatus("In The Lobby",DiscordRP.defaultDetails);
                WorldListener.isAlreadyInStaffHQ = true;
            }
        }
    }
}
