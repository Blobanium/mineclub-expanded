package io.github.blobanium.mineclubexpanded.mixin;

import io.github.blobanium.mineclubexpanded.games.AutoGG;
import net.minecraft.client.gui.hud.InGameHud;
import net.minecraft.client.gui.hud.SpectatorHud;
import net.minecraft.client.util.math.MatrixStack;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.item.Items;
import org.spongepowered.asm.mixin.Final;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Shadow;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;

@Mixin(InGameHud.class)
public class InGameHudMixin {
    private static boolean hasFeather = false;
    private static boolean hasCompass = false;
    private static boolean hasIceBlock = false;
    private static int featherCountdown = 10;
    private static int compassCountdown = 10;
    private static int iceBlockCountdown = 10;

    @Inject(at = @At("TAIL"), method = "renderHotbarItem")
    private void renderHotbarItem(int x, int y, float tickDelta, PlayerEntity player, ItemStack stack, int seed, CallbackInfo ci){
        int itemID = Item.getRawId(stack.getItem());

            if (itemID == 733) {
                if(!hasFeather) {
                    hasFeather = true;
                    System.out.println("Feather Truified!");
                }
                featherCountdown = 10;
            } else {
                if (featherCountdown == 0) {
                    if(hasFeather) {
                        hasFeather = false;
                        featherCountdown = 10;
                        System.out.println("Feather Falsified");
                    }
                } else {
                    featherCountdown = featherCountdown - 1;
                }
            }

            if (itemID == 795) {
                if(!hasCompass) {
                    hasCompass = true;
                    System.out.println("Compass Truified");
                }
                compassCountdown = 10;
            } else {
                if (compassCountdown == 0) {
                    if(hasCompass) {
                        hasCompass = false;
                        System.out.println("Compass Falsified");
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
                System.out.println("Spectator mode is on");
            } else {
                AutoGG.isSpectatorMode = false;
                System.out.println("Spectator mode is off");
            }
        }
    }
}
