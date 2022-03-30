package io.github.blobanium.mineclubexpanded.mixin;

import io.github.blobanium.mineclubexpanded.games.adminevent.AdminEventDecoder;
import net.minecraft.client.gui.hud.BossBarHud;
import net.minecraft.client.util.math.MatrixStack;
import net.minecraft.entity.boss.BossBar;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;

@Mixin(BossBarHud.class)
public class BossBarHudMixin {

    @Inject(at = @At("HEAD"), method = "renderBossBar")
    private void renderBossBar(MatrixStack matrices, int x, int y, BossBar bossBar, CallbackInfo ci){
        String text = bossBar.getName().getString();
        if(!AdminEventDecoder.lastString.equals(text)){
            AdminEventDecoder.lastString = text;
        }
    }
}