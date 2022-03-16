package io.github.blobanium.mineclubexpanded.util.tooltip;

import io.github.blobanium.mineclubexpanded.util.config.ConfigReader;
import io.github.blobanium.mineclubexpanded.util.feature.SerialIDProcessor;
import io.github.blobanium.mineclubexpanded.util.spreadsheet.SpreadsheetValueParser;
import net.minecraft.client.item.TooltipContext;
import net.minecraft.item.ItemStack;
import net.minecraft.text.LiteralText;
import net.minecraft.text.Text;

import java.util.List;

public class TooltipInjector {

    //context is never used, but however is required for the mod to compile properly
    public static void inject(ItemStack stack, TooltipContext context, List<Text> text){
        String id = SerialIDProcessor.getID(stack.getNbt());
        if(id != null && !ConfigReader.hideSerialID) {
            text.add(new LiteralText("ID: " + id));
        }
        if(stack.getNbt() != null && stack.getNbt().getCompound("PublicBukkitValues").getString(SerialIDProcessor.getKey()).equals("FAKE")){
            try {
                if(!SpreadsheetValueParser.hasLoadFailed) {
                    int value = SpreadsheetValueParser.getItemValue(stack);
                    text.add(new LiteralText("MC Bets " + SpreadsheetValueParser.month + " " + SpreadsheetValueParser.day + ": " + TooltipProcessor.simplifiedCount(value) + TooltipProcessor.getPrecentValue(stack)));
                } else {
                    text.add(new LiteralText("§a Failed to get MC Bets Spreadsheet data. Try restarting MC!"));
                }
            } catch (IndexOutOfBoundsException ignored){
                //This is technically ignored and will only show if a value does exist for that specified item.
            }
        }
    }
}
