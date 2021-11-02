package io.github.blobanium.mineclubexpanded.mixin;

import io.github.blobanium.mineclubexpanded.games.AutoGG;
import net.minecraft.client.sound.SoundInstance;
import net.minecraft.client.sound.SoundManager;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;

@Mixin(SoundManager.class)
public class SoundManagerMixin {
    @Inject(at = @At("HEAD"), method = "play")
    private void play(SoundInstance sound, CallbackInfo ci){
        AutoGG.autoGg(sound);
    }
}
