package io.github.blobanium.mineclubexpanded.util.spreadsheet;

import io.github.blobanium.mineclubexpanded.util.tooltip.TooltipInjector;
import io.github.blobanium.mineclubexpanded.util.tooltip.TooltipProcessor;
import net.minecraft.item.ItemStack;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.concurrent.TimeUnit;

import static java.lang.Integer.parseInt;

public class SpreadsheetValueParser {
    public static String getSheetsString = null;
    public static String values[] = null;
    public static String sheetDate = null;

    public static String month = null;
    public static String day = null;

    //Custom Model Data
    private static final int STORE_ITEM = 183;
    private static final int BETA_TITLE = 87;
    private static final int RED_HAT = 278;
    private static final int BLUE_HAT = 274;
    private static final int GREEN_HAT = 275;
    private static final int PURPLE_HAT = 276;
    private static final int RED_SABER = 349;
    private static final int PURPLE_SABER = 352;
    private static final int GREEN_SABER = 351;
    private static final int BLUE_SABER = 350;
    private static final int EASTER_HAT = 530;
    private static final int EASTER_BASKET = 531;
    private static final int EASTER_TITLE = 98;
    private static final int SPRING_CROWN = 417;
    private static final int SPRING_FLOWERS = 418;
    private static final int SPRING_TITLE = 99;
    private static final int SUMMER_BUTTERFLY = 424;
    private static final int SUMMER_NET = 425;
    private static final int SUMMER_TITLE = 200;
    private static final int NIXE_FIGURE = 133;
    private static final int HYLIX_FIGURE = 134;
    private static final int EXECUTIVE_FIGURE = 135;
    private static final int RASHO_FIGURE = 178;
    private static final int AUTUMN_CROWN = 763;
    private static final int AUTUMN_PLUSHIE = 764;
    private static final int AUTUMN_TITLE = 345;
    private static final int HALLOWEEN_BEANIE = 805;
    private static final int HALLOWEEN_BASKET = 806;
    private static final int HALLOWEEN_TITLE = 346;
    private static final int WINTER_HAT = 955;
    private static final int WINTER_PLUSHIE = 956;
    private static final int WINTER_TITLE = 380;
    private static final int CHRISTMAS_HAT = 1099;
    private static final int CHRISTMAS_CANE = 1100;
    private static final int CHRISTMAS_TITLE = 394;
    private static final int ANIVERSARY_HAT = 1117;
    private static final int ANIVERSARY_CAKE = 1118;
    private static final int ANIVERSARY_TITLE = 398;
    private static final int VALENTINES_CROWN = 1173;
    private static final int VALENTINES_BOW = 1174;
    private static final int VALENTINES_TITLE = 501;
    private static final int LEPRECHAUN_HAT = 1214;
    private static final int LEPRECHAUN_TREASURES = 1215;
    private static final int LEPRECHAUN_TITLE = 505;

    public static void setValue(){
        try {
            getSheetsString = SpreadsheetUtil.testInternal("C"+ getToday() + ":AY" + getToday()).replace("[", "").replace("]", "").replace(" ", "");
        } catch (ParseException e) {
            e.printStackTrace();
        }
        values = getSheetsString.split(",");
    }

    public static int getItemValue(int customModelData, ItemStack stack) throws IndexOutOfBoundsException{
        return switch(customModelData){
            case STORE_ITEM -> getStoreItemValue(stack);
            case BETA_TITLE -> parseInt(values[6]);
            case BLUE_HAT -> parseInt(values[7]);
            case PURPLE_HAT -> parseInt(values[8]);
            case GREEN_HAT -> parseInt(values[9]);
            case RED_HAT -> parseInt(values[10]);
            case RED_SABER -> parseInt(values[11]);
            case PURPLE_SABER -> parseInt(values[12]);
            case GREEN_SABER -> parseInt(values[13]);
            case BLUE_SABER -> parseInt(values[14]);
            case EASTER_HAT -> parseInt(values[15]);
            case EASTER_BASKET -> parseInt(values[16]);
            case EASTER_TITLE -> parseInt(values[17]);
            case SPRING_CROWN -> parseInt(values[18]);
            case SPRING_FLOWERS -> parseInt(values[19]);
            case SPRING_TITLE -> parseInt(values[20]);
            case SUMMER_BUTTERFLY -> parseInt(values[21]);
            case SUMMER_NET -> parseInt(values[22]);
            case SUMMER_TITLE -> parseInt(values[23]);
            case NIXE_FIGURE -> parseInt(values[24]);
            case HYLIX_FIGURE -> parseInt(values[25]);
            case EXECUTIVE_FIGURE -> parseInt(values[26]);
            case RASHO_FIGURE -> parseInt(values[27]);
            case AUTUMN_CROWN -> parseInt(values[28]);
            case AUTUMN_PLUSHIE -> parseInt(values[29]);
            case AUTUMN_TITLE -> parseInt(values[30]);
            case HALLOWEEN_BEANIE -> parseInt(values[31]);
            case HALLOWEEN_BASKET -> parseInt(values[32]);
            case HALLOWEEN_TITLE -> parseInt(values[33]);
            case WINTER_HAT -> parseInt(values[34]);
            case WINTER_PLUSHIE -> parseInt(values[35]);
            case WINTER_TITLE -> parseInt(values[36]);
            case CHRISTMAS_HAT -> parseInt(values[37]);
            case CHRISTMAS_CANE -> parseInt(values[38]);
            case CHRISTMAS_TITLE -> parseInt(values[39]);
            case ANIVERSARY_HAT -> parseInt(values[40]);
            case ANIVERSARY_CAKE -> parseInt(values[41]);
            case ANIVERSARY_TITLE -> parseInt(values[42]);
            case VALENTINES_CROWN -> parseInt(values[43]);
            case VALENTINES_BOW -> parseInt(values[44]);
            case VALENTINES_TITLE -> parseInt(values[45]);
            case LEPRECHAUN_HAT -> parseInt(values[46]);
            case LEPRECHAUN_TREASURES -> parseInt(values[47]);
            case LEPRECHAUN_TITLE -> parseInt(values[48]);
            default -> throw new IndexOutOfBoundsException();
        };
    }

    private static int getStoreItemValue(ItemStack stack){
        String tempString = null;
        return switch(tempString){
            case "Premium Token" -> parseInt(values[0]);
            case "Mythical Box" -> parseInt(values[1]);
            case "Epic Box" -> parseInt(values[2]);
            case "Rare Box" -> parseInt(values[3]);
            default -> throw new IndexOutOfBoundsException();
        };
    }

    private static long getToday() throws ParseException {
        String endDateSource = (Calendar.getInstance().get(Calendar.MONTH) + 1) + "-" + Calendar.getInstance().get(Calendar.DAY_OF_MONTH) + "-" + Calendar.getInstance().get(Calendar.YEAR);
        sheetDate = endDateSource;
        month = TooltipProcessor.getMonth(Calendar.getInstance().get(Calendar.MONTH) + 1);
        day = String.valueOf(Calendar.getInstance().get(Calendar.DAY_OF_MONTH));
        SimpleDateFormat obj = new SimpleDateFormat("MM-dd-yyyy");
        Date startDate = obj.parse("6-20-2021");
        Date endDate = obj.parse(endDateSource);
        long difference = endDate.getTime() - startDate.getTime();
        long days_difference = TimeUnit.MILLISECONDS.toDays(difference);
        return days_difference + 1;
    }
}
