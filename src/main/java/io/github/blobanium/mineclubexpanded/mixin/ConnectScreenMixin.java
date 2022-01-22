package io.github.blobanium.mineclubexpanded.mixin;

import io.github.blobanium.mineclubexpanded.global.WorldListener;
import io.github.blobanium.mineclubexpanded.housing.HousingRichPresenceListener;
import net.minecraft.client.MinecraftClient;
import net.minecraft.client.gui.screen.ConnectScreen;
import net.minecraft.client.network.ServerAddress;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;

@Mixin(ConnectScreen.class)
public class ConnectScreenMixin {
    @Inject(at = @At("HEAD"), method = "connect(Lnet/minecraft/client/MinecraftClient;Lnet/minecraft/client/network/ServerAddress;)V")
    private void connect(MinecraftClient client, ServerAddress address, CallbackInfo ci){
        if(address.getAddress().contains(".mineclub.house")){
            HousingRichPresenceListener.usernameFromServerIP = address.getAddress().replace(".mineclub.house","");
            WorldListener.connectUsingHousingIP = true;
        }
    }
}
