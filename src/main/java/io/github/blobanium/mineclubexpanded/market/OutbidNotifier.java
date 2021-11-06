package io.github.blobanium.mineclubexpanded.market;

import io.github.blobanium.mineclubexpanded.util.sound.SoundPlayer;
import net.minecraft.text.Text;

public class OutbidNotifier {
    private static float pitch;

    public static void onChatMessage(Text message){
        String textMessage = message.getString().replaceAll("\\[","\\\\[");
        if(textMessage.startsWith("ꌄ§8\\[§dMarket§8] §cYou have been outbid by")||textMessage.startsWith("ꌄ§8\\[§dHousing§8] §cYou have been outbid by")){
            if(textMessage.startsWith("ꌄ§8\\[§dHousing§8] §cYou have been outbid by")){
                pitch = 0.8F;
            } else {
                pitch = 1.0F;
            }

            SoundPlayer.playSound(pitch);
        }
    }
}
