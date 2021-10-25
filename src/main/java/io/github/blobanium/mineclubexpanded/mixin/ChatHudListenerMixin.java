package io.github.blobanium.mineclubexpanded.mixin;

import io.github.blobanium.mineclubexpanded.market.OutbidNotifier;
import net.minecraft.client.gui.hud.ChatHudListener;
import net.minecraft.network.MessageType;
import net.minecraft.text.Text;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;

import java.util.UUID;
import java.util.regex.Pattern;

@Mixin(ChatHudListener.class)
public class ChatHudListenerMixin {
    //TODO: Get The regex to work
    private final Pattern outbidPattern = Pattern.compile("ꌄ\\[Market] You have been outbid by");

    @Inject(at = @At("HEAD"), method = "onChatMessage")
    public void onChatMessage(MessageType messageType, Text message, UUID sender, CallbackInfo ci){
        OutbidNotifier.onChatMessage(message);
    }
}
