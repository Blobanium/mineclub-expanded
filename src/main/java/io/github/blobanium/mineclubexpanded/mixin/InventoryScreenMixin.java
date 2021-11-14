package io.github.blobanium.mineclubexpanded.mixin;

import io.github.blobanium.mineclubexpanded.global.WorldListener;
import io.github.blobanium.mineclubexpanded.util.discord.DiscordRP;
import net.minecraft.client.gui.screen.ingame.HandledScreen;
import net.minecraft.item.Item;
import net.minecraft.screen.slot.Slot;
import net.minecraft.screen.slot.SlotActionType;
import net.minecraft.text.Text;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;

import java.util.Arrays;

@Mixin(HandledScreen.class)
public class InventoryScreenMixin {
    private static String jsonDisplayName;

    @Inject(at = @At("HEAD"), method = "onMouseClick(Lnet/minecraft/screen/slot/Slot;IILnet/minecraft/screen/slot/SlotActionType;)V")
    protected void onMouseClick(Slot slot, int slotId, int button, SlotActionType actionType, CallbackInfo ci){
        //955
        try {
            if(Item.getRawId(slot.getStack().getItem()) == 955) {
                WorldListener.cancelHousingUpdate = true;
                jsonDisplayName = slot.getStack().getNbt().getCompound("display").getString("Name");
                DiscordRP.updateStatus("Currently In " + jsonDisplayName.substring(130).replace("\"}],\"text\":\"\"}", "") + "'s Home", "Playing On Mineclub");
            }
            }catch(NullPointerException ignored){
            //Supress Null Exception
        }
    }
}
