package io.github.blobanium.mineclubexpanded.util.command;

import io.github.blobanium.mineclubexpanded.games.adminevent.AdminEventDecoder;
import io.github.blobanium.mineclubexpanded.util.command.commnads.GetMiningValue;
import net.fabricmc.fabric.api.client.command.v1.ClientCommandManager;
import net.minecraft.text.LiteralText;
import net.minecraft.text.Text;

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
                    context.getSource().sendFeedback(new LiteralText(GetMiningValue.miningValueResponseString(false)));
                    return 0;
                        })
                ));

        ClientCommandManager.DISPATCHER.register(
                ClientCommandManager.literal("mcex").then(ClientCommandManager.literal("getminingvalue").then(ClientCommandManager.literal("detailed").executes(context -> {
                            context.getSource().sendFeedback(new LiteralText(GetMiningValue.miningValueResponseString(true)));
                            return 0;
                        })
                )));

        ClientCommandManager.DISPATCHER.register(
                ClientCommandManager.literal("mcex").then(ClientCommandManager.literal("yourmom").executes(context -> {
                            context.getSource().sendFeedback(new LiteralText("No your mom."));
                            return 0;
                        })
                ));

        ClientCommandManager.DISPATCHER.register(
                ClientCommandManager.literal("mcex").then(ClientCommandManager.literal("adminevent").executes(context -> {
                    AdminEventDecoder.decodeBossBar();
                            context.getSource().sendFeedback(new LiteralText(
                                    "Game: " + AdminEventDecoder.adminEvent +
                                    "\nHost: " + AdminEventDecoder.host +
                                    "\nTime Left: " + AdminEventDecoder.timeleft));
                            return 0;
                        })
                ));
    }
}
