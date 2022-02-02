package io.github.blobanium.mineclubexpanded.util.command;

import net.fabricmc.fabric.api.client.command.v1.ClientCommandManager;
import net.minecraft.text.LiteralText;
import net.minecraft.text.Text;

import java.text.NumberFormat;

public class CommandParser {
    public static void registerCommand() {
        ClientCommandManager.DISPATCHER.register(
                ClientCommandManager.literal("mcex").executes(context -> {
                    context.getSource().sendFeedback(Text.Serializer.fromJson("[\"\",{\"text\":\"Mineclub Expanded\\n\"},{\"text\":\"Click here to see the full set of commands\",\"color\":\"light_purple\",\"clickEvent\":{\"action\":\"open_url\",\"value\":\"https://github.com/Blobanium/mineclub-expanded/wiki/List-Of-Commands\"}}]"));
                    return 0;
                })
        );

        ClientCommandManager.DISPATCHER.register(
                ClientCommandManager.literal("mcex").then(ClientCommandManager.literal("getminingvalue").executes(context -> {
                    context.getSource().sendFeedback(new LiteralText("§dCurrent value: §r" + NumberFormat.getInstance().format(Commands.getOreValue())));
                    return 0;
                        })
                ));

        ClientCommandManager.DISPATCHER.register(
                ClientCommandManager.literal("mcex").then(ClientCommandManager.literal("getminingvalue").then(ClientCommandManager.literal("deatiled").executes(context -> {
                            context.getSource().sendFeedback(new LiteralText("§dCurrent value: §r" + NumberFormat.getInstance().format(Commands.getOreValue())
                            + "\n §8Stone: §r" + Commands.getStoneCount + " (Value: " +  NumberFormat.getInstance().format(Commands.getStoneCount * Commands.STONE_VALUE) + ")"
                            + "\n §aJade: §r" + Commands.getJadeCount + " (Value: " + NumberFormat.getInstance().format(Commands.getJadeCount * Commands.JADE_VALUE) + ")"
                                    + "\n §bSapphire: §r" + Commands.getSapphireCount + " (Value: " + NumberFormat.getInstance().format(Commands.getSapphireCount * Commands.SAPPHIRE_VALUE) + ")"
                                    + "\n §dAmethyst: §r" + Commands.getAmethystCount + " (Value: " + NumberFormat.getInstance().format(Commands.getAmethystCount * Commands.AMETHYST_VALUE) + ")"
                                    + "\n §cRuby: §r" + Commands.getRubyCount + " (Value: " + NumberFormat.getInstance().format(Commands.getRubyCount * Commands.RUBY_VALUE) + ")"
                            ));
                            return 0;
                        })
                )));

        ClientCommandManager.DISPATCHER.register(
                ClientCommandManager.literal("mcex").then(ClientCommandManager.literal("yourmom").executes(context -> {
                            context.getSource().sendFeedback(new LiteralText("No your mom."));
                            return 0;
                        })
                ));
    }
}
