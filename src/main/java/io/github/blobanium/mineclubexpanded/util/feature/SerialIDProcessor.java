package io.github.blobanium.mineclubexpanded.util.feature;

import io.github.blobanium.mineclubexpanded.global.WorldID;
import net.minecraft.nbt.NbtCompound;

import java.util.UUID;

public class SerialIDProcessor {
    public static String getID(NbtCompound nbt){
        if(nbt != null) {
            String clubId = nbt.getCompound("PublicBukkitValues").getString(getKey());
            if (nbt == null || clubId.equals("MINECOIN") || clubId.equals("") || clubId.equals("FAKE")) {
                return null;
            }
            int preSerial = UUID.fromString(clubId).hashCode();
            int serial = Math.abs(preSerial);
            if(preSerial == serial) {
                return "§9#" + serial;
            } else {
                return "§d#" + serial;
            }
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
