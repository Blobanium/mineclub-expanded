package io.github.blobanium.mineclubexpanded.util.spreadsheet;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.concurrent.TimeUnit;

import static java.lang.Integer.parseInt;

public class SpreadsheetValueParser {
    public static String getSheetsString = null;
    public static String values[] = null;

    public static void setValue(){
        try {
            getSheetsString = SpreadsheetUtil.testInternal("C"+ getToday() + ":AV" + getToday()).replace("[", "").replace("]", "").replace(" ", "");
        } catch (ParseException e) {
            e.printStackTrace();
        }
        values = getSheetsString.split(",");
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
            case "Easter Hat" -> parseInt(values[15]);
            case "Easter Basket" -> parseInt(values[16]);
            case "Easter Title" -> parseInt(values[17]);
            case "Spring Crown" -> parseInt(values[18]);
            case "Spring Flowers" -> parseInt(values[19]);
            case "Spring Title" -> parseInt(values[20]);
            case "Summer Butterfly" -> parseInt(values[21]);
            case "Summer Net" -> parseInt(values[22]);
            case "Summer Title" -> parseInt(values[23]);
            case "Nixe Beta Figure" -> parseInt(values[24]);
            case "Hylix Beta Figure" -> parseInt(values[25]);
            case "Executive Beta Figure" -> parseInt(values[26]);
            case "Rasho Beta Figure" -> parseInt(values[27]);
            case "Autumn Crown" -> parseInt(values[28]);
            case "Autumn Plushie" -> parseInt(values[29]);
            case "Autumn Title" -> parseInt(values[30]);
            case "Halloween Beanie" -> parseInt(values[31]);
            case "Halloween Basket" -> parseInt(values[32]);
            case "Halloween Title" -> parseInt(values[33]);
            case "Winter Hat" -> parseInt(values[34]);
            case "Winter Plushie" -> parseInt(values[35]);
            case "Winter Title" -> parseInt(values[36]);
            case "Christmas Hat" -> parseInt(values[37]);
            case "Christmas Cane" -> parseInt(values[38]);
            case "Christmas Title" -> parseInt(values[39]);
            case "Anniversary Hat" -> parseInt(values[40]);
            case "Anniversary Cake" -> parseInt(values[41]);
            case "Anniversary Title" -> parseInt(values[42]);
            case "Valentines Hat" -> parseInt(values[43]);
            case "Valentines Bow" -> parseInt(values[44]);
            case "Valentines Title" -> parseInt(values[45]);
            default -> 0;
        };
    }

    private static long getToday() throws ParseException {
        String endDateSource = (Calendar.getInstance().get(Calendar.MONTH) + 1) + "-" + Calendar.getInstance().get(Calendar.DAY_OF_MONTH) + "-" + Calendar.getInstance().get(Calendar.YEAR);
        SimpleDateFormat obj = new SimpleDateFormat("MM-dd-yyyy");
        Date startDate = obj.parse("6-20-2021");
        Date endDate = obj.parse(endDateSource);
        long difference = endDate.getTime() - startDate.getTime();
        long days_difference = TimeUnit.MILLISECONDS.toDays(difference);
        return days_difference + 1;
    }
}
