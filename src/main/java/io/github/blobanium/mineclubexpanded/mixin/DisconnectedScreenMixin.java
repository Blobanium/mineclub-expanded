package io.github.blobanium.mineclubexpanded.mixin;

import io.github.blobanium.mineclubexpanded.util.mixinhelper.AutoReconnectManager;
import net.minecraft.client.gui.screen.DisconnectedScreen;
import net.minecraft.client.gui.screen.Screen;
import net.minecraft.text.Text;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;

@Mixin(DisconnectedScreen.class)
public class DisconnectedScreenMixin {

    @Inject(at = @At("TAIL"), method = "<init>")
    private void init(Screen parent, Text title, Text reason, CallbackInfo ci) {
        AutoReconnectManager.onDisconnect(parent, title, reason);
    }
}
