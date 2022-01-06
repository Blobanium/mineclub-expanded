package io.github.blobanium.mineclubexpanded.util.mixinhelper;

import io.github.blobanium.mineclubexpanded.housing.HousingRichPresenceListener;
import net.minecraft.item.Item;
import net.minecraft.screen.slot.Slot;

public class InventoryItemIdentifier {
    private static String fullname;

    public static void checkItem(Slot slot){
        //Player Heads have a Raw ID of 955
        if(slot != null){
            if(Item.getRawId(slot.getStack().getItem()) == 955 && slot.getStack().getNbt().getCompound("display").getString("Name").length() != 0) {
                fullname = slot.getStack().getNbt().getCompound("display").getString("Name").substring(130).replace("\"}],\"text\":\"\"}", "");
                if(fullname.length() <= 16){
                    HousingRichPresenceListener.playerheadName = fullname;
                } else {
                    HousingRichPresenceListener.playerheadName = GradientHelper.convertGradientToString(fullname);
                }
            }
        }
    }
}
