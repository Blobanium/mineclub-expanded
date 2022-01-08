package io.github.blobanium.mineclubexpanded.util.config;

import io.github.blobanium.mineclubexpanded.util.discord.DiscordRP;
import net.minecraft.text.Text;
import net.minecraft.text.TranslatableText;

public class DynamicModMenuTranslatable {
    public static Text getDiscordRPTranslatable(){
        if(DiscordRP.supportsRichPresence){
            return new TranslatableText("mineclub-expanded.config.richpresence");
        } else {
            return new TranslatableText("mineclub-expanded.config.richpresence.unsupported");
        }
    }

    public static Text getDiscordRPDescriptionTranslatable(){
        if(DiscordRP.supportsRichPresence){
            return new TranslatableText("mineclub-expanded.config.richpresence.description");
        } else {
            return new TranslatableText("mineclub-expanded.config.richpresence.unsupported.description");
        }
    }
}
