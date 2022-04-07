package io.github.blobanium.mineclubexpanded.util.unicode;

import java.util.ArrayList;

public class UnicodeTranslator {
    private static StringBuilder unicodebuilder = new StringBuilder();

    public static String unicodeToReadableText(String unicode){
        if(unicode.length() > 1){
            throw new StringIndexOutOfBoundsException("Cannot use more then 1 char at a time.  Char: " + unicode);
        }
        return switch (unicode){
            //Space Splits
            case "匮", "ꌅ", "奋", "蒸" -> "Split";

            //Numbers
            case "匪", "黄", "勤", "萣", "峣" -> "0";
            case "赜", "甘", "苈", "菪", "岵" -> "1";
            case "臣", "荁", "芜", "靰", "凶" -> "2";
            case "届", "薷", "兰", "莞", "岽" -> "3";
            case "卧", "虉", "其", "鞔", "炭" -> "4";
            case "彐", "藿", "芸", "韂", "崤" -> "5";
            case "刁", "觐", "芫", "孽", "崦" -> "6";
            case "翚", "蕾", "甚", "鞣", "崎" -> "7";
            case "帚", "茜", "邯", "靸", "嵽" -> "8";
            case "翂", "某", "薤", "鞬", "崭" -> "9";

            //Admin Event Host Names
            case "蕨" -> "ComicxqcL";
            case "菰" -> "Gabanna";
            case "芽" -> "Shower";
            case "薢" -> "JpCuber109";
            case "岍" -> "Ferus";
            case "蓐" -> "Lazrith";
            case "菟" -> "Guby";
            case "荪" -> "Executive";
            case "蔈" -> "Respawning";
            case "艺" -> "Rasmus";
            case "芎" -> "Hylix";
            case "蘸" -> "Elsa040";
            case "蕈" -> "Chqrri";
            case "蔫" -> "Nixe";

            //Admin Event Banners
            case "颐", "荽" -> "TNT Run";
            case "萱", "苧" -> "Spleef";
            case "芋", "芣" -> "Brawl";
            case "岐", "巅" -> "Infected";
            case "迭", "追" -> "Sword Game";

            //return default if unique unicode
            default -> unicode;
        };
    }

    public static String decodeEntireString(String text){
        unicodebuilder.delete(0, unicodebuilder.length());
        for(int i = 0; i < text.length(); i++){
            unicodebuilder.append(unicodeToReadableText(String.valueOf(text.charAt(i))));
            unicodebuilder.append(":");
        }
        return unicodebuilder.toString();
    }

    public static ArrayList<String> decodeToArrayList(String text){
        ArrayList<String> list = new ArrayList<String>();
        for(int i = 0; i < text.length(); i++){
            list.add(unicodeToReadableText(String.valueOf(text.charAt(i))));
        }
        return list;
    }
}
