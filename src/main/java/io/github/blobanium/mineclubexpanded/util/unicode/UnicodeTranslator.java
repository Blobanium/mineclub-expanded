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
            case "匮", "ꌅ", "奋", "蒸", "钪", "ꌋ", "镦", "晖", "ꈋ", "香", "ꌆ", "铰", "ꌄ", "腈", "顼", "鼬" -> "Split";

            //Numbers
            case "匪", "黄", "勤", "萣", "峣", "锈" -> "0";
            case "赜", "甘", "苈", "菪", "岵", "钣" -> "1";
            case "臣", "荁", "芜", "靰", "凶", "锋" -> "2";
            case "届", "薷", "兰", "莞", "岽", "铬" -> "3";
            case "卧", "虉", "其", "鞔", "炭", "铥" -> "4";
            case "彐", "藿", "芸", "韂", "崤", "锆" -> "5";
            case "刁", "觐", "芫", "孽", "崦", "铣" -> "6";
            case "翚", "蕾", "甚", "鞣", "崎", "钐" -> "7";
            case "帚", "茜", "邯", "靸", "嵽", "铆" -> "8";
            case "翂", "某", "薤", "鞬", "崭", "镏" -> "9";

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

            //Others
            case "愉" -> "Topbar";
            case "谏" -> "Diamond";
            case "悒" -> "Kills";
            case "惚", "毕", "毙", "怅", "毖", "忳", "情", "愫" -> "Alive";
            case "句" -> "Dead";
            case "钭" -> "Timer";

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
