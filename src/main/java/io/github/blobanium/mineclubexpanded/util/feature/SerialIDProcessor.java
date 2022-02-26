package io.github.blobanium.mineclubexpanded.util.feature;

import io.github.blobanium.mineclubexpanded.global.WorldID;
import net.minecraft.nbt.NbtCompound;

import java.util.UUID;

public class SerialIDProcessor {
    public static String getID(NbtCompound nbt){
        if(nbt != null) {
            String clubId = nbt.getCompound("PublicBukkitValues").getString(getKey());
            if (nbt == null) {
                return null;
            } else if (clubId.equals("MINECOIN")) {
                return null;
            } else if (clubId.equals("")) {
                return "Your phone doesnt have a serial number";
            } else if (clubId.equals("FAKE")){
                return null;
            }
            int serial = Math.abs(UUID.fromString(clubId).hashCode());
            return "#" + serial;
        }
        return null;
    }

    private static String getKey(){
        if(WorldID.worldID == WorldID.HOUSING){
            return "mineclubhousing:club_id";
        }else{
            return "mineclub-lobby:club_id";
        }
    }
}
