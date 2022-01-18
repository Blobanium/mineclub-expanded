package io.github.blobanium.mineclubexpanded.util.command;

import net.fabricmc.fabric.api.client.command.v1.ClientCommandManager;
import net.minecraft.client.MinecraftClient;
import net.minecraft.item.Item;
import net.minecraft.text.LiteralText;

import java.text.NumberFormat;

public class CommandParser {
    public static void registerCommand() {
        ClientCommandManager.DISPATCHER.register(
                ClientCommandManager.literal("mcex").then(ClientCommandManager.literal("getminingvalue").executes(context -> {
                    context.getSource().sendFeedback(new LiteralText("§dCurrent value: §r" + NumberFormat.getInstance().format(Commands.getOreValue())));
                    return 0;
                        })
                ));
    }
}
