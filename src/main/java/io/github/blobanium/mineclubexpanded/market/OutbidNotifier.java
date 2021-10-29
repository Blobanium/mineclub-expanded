package io.github.blobanium.mineclubexpanded.market;

import io.github.blobanium.mineclubexpanded.util.SoundPlayer;
import net.minecraft.client.MinecraftClient;
import net.minecraft.client.sound.SoundInstance;
import net.minecraft.client.toast.SystemToast;
import net.minecraft.text.LiteralText;
import net.minecraft.text.Text;

public class OutbidNotifier {
    public static void onChatMessage(Text message){
        String textMessage = message.getString().replaceAll("\\[","\\\\[");
        if(textMessage.startsWith("ꌄ§8\\[§dMarket§8] §cYou have been outbid by")){
            SoundPlayer.playSound();
        }
    }
}
