package io.github.blobanium.mineclubexpanded.util.command;

import net.minecraft.client.MinecraftClient;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.util.collection.DefaultedList;

import java.text.NumberFormat;

public class Commands {
    //Vanilla Item
    private static final int GOLD_NUGGET = 861;

    //Custom Model Data
    private static final int STONE = 22;
    private static final int JADE = 23;
    private static final int SAPPHIRE = 24;
    private static final int AMETHYST = 25;
    private static final int RUBY = 26;

    //Values of each ores
    public static final int STONE_VALUE = 5;
    public static final int JADE_VALUE = 10;
    public static final int SAPPHIRE_VALUE = 100;
    public static final int AMETHYST_VALUE = 250;
    public static final int RUBY_VALUE = 600;


    //The Ore Counts for each
    public static int getStoneCount = 0;
    public static int getJadeCount = 0;
    public static int getSapphireCount = 0;
    public static int getAmethystCount = 0;
    public static int getRubyCount = 0;


    public static int getOreValue(){
        DefaultedList<ItemStack> currentinv = MinecraftClient.getInstance().player.getInventory().main;
        resetOreCounts();
        int returnValue = 0;
        for (int i = 0; i <= 35; ++i) {
            if(Item.getRawId(currentinv.get(i).getItem()) == GOLD_NUGGET){
                if(currentinv.get(i).getNbt().getInt("CustomModelData") == STONE){
                    returnValue = returnValue + (STONE_VALUE * currentinv.get(i).getCount());
                    getStoneCount = getStoneCount + currentinv.get(i).getCount();

                }else if(currentinv.get(i).getNbt().getInt("CustomModelData") == JADE){
                    returnValue = returnValue + (JADE_VALUE * currentinv.get(i).getCount());
                    getJadeCount = getJadeCount + currentinv.get(i).getCount();

                }else if(currentinv.get(i).getNbt().getInt("CustomModelData") == SAPPHIRE){
                    returnValue = returnValue + (SAPPHIRE_VALUE * currentinv.get(i).getCount());
                    getSapphireCount = getSapphireCount + currentinv.get(i).getCount();

                }else if(currentinv.get(i).getNbt().getInt("CustomModelData") == AMETHYST){
                    returnValue = returnValue + (AMETHYST_VALUE * currentinv.get(i).getCount());
                    getAmethystCount = getAmethystCount + currentinv.get(i).getCount();

                }else if(currentinv.get(i).getNbt().getInt("CustomModelData") == RUBY){
                    returnValue = returnValue + (RUBY_VALUE * currentinv.get(i).getCount());
                    getRubyCount = getRubyCount + currentinv.get(i).getCount();
                }
            }
        }
        return returnValue;
    }

    private static void resetOreCounts(){
        getStoneCount = 0;
        getJadeCount = 0;
        getSapphireCount = 0;
        getAmethystCount = 0;
        getRubyCount = 0;
    }

    public static String miningValueResponseString(boolean detailed){
        if(!detailed){
            return miningValueResponseInternalString();
        } else {
            return miningValueResponseInternalString()
                    + "\n §8Stone: §r" + getStoneCount + " (Value: " +  NumberFormat.getInstance().format(getStoneCount * STONE_VALUE) + ")"
                    + "\n §aJade: §r" + getJadeCount + " (Value: " + NumberFormat.getInstance().format(getJadeCount * JADE_VALUE) + ")"
                    + "\n §bSapphire: §r" + getSapphireCount + " (Value: " + NumberFormat.getInstance().format(getSapphireCount * SAPPHIRE_VALUE) + ")"
                    + "\n §dAmethyst: §r" + getAmethystCount + " (Value: " + NumberFormat.getInstance().format(getAmethystCount * AMETHYST_VALUE) + ")"
                    + "\n §cRuby: §r" + getRubyCount + " (Value: " + NumberFormat.getInstance().format(getRubyCount * RUBY_VALUE) + ")";
        }
    }

    private static String miningValueResponseInternalString(){
        return "§dCurrent value: §r" + NumberFormat.getInstance().format(getOreValue());
    }
}
