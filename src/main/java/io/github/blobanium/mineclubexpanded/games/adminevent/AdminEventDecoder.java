package io.github.blobanium.mineclubexpanded.games.adminevent;

public class AdminEventDecoder {
    public static String lastString = "";

    public static String adminEvent = null;
    public static String host = null;
    public static String timeleft = null;
    public static boolean isStarting = false;

    public static void decodeBossBar(){
        String text = lastString;
        if(text.length() != 0) {
            adminEvent = switch (String.valueOf(text.charAt(0))) {
                case "颐", "荽" -> "TNT Run";
                case "萱", "苧" -> "Spleef";
                case "芋", "芣" -> "Brawl";
                case "岐", "巅" -> "Infected";
                case "迭", "追" -> "Sword Game";
                default -> null;
            };

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
        return switch (unicode){
            case "匪", "黄", "勤", "萣", "峣" -> 0;
            case "赜", "甘", "苈", "菪", "岵" -> 1;
            case "臣", "荁", "芜", "靰", "凶" -> 2;
            case "届", "薷", "兰", "莞", "岽" -> 3;
            case "卧", "虉", "其", "鞔", "炭" -> 4;
            case "彐", "藿", "芸", "韂", "崤" -> 5;
            case "刁", "觐", "芫", "孽", "崦" -> 6;
            case "翚", "蕾", "甚", "鞣", "崎" -> 7;
            case "帚", "茜", "邯", "靸", "嵽" -> 8;
            case "翂", "某", "薤", "鞬", "崭" -> 9;
            default -> 0;
        };
    }

    private static String getHost(String unicode){
        return switch(unicode){
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
            default -> null;
        };
    }

}
