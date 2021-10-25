package io.github.blobanium.mineclubexpanded.util;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class RegexFinder {
    public static boolean matchesRegex(String regex, String message){
        Pattern finalRegex = Pattern.compile(regex, Pattern.LITERAL + Pattern.UNICODE_CASE);
        Matcher searchMatcher = finalRegex.matcher(message);
        if(!searchMatcher.matches()){
            System.out.println(message + "///" + finalRegex);
        }
        return searchMatcher.matches();
    }
}
