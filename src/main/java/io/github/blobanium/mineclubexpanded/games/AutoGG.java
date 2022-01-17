package io.github.blobanium.mineclubexpanded.games;

import io.github.blobanium.mineclubexpanded.util.config.ConfigReader;
import net.minecraft.client.MinecraftClient;
import net.minecraft.client.sound.SoundInstance;
import net.minecraft.item.Item;

public class AutoGG {
    public static boolean isSpectatorMode(){
        if (MinecraftClient.getInstance().player != null) {
            return Item.getRawId(MinecraftClient.getInstance().player.getInventory().main.get(4).getItem()) == 795;
        }
        return false;
    }

    public static void autoGg(SoundInstance sound){
        if(!isSpectatorMode() && ConfigReader.autogg && sound.getId().toString().equals("minecraft:custom.mineclub.roundover-1")){
            MinecraftClient.getInstance().player.sendChatMessage("gg");
        }
    }
}
