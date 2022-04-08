package io.github.blobanium.mineclubexpanded.games;

import io.github.blobanium.mineclubexpanded.util.unicode.UnicodeTranslator;

import java.time.OffsetDateTime;
import java.util.ArrayList;

public class BattleDomePresence {

    public static OffsetDateTime getTimeLeft(String bossbartext, OffsetDateTime time){
        ArrayList<String> array = UnicodeTranslator.decodeToArrayList(bossbartext);
        int TimerIndex = array.indexOf("Timer");
        int minutes = 0;
        int seconds = 0;
        if(TimerIndex != -1) {
            minutes = (Integer.parseInt(array.get(TimerIndex + 2)) * 10) + Integer.parseInt(array.get(TimerIndex + 3));
            seconds = (Integer.parseInt(array.get(TimerIndex + 5)) * 10) + Integer.parseInt(array.get(TimerIndex + 6));
        }
        return time.plusMinutes(minutes).plusSeconds(seconds);
    }

    public static OffsetDateTime getTimeLeft(String bossbartext){
        return getTimeLeft(bossbartext, OffsetDateTime.now());
    }


}
