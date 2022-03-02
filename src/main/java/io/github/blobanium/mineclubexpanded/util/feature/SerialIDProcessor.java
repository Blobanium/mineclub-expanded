package io.github.blobanium.mineclubexpanded.util.feature;

import io.github.blobanium.mineclubexpanded.global.WorldID;
import io.github.blobanium.mineclubexpanded.util.config.ConfigReader;
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
            return getPrefixString() + serial + getDigits(serial);
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

    private static String getPrefixString(){
        return switch (ConfigReader.serialColor){
            case "Dark Blue" -> "§1#";
            case "Dark Green" -> "§2#";
            case "Dark Aqua" -> "§3#";
            case "Dark Red" -> "§4#";
            case "Dark Purple" -> "§5#";
            case "Gold" -> "§6#";
            case "Gray" -> "§7#";
            case "Dark Gray" -> "§8#";
            case "Blue" -> "§9#";
            case "Green" -> "§a#";
            case "Aqua" -> "§b#";
            case "Red" -> "§c#";
            case "Light Purple" -> "§d#";
            case "Yellow" -> "§e#";
            //case "White" -> "§f#"; (Ignored, Tooltip already outputs White text by default)
            default -> "#";
        };
    }

    private static String getDigits(int value){
        if(ConfigReader.showDigits){
            return " (" + String.valueOf(value).length() + ")";
        } else{
            return "";
        }
    }
}
