package io.github.blobanium.mineclubexpanded.mixin;

import io.github.blobanium.mineclubexpanded.util.discord.DiscordRP;
import net.minecraft.client.network.ClientLoginNetworkHandler;
import net.minecraft.network.packet.s2c.login.LoginDisconnectS2CPacket;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;

@Mixin(ClientLoginNetworkHandler.class)
public class ClientLoginNetworkHandlerMixin {
    @Inject(at = @At("HEAD"), method = "onDisconnect")
    private void onDisconect(LoginDisconnectS2CPacket packet, CallbackInfo ci){
        DiscordRP.clearStatus();
    }
}
