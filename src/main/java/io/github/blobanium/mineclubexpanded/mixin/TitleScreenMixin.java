package io.github.blobanium.mineclubexpanded.mixin;

import io.github.blobanium.mineclubexpanded.MineclubExpanded;
import io.github.blobanium.mineclubexpanded.util.config.ConfigReader;
import net.minecraft.client.MinecraftClient;
import net.minecraft.client.gui.screen.ConnectScreen;
import net.minecraft.client.gui.screen.TitleScreen;
import net.minecraft.client.network.ServerAddress;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;

@Mixin(TitleScreen.class)
public class TitleScreenMixin{
    private static boolean hasStarted = false;

    @Inject(at = @At("HEAD"), method = "init")
    private void init(CallbackInfo ci){
        if (!hasStarted && ConfigReader.expressConnect) {
            System.out.println("Connecting");
            ConnectScreen.connect(MinecraftClient.getInstance().currentScreen, MinecraftClient.getInstance(), new ServerAddress("play.mineclub.com", 25565), MineclubExpanded.mineclub);
        }
        hasStarted = true;
    }
}
