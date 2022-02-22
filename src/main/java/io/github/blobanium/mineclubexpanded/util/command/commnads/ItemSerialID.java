package io.github.blobanium.mineclubexpanded.util.command.commnads;

import io.github.blobanium.mineclubexpanded.global.WorldID;
import net.minecraft.client.MinecraftClient;
import net.minecraft.entity.player.PlayerInventory;
import net.minecraft.item.ItemStack;
import net.minecraft.nbt.NbtCompound;
import net.minecraft.util.collection.DefaultedList;

import java.util.UUID;

public class ItemSerialID {
    public static String getItemSerial(){
        PlayerInventory currentinv = MinecraftClient.getInstance().player.getInventory();
        NbtCompound nbt = currentinv.main.get(currentinv.selectedSlot).getNbt();
        String clubId = nbt.getCompound("PublicBukkitValues").getString(getKey());
        if(nbt == null){
            return "";
        }else if(clubId.equals("MINECOIN")){
            return "";
        }else if(clubId.equals("")){
            return "Your phone doesnt have a serial number";
        }
        int serial = Math.abs(UUID.fromString(clubId).hashCode());
        return "#" + serial;
    }

    private static String getKey(){
        if(WorldID.worldID == WorldID.HOUSING){
            return "mineclubhousing:club_id";
        }else{
            return "mineclub-lobby:club_id";
        }
    }
}
