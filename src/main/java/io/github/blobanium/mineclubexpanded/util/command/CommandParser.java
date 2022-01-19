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
    }
}
