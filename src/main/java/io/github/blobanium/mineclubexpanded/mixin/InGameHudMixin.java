package io.github.blobanium.mineclubexpanded.mixin;

import io.github.blobanium.mineclubexpanded.MineclubExpanded;
import io.github.blobanium.mineclubexpanded.games.AutoGG;
import net.minecraft.client.gui.hud.InGameHud;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;

@Mixin(InGameHud.class)
public class InGameHudMixin {
    private static boolean hasFeather = false;
    private static boolean hasCompass = false;
    private static int featherCountdown = 10;
    private static int compassCountdown = 10;

    @Inject(at = @At("TAIL"), method = "renderHotbarItem")
    private void renderHotbarItem(int x, int y, float tickDelta, PlayerEntity player, ItemStack stack, int seed, CallbackInfo ci){
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

    private boolean isItemInHotbar(int itemID, int targetItemID, boolean currentValue, int countdown){
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
