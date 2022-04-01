package io.github.blobanium.mineclubexpanded.games.adminevent;

import io.github.blobanium.mineclubexpanded.util.sound.SoundPlayer;
import net.minecraft.sound.SoundEvents;

public class AdminEventNotifier {
    public static boolean adminEventPending = false;

    public static void checkEvent(){
        AdminEventDecoder.decodeBossBar();
        if(AdminEventDecoder.adminEvent != null && !adminEventPending){
            SoundPlayer.playSound(1.0F, SoundEvents.BLOCK_NOTE_BLOCK_CHIME);
            adminEventPending = true;
        } else if (AdminEventDecoder.adminEvent == null && adminEventPending){
            adminEventPending = false;
        }
    }
}
