package io.github.blobanium.mineclubexpanded.games;

import net.minecraft.client.MinecraftClient;
import net.minecraft.text.Text;

public class AutoGG {
    public static void autoGg(Text message){
        //NOT WORKING YET

        //Note: only works for Battle Dome And Dodge Ball
        String textMessage = message.getString();
        if(textMessage.startsWith("ꌄ醣 §c4th Place:")||textMessage.startsWith("ꌄ驰 §c4th Place:")){

            //MinecraftClient.getInstance().player.sendChatMessage("gg");
        }
    }
}
