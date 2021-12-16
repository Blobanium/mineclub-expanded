package io.github.blobanium.mineclubexpanded.util.mixinhelper;

import io.github.blobanium.mineclubexpanded.MineclubExpanded;
import io.github.blobanium.mineclubexpanded.util.config.ConfigReader;
import io.github.blobanium.mineclubexpanded.util.feature.Autoreconnect;
import net.minecraft.client.gui.screen.Screen;
import net.minecraft.text.Text;

public class AutoReconnectManager {
    public static int attempts = 0;

    public static void onDisconnect(Screen parent, Text title, Text reason) {
        MineclubExpanded.LOGGER.warn("Disconnection occurred.\nTitle=" + title.getString() + "\nReason=" + reason.getString());
        if (ConfigReader.autoReconnect && !wasUsingMaliciousIntent(reason) && attempts <= 3) {
            Autoreconnect.setReminder(5, parent);
            attempts = attempts + 1;
        } else if (wasUsingMaliciousIntent(reason)){
            MineclubExpanded.LOGGER.error( "Autoreconnect will not reconnect to Mineclub.\nDo not use Autoreconnect with malicious intent.");
        }
    }

    public static boolean wasUsingMaliciousIntent(Text reason){
        if (reason.getString().contains("You were kicked for")) {
            return true;
        }else if (reason.getString().contains("You were banned for")) {
            return true;
        }else return reason.getString().contains("You are banned for");
    }
}
