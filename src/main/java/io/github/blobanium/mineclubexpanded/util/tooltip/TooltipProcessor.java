package io.github.blobanium.mineclubexpanded.util.tooltip;

import io.github.blobanium.mineclubexpanded.util.spreadsheet.SpreadsheetValueParser;
import net.minecraft.item.ItemStack;
import net.minecraft.item.Items;
import net.minecraft.nbt.NbtElement;
import java.lang.Integer;

import static java.lang.Integer.parseInt;

public class TooltipProcessor {
    public static String getPrecentValue(ItemStack stack){
        try {
            int mineclubValue = parseInt(stack.getNbt().getCompound("display").getList("Lore", NbtElement.STRING_TYPE).getString(getIndex(stack)).substring(35).replace("ꌆ鲭\"}", "").replace(",", ""));
            int spreadsheetValue = SpreadsheetValueParser.getItemValue(stack);
            float precent = (((((float) mineclubValue / spreadsheetValue) - 1) * 100));
            if (precent != Math.abs(precent)) {
                return " §c(" + String.format("%.2f", precent) + "%)";
            } else {
                return " §a(" + String.format("%.2f", precent) + "%)";
            }
        }catch (NumberFormatException e){
            return  "";
        }
    }

    private static int getIndex(ItemStack stack){
        if(stack.getItem() == Items.LEATHER_HORSE_ARMOR){
            return 4;
        }else{
            return 5;
        }
    }

    public static String simplifiedCount(long count) {
        if (count < 1000) return "" + count;
        int exp = (int) (Math.log(count) / Math.log(1000));
        int exp2 = (int) (Math.log(count) / Math.log(10));
        int decimal = exp2 - (exp*3);
        return String.format("%."+ getDecimalPlace(decimal) +"f%c",
                count / Math.pow(1000, exp),
                "kMBTqQ".charAt(exp-1));
    }

    private static int getDecimalPlace(int value){
        return switch (value){
            case 0 -> 2;
            case 1 -> 1;
            case 2 -> 0;
            default -> (-(value) + 2);
        };
    }

    public static String getMonth(int month){
        return switch(month){
            case 1 -> "January";
            case 2 -> "Febuary";
            case 3 -> "March";
            case 4 -> "April";
            case 5 -> "May";
            case 6 -> "June";
            case 7 -> "July";
            case 8 -> "August";
            case 9 -> "September";
            case 10 -> "October";
            case 11 -> "November";
            case 12 -> "December";
            default -> "???";
        };
    }
}
