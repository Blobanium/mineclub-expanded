package io.github.blobanium.mineclubexpanded.util.mixinhelper;

public class GradientHelper {

    private static StringBuilder u = new StringBuilder();

    public static String convertGradientToString(String text){
        u.delete(0, u.length());
        return setUsername(text);
    }

    private static int getPosition(int i){
        return  (46 * i) - 21;
    }

    private static String setUsername(String text){
        for(int i = 1; getPosition(i) <= text.length(); i++){
            u.append(text.charAt(getPosition(i)));
        }
        return u.toString();
    }
}
