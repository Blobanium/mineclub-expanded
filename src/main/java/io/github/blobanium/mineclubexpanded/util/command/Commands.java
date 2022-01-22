package io.github.blobanium.mineclubexpanded.util.command;

import net.minecraft.client.MinecraftClient;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.util.collection.DefaultedList;

public class Commands {
    //Vanilla Item
    private static final int GOLD_NUGGET = 861;

    //Custom Model Data
    private static final int STONE = 22;
    private static final int GOLD = 23;
    private static final int SAPPHIRE = 24;
    private static final int AMETHYST = 25;
    private static final int RUBY = 26;

    //Values of each ores
    public static final int STONE_VALUE = 5;
    public static final int GOLD_VALUE = 10;
    public static final int SAPPHIRE_VALUE = 100;
    public static final int AMETHYST_VALUE = 250;
    public static final int RUBY_VALUE = 600;


    //The Ore Counts for each
    public static int getStoneCount = 0;
    public static int getGoldCount = 0;
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

                }else if(currentinv.get(i).getNbt().getInt("CustomModelData") == GOLD){
                    returnValue = returnValue + (GOLD_VALUE * currentinv.get(i).getCount());
                    getGoldCount = getGoldCount + currentinv.get(i).getCount();

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
        getGoldCount = 0;
        getSapphireCount = 0;
        getAmethystCount = 0;
        getRubyCount = 0;
    }
}
