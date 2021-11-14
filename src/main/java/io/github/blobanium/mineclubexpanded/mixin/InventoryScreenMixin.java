package io.github.blobanium.mineclubexpanded.mixin;

import io.github.blobanium.mineclubexpanded.global.WorldListener;
import io.github.blobanium.mineclubexpanded.util.mixinhelper.GradientHelper;
import io.github.blobanium.mineclubexpanded.util.discord.DiscordRP;
import net.minecraft.client.gui.screen.ingame.HandledScreen;
import net.minecraft.item.Item;
import net.minecraft.screen.slot.Slot;
import net.minecraft.screen.slot.SlotActionType;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;

@Mixin(HandledScreen.class)
public class InventoryScreenMixin {
    private static String jsonDisplayName;
    private static String fullname;

    @Inject(at = @At("HEAD"), method = "onMouseClick(Lnet/minecraft/screen/slot/Slot;IILnet/minecraft/screen/slot/SlotActionType;)V")
    protected void onMouseClick(Slot slot, int slotId, int button, SlotActionType actionType, CallbackInfo ci){
        //Player Heads have a Raw ID of 955
        try {
            if(Item.getRawId(slot.getStack().getItem()) == 955) {
                jsonDisplayName = slot.getStack().getNbt().getCompound("display").getString("Name");
                fullname = jsonDisplayName.substring(130).replace("\"}],\"text\":\"\"}", "");
                if(fullname.length() <= 16){
                    WorldListener.cancelHousingUpdate = true;
                    DiscordRP.updateStatus("Currently In " + fullname + "'s Home", "Playing On Mineclub");
                } else {
                    WorldListener.cancelHousingUpdate = true;
                    DiscordRP.updateStatus("Currently In " + GradientHelper.convertGradientToString(fullname) + "'s Home", "Playing On Mineclub");
                }
            }
        }catch(NullPointerException ignored){
            //Supress Null Exception
        }
    }
}
