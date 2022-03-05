package io.github.blobanium.mineclubexpanded.util.spreadsheet;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.time.Period;
import java.time.Duration;
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
