package io.github.blobanium.mineclubexpanded.util.mixinhelper;

import io.github.blobanium.mineclubexpanded.global.WorldListener;
import io.github.blobanium.mineclubexpanded.util.discord.DiscordRP;
import net.minecraft.item.Item;
import net.minecraft.screen.slot.Slot;

public class InventoryItemIdentifier {
    private static String jsonDisplayName;
    private static String fullname;

    public static void checkItem(Slot slot){
        //Player Heads have a Raw ID of 955
        if(slot != null){
            if(Item.getRawId(slot.getStack().getItem()) == 955) {
                jsonDisplayName = slot.getStack().getNbt().getCompound("display").getString("Name");
                fullname = jsonDisplayName.substring(130).replace("\"}],\"text\":\"\"}", "");
                if(fullname.length() <= 16){
                    WorldListener.cancelHousingUpdate = true;
                    DiscordRP.updateStatus("Currently In " + fullname + "'s Home", DiscordRP.defaultDetails);
                } else {
                    WorldListener.cancelHousingUpdate = true;
                    DiscordRP.updateStatus("Currently In " + GradientHelper.convertGradientToString(fullname) + "'s Home", DiscordRP.defaultDetails);
                }
            }
        }
    }
}
