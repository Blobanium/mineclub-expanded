package io.github.blobanium.mineclubexpanded.util.tooltip;

import io.github.blobanium.mineclubexpanded.util.spreadsheet.SpreadsheetValueParser;
import net.minecraft.item.ItemStack;
import net.minecraft.item.Items;
import net.minecraft.nbt.NbtElement;
import java.lang.Integer;

import static java.lang.Integer.parseInt;

public class TooltipProcessor {
    public static String getPrecentValue(ItemStack stack){
        int mineclubValue = parseInt(stack.getNbt().getCompound("display").getList("Lore", NbtElement.STRING_TYPE).getString(getIndex(stack)).substring(35).replace("ꌆ鲭\"}", "").replace(",", ""));
        int spreadsheetValue = SpreadsheetValueParser.getItemValue(stack.getNbt().getInt("CustomModelData"), stack);
        float precent = (((((float) mineclubValue / spreadsheetValue) - 1) * 100));
        return String.format("%.2f", precent) + "%";
    }

    private static int getIndex(ItemStack stack){
        if(stack.getItem() == Items.LEATHER_HORSE_ARMOR){
            return 4;
        }else{
            return 5;
        }
    }
}
