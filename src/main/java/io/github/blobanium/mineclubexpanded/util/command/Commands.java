package io.github.blobanium.mineclubexpanded.util.command;

import net.minecraft.client.MinecraftClient;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.util.collection.DefaultedList;

public class Commands {
    private static final int GOLD_NUGGET = 861;

    private static final int STONE = 22;
    private static final int GOLD = 23;
    private static final int SAPPHIRE = 24;
    private static final int AMETHYST = 25;
    private static final int RUBY = 26;

    private static final int STONE_VALUE = 5;
    private static final int GOLD_VALUE = 10;
    private static final int SAPPHIRE_VALUE = 100;
    private static final int AMETHYST_VALUE = 250;
    private static final int RUBY_VALUE = 600;

    public static int getOreValue(){
        DefaultedList<ItemStack> currentinv = MinecraftClient.getInstance().player.getInventory().main;
        int returnValue = 0;
        for (int i = 0; i <= 35; ++i) {
            if(Item.getRawId(currentinv.get(i).getItem()) == 861){
                if(currentinv.get(i).getNbt().getInt("CustomModelData") == STONE){
                    returnValue = returnValue + (STONE_VALUE * currentinv.get(i).getCount());

                }else if(currentinv.get(i).getNbt().getInt("CustomModelData") == GOLD){
                    returnValue = returnValue + (GOLD_VALUE * currentinv.get(i).getCount());

                }else if(currentinv.get(i).getNbt().getInt("CustomModelData") == SAPPHIRE){
                    returnValue = returnValue + (SAPPHIRE_VALUE * currentinv.get(i).getCount());

                }else if(currentinv.get(i).getNbt().getInt("CustomModelData") == AMETHYST){
                    returnValue = returnValue + (AMETHYST_VALUE * currentinv.get(i).getCount());

                }else if(currentinv.get(i).getNbt().getInt("CustomModelData") == RUBY){
                    returnValue = returnValue + (RUBY_VALUE * currentinv.get(i).getCount());
                }
            }
        }
        return returnValue;
    }
}