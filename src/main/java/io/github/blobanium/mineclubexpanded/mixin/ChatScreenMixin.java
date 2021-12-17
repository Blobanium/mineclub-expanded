package io.github.blobanium.mineclubexpanded.mixin;


import io.github.blobanium.mineclubexpanded.MineclubExpanded;
import net.minecraft.client.gui.screen.ChatScreen;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;

@Mixin(ChatScreen.class)
public abstract class ChatScreenMixin {

    @Inject(at = @At("HEAD"), method =  "onChatFieldUpdate")
    private void onChatFieldUpdate(String chatText, CallbackInfo ci){
        MineclubExpanded.lastChatField = chatText;
    }

    @Inject(at = @At("HEAD"), method =  "init")
    private void init(CallbackInfo ci){
        MineclubExpanded.isChatOpen = true;
    }
}
