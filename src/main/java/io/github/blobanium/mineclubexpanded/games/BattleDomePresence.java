package io.github.blobanium.mineclubexpanded.games;

import io.github.blobanium.mineclubexpanded.util.unicode.UnicodeTranslator;

import java.time.OffsetDateTime;
import java.util.ArrayList;

public class BattleDomePresence {
    public static OffsetDateTime offsetlastfield = null;

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
        OffsetDateTime value = getTimeLeft(bossbartext, OffsetDateTime.now());
        if(offsetlastfield != null){
            if(compare(offsetlastfield, value) >= 1000000000L){
                System.out.println("Update Offset Time presence");
            }
        }
        offsetlastfield = value;
        return value;
    }
    private static long compare(OffsetDateTime date1, OffsetDateTime date2){
        long hoursDiff = date2.getHour() - date1.getHour();
        long minutesDiff = date2.getMinute() - date1.getMinute();
        long secondsDiff = date2.getSecond() - date1.getSecond();
        long nanoDiff = date2.getNano() - date1.getNano();
        return nanoDiff + (secondsDiff * 1000000000L) + (minutesDiff * 60000000000L) + (hoursDiff * 3600000000000L);
    }
}
