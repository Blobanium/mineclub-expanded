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

            if (itemID == 733) {
                if(!hasFeather) {
                    hasFeather = true;
                    MineclubExpanded.LOGGER.debug("Feather Truified!");
                }
                featherCountdown = 10;
            } else {
                if (featherCountdown == 0) {
                    if(hasFeather) {
                        hasFeather = false;
                        featherCountdown = 10;
                        MineclubExpanded.LOGGER.debug("Feather Falsified");
                    }
                } else {
                    featherCountdown = featherCountdown - 1;
                }
            }

            if (itemID == 795) {
                if(!hasCompass) {
                    hasCompass = true;
                    MineclubExpanded.LOGGER.debug("Compass Truified");
                }
                compassCountdown = 10;
            } else {
                if (compassCountdown == 0) {
                    if(hasCompass) {
                        hasCompass = false;
                        MineclubExpanded.LOGGER.debug("Compass Falsified");
                    } else {
                        compassCountdown = 10;
                    }
                } else {
                    compassCountdown = compassCountdown - 1;
                }
            }

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
}