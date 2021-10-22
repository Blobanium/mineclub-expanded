package io.github.blobanium.mineclubexpanded.mixin;

import io.github.blobanium.mineclubexpanded.MineclubExpanded;
import net.minecraft.client.MinecraftClient;
import net.minecraft.client.gui.hud.ChatHudListener;
import net.minecraft.client.toast.SystemToast;
import net.minecraft.network.MessageType;
import net.minecraft.text.LiteralText;
import net.minecraft.text.Text;
import net.minecraft.text.TranslatableText;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;

import java.util.UUID;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

@Mixin(ChatHudListener.class)
public class ChatHudListenerMixin {
    //TODO: Get The regex to work
    private final Pattern outbidPattern = Pattern.compile("ꌄ§8\\[§dMarket§8] §cYou have been outbid by");

    @Inject(at = @At("HEAD"), method = "onChatMessage")
    public void onChatMessage(MessageType messageType, Text message, UUID sender, CallbackInfo ci){
        Matcher outbidMatcher = outbidPattern.matcher(message.getString());
        if(outbidMatcher.matches()){
            //this is what i want it to output below
            System.out.println("Triggered message");
            SystemToast toast = SystemToast.create(MinecraftClient.getInstance(), SystemToast.Type.TUTORIAL_HINT, new LiteralText("ayyy!!!"), new LiteralText("It Worked!"));
            MinecraftClient.getInstance().getToastManager().add(toast);
        } else {
            System.out.println(outbidPattern);
            System.out.println(outbidMatcher);
        }

    }
}
