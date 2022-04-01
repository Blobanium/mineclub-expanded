package io.github.blobanium.mineclubexpanded.util.sound;

import io.github.blobanium.mineclubexpanded.util.config.ConfigReader;
import net.minecraft.client.MinecraftClient;
import net.minecraft.client.sound.PositionedSoundInstance;
import net.minecraft.sound.SoundEvent;
import net.minecraft.sound.SoundEvents;
import net.minecraft.util.Identifier;
import net.minecraft.util.registry.Registry;

import java.math.BigDecimal;
import java.math.RoundingMode;


public class SoundPlayer {

    public static void playSound(float pitch, SoundEvent event){
        SoundEvent sound = registerSound(event);
        PositionedSoundInstance posSound = PositionedSoundInstance.master(sound, pitch, getVolume(ConfigReader.outbidVolume));
        MinecraftClient.getInstance().getSoundManager().play(posSound);
    }

    private static SoundEvent registerSound(SoundEvent event){
        Identifier soundId = new Identifier(event.getId().toString());
        return Registry.SOUND_EVENT.get(soundId);
    }

    private static float getVolume(int volume){
        return new BigDecimal(volume).divide(new BigDecimal(100), 2, RoundingMode.CEILING).floatValue();
    }
}
