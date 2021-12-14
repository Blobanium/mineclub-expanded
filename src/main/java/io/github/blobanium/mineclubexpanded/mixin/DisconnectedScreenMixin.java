package io.github.blobanium.mineclubexpanded.mixin;

import io.github.blobanium.mineclubexpanded.MineclubExpanded;
import io.github.blobanium.mineclubexpanded.util.config.ConfigReader;
import io.github.blobanium.mineclubexpanded.util.feature.Autoreconnect;
import net.minecraft.client.gui.screen.DisconnectedScreen;
import net.minecraft.client.gui.screen.Screen;
import net.minecraft.text.Text;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Shadow;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;

@Mixin(DisconnectedScreen.class)
public class DisconnectedScreenMixin {
    private static boolean doNotReconnect = false;

    @Inject(at = @At("TAIL"), method = "<init>")
    private void init(Screen parent, Text title, Text reason, CallbackInfo ci) {
        doNotReconnect = false;
        MineclubExpanded.LOGGER.warn("Disconnection occurred.\nTitle=" + title.getString() + "\nReason=" + reason.getString());
        if(reason.getString().contains("You were kicked for")){
            MineclubExpanded.LOGGER.error("Will not reconnect (has been kicked my a moderator, do not use auto-reconnect for malicious intent.)");
            doNotReconnect = true;
        }
        if(ConfigReader.autoReconnect && !doNotReconnect) {
            Autoreconnect.setReminder(5, parent);
        }
    }
}
