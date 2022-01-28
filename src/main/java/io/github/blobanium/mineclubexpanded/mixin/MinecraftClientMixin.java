package io.github.blobanium.mineclubexpanded.mixin;

import io.github.blobanium.mineclubexpanded.util.config.ConfigReader;
import io.github.blobanium.mineclubexpanded.util.tick.TickTracker;
import net.minecraft.client.MinecraftClient;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;

@Mixin(MinecraftClient.class)
public class MinecraftClientMixin {
    @Inject(at = @At("HEAD"), method = "tick")
    private void tick(CallbackInfo ci){
        TickTracker.onTick();
    }

    @Inject(at = @At("HEAD"), method = "stop")
    private void stop(CallbackInfo ci){
        ConfigReader.refreshConfig();
    }
}
