package io.github.blobanium.mineclubexpanded.util.command.commnads;

import net.minecraft.client.MinecraftClient;
import net.minecraft.entity.player.PlayerInventory;
import net.minecraft.item.ItemStack;
import net.minecraft.util.collection.DefaultedList;

import java.util.UUID;

public class ItemSerialID {
    public static String getItemSerial(){
        PlayerInventory currentinv = MinecraftClient.getInstance().player.getInventory();
        String clubId = currentinv.main.get(currentinv.selectedSlot).getNbt().getCompound("PublicBukkitValues").getString("mineclub-lobby:club_id");
        int serial = Math.abs(UUID.fromString(clubId).hashCode());
        return "#" + serial;
    }
}
