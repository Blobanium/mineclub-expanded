package io.github.blobanium.mineclubexpanded.util.mixinhelper;

import io.github.blobanium.mineclubexpanded.MineclubExpanded;
import io.github.blobanium.mineclubexpanded.games.tabletop.RichPresenceTabletopChatListener;
import io.github.blobanium.mineclubexpanded.global.WorldListener;
import io.github.blobanium.mineclubexpanded.housing.HousingRichPresenceTickTracker;
import io.github.blobanium.mineclubexpanded.market.OutbidNotifier;
import io.github.blobanium.mineclubexpanded.util.config.ConfigReader;
import io.github.blobanium.mineclubexpanded.util.discord.DiscordRP;
import net.minecraft.text.Text;

public class ChatListener {
    private static boolean chatBeingCleared = false;

    private static int msgsLastTick = 0;
    private static int tickmsg = 0;

    public static void onChatMessage(Text message){
        //Detect If A Chat message is being deleted on mineclub.
        tickmsg = tickmsg + 1;
        if(!chatBeingCleared) {
            onMessage(message);
        }
    }

    private static void onMessage(Text message){
        if(ConfigReader.outbidNotification) {
            OutbidNotifier.onChatMessage(message);
        }

        if(ConfigReader.richPresence){
            RichPresenceTabletopChatListener.onChatMessage(message);

            if(WorldListener.isInHousing && message.getString().startsWith("ꌄ冈 No player found by name")){
                    HousingRichPresenceTickTracker.cancelHousingUpdate = true;
            }

            if(message.getString().startsWith("ꌄ咀") && !WorldListener.isAlreadyInStaffHQ){
                staffHQRPUpdate("Currently in Staff HQ");
            }

            if(message.getString().startsWith("ꌄ骐") && WorldListener.isAlreadyInStaffHQ){
                staffHQRPUpdate("In The Lobby");
            }
        }
    }

    public static void onTick(){
        msgsLastTick = tickmsg;
        tickmsg = 0;

        if(msgsLastTick > 5){
            MineclubExpanded.LOGGER.info("Chat Being Cleared (" + msgsLastTick + "msgs last tick)");
            chatBeingCleared = true;
        } else {
            chatBeingCleared = false;
        }
    }

    private static void staffHQRPUpdate(String state){
        DiscordRP.updateStatus(state,DiscordRP.defaultDetails);
        WorldListener.isAlreadyInStaffHQ = true;
    }
}
