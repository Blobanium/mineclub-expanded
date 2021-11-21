package io.github.blobanium.mineclubexpanded.util.mixinhelper;

public class GradientHelper {
    //Turns out using String arrays didn't like me too much, just going to use this instead.
    //I might change this up later.

    private static String u1;
    private static String u2;
    private static String u3;
    private static String u4;
    private static String u5;
    private static String u6;
    private static String u7;
    private static String u8;
    private static String u9;
    private static String u10;
    private static String u11;
    private static String u12;
    private static String u13;
    private static String u14;
    private static String u15;
    private static String u16;

    public static String convertGradientToString(String text){
        resetVariables();
        return setUsername(text);
    }

    private static String setUsername(String text){
        try {
            u1 = String.valueOf(text.charAt(25));
            u2 = String.valueOf(text.charAt(71));
            u3 = String.valueOf(text.charAt(117));
            u4 = String.valueOf(text.charAt(163));
            u5 = String.valueOf(text.charAt(209));
            u6 = String.valueOf(text.charAt(255));
            u7 = String.valueOf(text.charAt(301));
            u8 = String.valueOf(text.charAt(347));
            u9 = String.valueOf(text.charAt(393));
            u10 = String.valueOf(text.charAt(439));
            u11 = String.valueOf(text.charAt(485));
            u12 = String.valueOf(text.charAt(531));
            u13 = String.valueOf(text.charAt(577));
            u14 = String.valueOf(text.charAt(623));
            u15 = String.valueOf(text.charAt(669));
            u16 = String.valueOf(text.charAt(715));
        } catch (IndexOutOfBoundsException ignored){
            //This is pretty much ignored.
        }
        return (u1 + u2 + u3 + u4 + u5 + u6 + u7 + u8 + u9 + u10 + u11 + u12 + u13 + u14 + u15 + u16);
    }

    private static void resetVariables(){
        u1 = ""; u2 = ""; u3 = ""; u4 = ""; u5 = ""; u6 = ""; u7 = ""; u8 = ""; u9 = ""; u10 = ""; u11 = ""; u12 = ""; u13 = ""; u14 = ""; u15 = ""; u16 = "";
    }
}
