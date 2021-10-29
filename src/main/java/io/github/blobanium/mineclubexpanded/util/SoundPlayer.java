package io.github.blobanium.mineclubexpanded.util;

import net.minecraft.client.MinecraftClient;
import net.minecraft.client.sound.PositionedSoundInstance;
import net.minecraft.sound.SoundEvent;
import net.minecraft.sound.SoundEvents;
import net.minecraft.util.Identifier;
import net.minecraft.util.registry.Registry;


public class SoundPlayer {
    public static void playSound(){
        SoundEvent sound = registerSound();
        PositionedSoundInstance posSound = PositionedSoundInstance.master(sound, 1.0F);
        MinecraftClient.getInstance().getSoundManager().play(posSound);
    }

    private static SoundEvent registerSound(){
        Identifier soundId = new Identifier(SoundEvents.ENTITY_EXPERIENCE_ORB_PICKUP.getId().toString());
        return Registry.SOUND_EVENT.get(soundId);
    }
}
