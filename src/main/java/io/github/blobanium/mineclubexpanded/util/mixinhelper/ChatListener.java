package io.github.blobanium.mineclubexpanded.util.mixinhelper;

import io.github.blobanium.mineclubexpanded.games.tabletop.RichPresenceTabletopChatListener;
import io.github.blobanium.mineclubexpanded.global.WorldListener;
import io.github.blobanium.mineclubexpanded.housing.HousingRichPresenceTickTracker;
import io.github.blobanium.mineclubexpanded.market.OutbidNotifier;
import io.github.blobanium.mineclubexpanded.util.config.ConfigReader;
import net.minecraft.text.Text;

public class ChatListener {
    private static int tickmsg = 0;

    private static boolean chatBeingCleared(){
        if (tickmsg >= 5){
            return true;
        } else {
            return false;
        }
    }

    public static void onChatMessage(Text message){
        //Detect If A Chat message is being deleted on mineclub.
        tickmsg = tickmsg + 1;
        if(!chatBeingCleared()) {
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
        }
    }

    public static void onTick(){
        tickmsg = 0;
    }
}
