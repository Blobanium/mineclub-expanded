package io.github.blobanium.mineclubexpanded.mixin;

import io.github.blobanium.mineclubexpanded.MineclubExpanded;
import io.github.blobanium.mineclubexpanded.util.config.ConfigReader;
import io.github.blobanium.mineclubexpanded.util.feature.Autoreconnect;
import net.minecraft.client.gui.screen.DisconnectedScreen;
import net.minecraft.client.gui.screen.Screen;
import net.minecraft.text.Text;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;

@Mixin(DisconnectedScreen.class)
public class DisconnectedScreenMixin {
    private static int attempts = 0;

    @Inject(at = @At("TAIL"), method = "<init>")
    private void init(Screen parent, Text title, Text reason, CallbackInfo ci) {
        onDisconnect(parent, title, reason);
    }

    private static void onDisconnect(Screen parent, Text title, Text reason) {
        MineclubExpanded.LOGGER.warn("Disconnection occurred.\nTitle=" + title.getString() + "\nReason=" + reason.getString());
        if (ConfigReader.autoReconnect && !wasUsingMaliciousIntent(reason) && attempts <= ConfigReader.autoReconnectAttempts) {
            Autoreconnect.setReminder(ConfigReader.autoReconnectSeconds, parent);
            attempts = attempts + 1;
        } else if (wasUsingMaliciousIntent(reason)){
            MineclubExpanded.LOGGER.error( "Autoreconnect will not reconnect to Mineclub.\nDo not use Autoreconnect with malicious intent.");
        }
    }

    private static boolean wasUsingMaliciousIntent(Text reason){
        if (reason.getString().contains("You were kicked for")) {
            return true;
        }else if (reason.getString().contains("You were banned for")) {
            return true;
        }else return reason.getString().contains("You are banned for");
    }
}
