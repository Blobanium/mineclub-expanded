package io.github.blobanium.mineclubexpanded.util.config;

import io.github.blobanium.mineclubexpanded.util.discord.DiscordRP;
import net.minecraft.text.Text;
import net.minecraft.text.TranslatableText;



public class DynamicModMenuTranslatable {
    private static final int NORMAL = 0;
    private static final int EXCEPTION = 1;
    private static final int MAC_OS = 2;

    public static Text getDiscordRPTranslatable(){
        if(DiscordRP.discordRPErrorcode == NORMAL){
            return new TranslatableText("mineclub-expanded.config.richpresence");
        } else {
            return new TranslatableText("mineclub-expanded.config.richpresence.unsupported");
        }
    }

    public static Text getDiscordRPDescriptionTranslatable(){
        if(DiscordRP.discordRPErrorcode == NORMAL){
            return new TranslatableText("mineclub-expanded.config.richpresence.description");
        } else if(DiscordRP.discordRPErrorcode == EXCEPTION){
            return new TranslatableText("mineclub-expanded.config.richpresence.unsupported.description.exception");
        } else if(DiscordRP.discordRPErrorcode == MAC_OS){
            return new TranslatableText("mineclub-expanded.config.richpresence.unsupported.description.macos");
        } else {
            throw new IndexOutOfBoundsException("DiscordRP Error Code is not in bounds of expected range. (Expected Range is 0-2 but found " + DiscordRP.discordRPErrorcode + ")");
        }
    }
}
