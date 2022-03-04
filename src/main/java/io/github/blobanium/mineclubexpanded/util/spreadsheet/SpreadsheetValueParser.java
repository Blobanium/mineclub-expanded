package io.github.blobanium.mineclubexpanded.util.spreadsheet;

import static java.lang.Integer.parseInt;

public class SpreadsheetValueParser {
    public static String getSheetsString = null;
    public static String values[] = null;

    public static void setValue(){
        getSheetsString = SpreadsheetUtil.testInternal("C257:AV257").replace("[", "").replace("]", "").replace(" ", "");
        values = getSheetsString.split(",");
        System.out.println(getItemValue("Blue Bhat"));
    }

    public static int getItemValue(String item){
        return switch(item){
            case "Premium Token" -> parseInt(values[0]);
            case "Mythical Box" -> parseInt(values[1]);
            case "Epic Box" -> parseInt(values[2]);
            case "Rare Box" -> parseInt(values[3]);
            case "Beta Hat" -> parseInt(values[4]);
            case "Beta Saber" -> parseInt(values[5]);
            case "Beta Title" -> parseInt(values[6]);
            case "Blue Bhat" -> parseInt(values[7]);
            case "Purple Bhat" -> parseInt(values[8]);
            case "Green Bhat" -> parseInt(values[9]);
            case "Red Bhat" -> parseInt(values[10]);
            case "Red Saber" -> parseInt(values[11]);
            case "Purple Saber" -> parseInt(values[12]);
            case "Green Saber" -> parseInt(values[13]);
            case "Blue Saber" -> parseInt(values[14]);
            default -> 0;
        };
    }
}
