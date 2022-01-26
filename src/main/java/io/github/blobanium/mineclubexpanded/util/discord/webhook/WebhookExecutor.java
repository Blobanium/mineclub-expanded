package io.github.blobanium.mineclubexpanded.util.discord.webhook;

import io.github.blobanium.mineclubexpanded.MineclubExpanded;
import io.github.blobanium.mineclubexpanded.util.config.ConfigReader;
import net.minecraft.client.MinecraftClient;

import java.io.IOException;
import java.util.concurrent.CompletableFuture;

public class WebhookExecutor {
    public static String uuid = MinecraftClient.getInstance().getSession().getUuid();
    public static String username = MinecraftClient.getInstance().getSession().getUsername();

    /*
    * Notice
    *
    * This Experiment is only to show a proof-of-concept of sending data to a remote discord server via a webhook.
    * Doing this can achieve many things: Such as events, tracking unobtainable rolls, real-time market tracking, etc.
    *
    * There are many flaws to this particular approach and is not the final product.
    * The Final product will most likely be executed through a remote verification server and then to discord, instead of having it being sent to discord directly.
     */
    public static void execute(String title, String description) throws IOException {
        CompletableFuture.runAsync(() -> {
            DiscordWebhook webhook = new DiscordWebhook(ConfigReader.webhookURL);
            webhook.setContent("Mineclub Expanded (" + username + ")");
            webhook.setAvatarUrl("https://mc-heads.net/avatar/"+ uuid + "/128");
            webhook.setUsername(username);
            webhook.setTts(false);
            webhook.addEmbed(new DiscordWebhook.EmbedObject()
                    .setTitle(title)
                    .setDescription(description)
                    .setAuthor(username, "https://www.curseforge.com/minecraft/mc-mods/mineclub-expanded", "https://mc-heads.net/avatar/"+ uuid + "/100"));
            try {
                webhook.execute(); //Handle exception
            } catch (IOException e) {
                MineclubExpanded.LOGGER.error("Failed To Execute Webhook.");
                e.printStackTrace();
            }
        });
    }


}
