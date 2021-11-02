package io.github.blobanium.mineclubexpanded.games;

import io.github.blobanium.mineclubexpanded.util.config.ConfigReader;
import net.minecraft.client.MinecraftClient;
import net.minecraft.client.sound.SoundInstance;
import net.minecraft.text.Text;
import net.minecraft.util.Identifier;

public class AutoGG {
    public static void autoGg(SoundInstance sound){
        //NOT WORKING YET
        //Note: only works for Battle Dome And Dodge Ball
        if(ConfigReader.autogg) {
            if (sound.getId().toString().equals("minecraft:custom.mineclub.roundover-1")) {
                MinecraftClient.getInstance().player.sendChatMessage("gg");
            }
        }
    }
}
