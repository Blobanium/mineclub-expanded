package io.github.blobanium.mineclubexpanded.games.adminevent;

import io.github.blobanium.mineclubexpanded.util.unicode.UnicodeTranslator;

public class AdminEventDecoder {
    public static String lastString = "";

    public static String adminEvent = null;
    public static String host = null;
    public static String timeleft = null;
    public static boolean isStarting = false;

    public static void decodeBossBar(){
        String text = lastString;
        if(text.length() != 0) {
            adminEvent = UnicodeTranslator.unicodeToReadableText(String.valueOf(text.charAt(0)));

            if (text.length() == 10) {
                isStarting = false;
                timeleft = getTime(text);
                host = getHost(String.valueOf(text.charAt(8)));
            } else if (text.length() == 6) {
                isStarting = true;
            }
        }
    }

    private static String getTime(String text){
        StringBuilder builder = new StringBuilder();
        String time = text.substring(2, 7);
        builder.append(getDigit(String.valueOf(time.charAt(0))));
        builder.append(getDigit(String.valueOf(time.charAt(1))));
        builder.append(":");
        builder.append(getDigit(String.valueOf(time.charAt(3))));
        builder.append(getDigit(String.valueOf(time.charAt(4))));
        return builder.toString();
    }

    private static int getDigit(String unicode){
        return Integer.parseInt(UnicodeTranslator.unicodeToReadableText(unicode));
    }

    private static String getHost(String unicode){
        return UnicodeTranslator.unicodeToReadableText(unicode);
    }

}
