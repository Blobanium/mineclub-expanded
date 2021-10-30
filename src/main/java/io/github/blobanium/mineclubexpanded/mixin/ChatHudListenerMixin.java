package io.github.blobanium.mineclubexpanded.mixin;

import io.github.blobanium.mineclubexpanded.MineclubExpanded;
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
    private static String lastMessage;
    private static String lastMessage2;
    private static Integer msgCount = 0;
    private static boolean chatBeingCleared = false;

    @Inject(at = @At("HEAD"), method = "onChatMessage")
    public void onChatMessage(MessageType messageType, Text message, UUID sender, CallbackInfo ci){
        if(message.getString().matches("ꌄ§7")){
            msgCount = msgCount + 1;
            if(msgCount >= 80){
                chatBeingCleared = true;
                MineclubExpanded.LOGGER.info("chat being cleared");
            }
        } else if(!chatBeingCleared) {
            lastMessage2 = lastMessage;
            lastMessage = message.getString();
            OutbidNotifier.onChatMessage(message);
        } else {
            if(lastMessage.equals(message.getString())||lastMessage2.equals(message.getString())){
                chatBeingCleared = false;
            }
        }
    }
}
