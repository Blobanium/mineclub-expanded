package io.github.blobanium.mineclubexpanded.util.mixinhelper;

import io.github.blobanium.mineclubexpanded.MineclubExpanded;
import io.github.blobanium.mineclubexpanded.games.AutoGG;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;

public class HotbarListener {
    private static boolean hasFeather = false;
    private static boolean hasCompass = false;
    private static int featherCountdown = 10;
    private static int compassCountdown = 10;

    public static void listenHotbar(ItemStack stack){
        int itemID = Item.getRawId(stack.getItem());

        hasFeather = isItemInHotbar(itemID, 733, hasFeather, featherCountdown);
        hasCompass = isItemInHotbar(itemID, 795, hasCompass, compassCountdown);


        if ((hasFeather && hasCompass) != AutoGG.isSpectatorMode){
            if(hasFeather && hasCompass){
                AutoGG.isSpectatorMode = true;
                MineclubExpanded.LOGGER.debug("Spectator mode is on");
            } else {
                AutoGG.isSpectatorMode = false;
                MineclubExpanded.LOGGER.debug("Spectator mode is off");
            }
        }
    }

    private static boolean isItemInHotbar(int itemID, int targetItemID, boolean currentValue, int countdown){
        if (itemID == targetItemID) {
            if(!currentValue) {
                returnCountown(targetItemID, countdown);
                return true;
            }
            countdown = 10;
        } else {
            if (countdown == 0) {
                if(currentValue) {
                    returnCountown(targetItemID, countdown);
                    return false;
                } else {
                    countdown = 10;
                }
            } else {
                countdown = countdown - 1;
            }
        }
        returnCountown(targetItemID, countdown);
        return currentValue;
    }

    private static void returnCountown(int targetItemID, int countdown){
        if(targetItemID == 733){
            featherCountdown = countdown;
        }

        if(targetItemID == 795){
            compassCountdown = countdown;
        }
    }
}
