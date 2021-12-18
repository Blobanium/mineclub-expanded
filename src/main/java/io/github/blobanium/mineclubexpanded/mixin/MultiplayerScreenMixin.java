package io.github.blobanium.mineclubexpanded.mixin;

import io.github.blobanium.mineclubexpanded.util.feature.Autoreconnect;
import net.minecraft.client.gui.screen.multiplayer.MultiplayerScreen;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;

@Mixin(MultiplayerScreen.class)
public class MultiplayerScreenMixin {
    @Inject(at = @At("HEAD"), method = "init")
    private void init(CallbackInfo ci){
        if(!Autoreconnect.hasNotified){
            Autoreconnect.cancelAutoReconnect = true;
        }
    }
}

