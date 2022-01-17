package io.github.blobanium.mineclubexpanded.util.command;

import net.fabricmc.fabric.api.client.command.v1.ClientCommandManager;
import net.minecraft.text.LiteralText;

public class CommandParser {
    public static void registerCommand() {
        ClientCommandManager.DISPATCHER.register(
                ClientCommandManager.literal("mcex").then(ClientCommandManager.literal("getminingvalue").executes(context -> {
                    context.getSource().sendFeedback(new LiteralText("This command is still a work in progress!!"));
                    return 0;
                        })
                ));
    }
}
