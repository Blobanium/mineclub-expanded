package io.github.blobanium.mineclubexpanded.mixin;

import io.github.blobanium.mineclubexpanded.util.discord.DiscordRP;
import net.minecraft.client.network.ClientPlayNetworkHandler;
import net.minecraft.network.packet.s2c.play.DisconnectS2CPacket;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;

@Mixin(ClientPlayNetworkHandler.class)
public class ClientPlayNetworkHandlerMixin {
    @Inject(at = @At("HEAD"), method = "onDisconnect")
    private void onDisconect(DisconnectS2CPacket packet, CallbackInfo ci){
        DiscordRP.clearStatus();
    }
}
