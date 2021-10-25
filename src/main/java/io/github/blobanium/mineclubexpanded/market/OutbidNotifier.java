package io.github.blobanium.mineclubexpanded.market;

import io.github.blobanium.mineclubexpanded.util.RegexFinder;
import net.minecraft.client.MinecraftClient;
import net.minecraft.client.toast.SystemToast;
import net.minecraft.text.LiteralText;
import net.minecraft.text.Text;

public class OutbidNotifier {
    public static void onChatMessage(Text message){
        String textMessage = message.getString().replaceAll("\\[","\\\\[");
        if(RegexFinder.matchesRegex("ꌄ§8\\[§dMarket§8] §cYou have been outbid by", textMessage)){
            System.out.println("Triggered message");
            SystemToast toast = SystemToast.create(MinecraftClient.getInstance(), SystemToast.Type.TUTORIAL_HINT, new LiteralText("ayyy!!!"), new LiteralText("It Worked!"));
            MinecraftClient.getInstance().getToastManager().add(toast);
        } else {
            System.out.println("nope");

        }
    }
}
