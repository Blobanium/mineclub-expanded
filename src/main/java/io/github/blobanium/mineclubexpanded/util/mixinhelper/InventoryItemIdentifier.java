package io.github.blobanium.mineclubexpanded.util.mixinhelper;

import io.github.blobanium.mineclubexpanded.housing.HousingRichPresenceListener;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.screen.slot.Slot;

public class InventoryItemIdentifier {
    private static String fullname;

    public static void checkItem(Slot slot){
        //Player Heads have a Raw ID of 955
        if(slot != null && slot.getStack().getNbt() != null){
            ItemStack stack = slot.getStack();
            String namestring = stack.getNbt().getCompound("display").getString("Name");
            if(Item.getRawId(stack.getItem()) == 955 && namestring.length() != 0) {
                fullname = namestring.substring(130).replace("\"}],\"text\":\"\"}", "");
                if(fullname.length() <= 16){
                    HousingRichPresenceListener.playerheadName = fullname;
                } else {
                    HousingRichPresenceListener.playerheadName = GradientHelper.convertGradientToString(fullname);
                }
            }
        }
    }
}
