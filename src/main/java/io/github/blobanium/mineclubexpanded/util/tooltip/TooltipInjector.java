package io.github.blobanium.mineclubexpanded.util.tooltip;

import io.github.blobanium.mineclubexpanded.util.config.ConfigReader;
import io.github.blobanium.mineclubexpanded.util.feature.SerialIDProcessor;
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
        if(stack.getNbt().getCompound("PublicBukkitValues").getString(SerialIDProcessor.getKey()).equals("FAKE")){

        }
    }
}
