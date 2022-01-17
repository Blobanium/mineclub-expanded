package io.github.blobanium.mineclubexpanded.util.mixinhelper;

public class GradientHelper {

    private static StringBuilder u = new StringBuilder();

    public static String convertGradientToString(String text){
        u.delete(0, u.length());
        return setUsername(text);
    }

    private static String setUsername(String text){
        try {
            u.append(text.charAt(25));
            u.append(text.charAt(71));
            u.append(text.charAt(117));
            u.append(text.charAt(163));
            u.append(text.charAt(209));
            u.append(text.charAt(255));
            u.append(text.charAt(301));
            u.append(text.charAt(347));
            u.append(text.charAt(393));
            u.append(text.charAt(439));
            u.append(text.charAt(485));
            u.append(text.charAt(531));
            u.append(text.charAt(577));
            u.append(text.charAt(623));
            u.append(text.charAt(669));
            u.append(text.charAt(715));
        } catch (IndexOutOfBoundsException ignored){
            //This is pretty much ignored.
        }

        return u.toString();
    }
}
