package io.github.blobanium.mineclubexpanded.mixin;

import io.github.blobanium.mineclubexpanded.games.tabletop.RichPresenceTabletopChatListener;
import io.github.blobanium.mineclubexpanded.global.WorldListener;
import io.github.blobanium.mineclubexpanded.market.OutbidNotifier;
import io.github.blobanium.mineclubexpanded.util.config.ConfigReader;
import io.github.blobanium.mineclubexpanded.util.discord.DiscordRP;
import io.github.blobanium.mineclubexpanded.util.tick.TickTracker;
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
    private static String lastMessage;
    private static String lastMessage2;
    private static Integer msgCount = 0;
    private static boolean chatBeingCleared = false;

    @Inject(at = @At("HEAD"), method = "onChatMessage")
    public void onChatMessage(MessageType messageType, Text message, UUID sender, CallbackInfo ci){
        //Detect If A Chat message is being deleted on mineclub.
        if(message.getString().matches("ꌄ§7")){
            msgCount = msgCount + 1;
            if(msgCount >= 80){
                chatBeingCleared = true;
            }
        } else if(!chatBeingCleared) {
            lastMessage2 = lastMessage;
            lastMessage = message.getString();
            //Broadcast On Chat Message
            onMessage(message);
        } else {
            if(lastMessage.equals(message.getString())||lastMessage2.equals(message.getString())){
                chatBeingCleared = false;
            }
        }
    }

    private static void onMessage(Text message){
        if(ConfigReader.outbidNotification) {
            OutbidNotifier.onChatMessage(message);
        }

        if(ConfigReader.richPresence){
            RichPresenceTabletopChatListener.onChatMessage(message);

            if(WorldListener.isInHousing){
                if(message.getString().startsWith("ꌄ冈 No player found by name")){
                    TickTracker.cancelHousingUpdate = true;
                }
            }

            if(message.getString().startsWith("ꌄ咀")){
                DiscordRP.updateStatus("Currently in Staff HQ","Playing on Mineclub");
            }
        }
    }
}
