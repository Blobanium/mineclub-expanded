package io.github.blobanium.mineclubexpanded.mixin;

import io.github.blobanium.mineclubexpanded.MineclubExpanded;
import io.github.blobanium.mineclubexpanded.global.WorldListener;
import io.github.blobanium.mineclubexpanded.util.tick.TickTracker;
import net.minecraft.client.Keyboard;
import net.minecraft.client.MinecraftClient;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;

@Mixin(Keyboard.class)
public class KeyboardMixin {
    @Inject(at = @At("HEAD"), method = "onKey")
    private void onKey(long window, int key, int scancode, int action, int modifiers, CallbackInfo ci){
        //TODO Figure out keypresses
        //ESC = 256, Enter/Return = 257
        if(key == 256){
            MineclubExpanded.isChatOpen = false;
        }

        if(key == 257){
            if(WorldListener.isInHousing){
                if(MineclubExpanded.lastChatField.startsWith("/home")) {
                    TickTracker.setReminder(3);
                }
            }
        }
    }

}
