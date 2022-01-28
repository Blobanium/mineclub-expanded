package io.github.blobanium.mineclubexpanded.util.discord;

import com.jagrosh.discordipc.IPCClient;
import com.jagrosh.discordipc.IPCListener;
import com.jagrosh.discordipc.entities.RichPresence;
import com.jagrosh.discordipc.exceptions.NoDiscordClientException;
import io.github.blobanium.mineclubexpanded.MineclubExpanded;
import io.github.blobanium.mineclubexpanded.util.config.ConfigReader;
import net.minecraft.client.MinecraftClient;

import java.time.OffsetDateTime;

public class DiscordRP {
    static final IPCClient client = new IPCClient(907142070140035102L);
    static RichPresence.Builder builder = new RichPresence.Builder();
    public static boolean hasRPStarted = false;
    public static boolean hasBlankStatus = true;
    public static int discordRPErrorcode = 0;
    //0 = Normal State, 1 = Error, 2 = MacOS

    public static void startRP() {
        setup(client);
    }

    public static void updateStatus(String state, String details){
        if(ConfigReader.richPresence) {
            updateStatusInternal(state, details);
            hasBlankStatus = false;
        }
    }

    public static void clearStatus(){
        updateStatusInternal("","");
        hasBlankStatus = true;
    }

    private static void updateStatusInternal(String state, String details){
        if(discordRPErrorcode == 0) {
            try {
                builder.setState(state)
                        .setDetails(details)
                        .setStartTimestamp(OffsetDateTime.now())
                        .setLargeImage("icon_new", getPresenceImageText())
                        .addButton("Get Mineclub Expanded", "https://modrinth.com/mod/mineclub-expanded");
                client.sendRichPresence(builder.build());
            } catch (IllegalStateException e) {
                MineclubExpanded.LOGGER.error("IPC not connected! Attempting to reconnect IPC");
                connectClient();
            }
        }
    }

    private static void setup(IPCClient client){
        client.setListener(new IPCListener(){
            @Override
            public void onReady(IPCClient client)
            {
                hasRPStarted = true;
                MineclubExpanded.LOGGER.info("Mineclub Rich presence Ready!");
            }
        });
        connectClient();
    }

    public static void connectClient(){
        try {
            client.connect();
        } catch (NoDiscordClientException e) {
            MineclubExpanded.LOGGER.error("Unable To Connect To Discord Client");
            e.printStackTrace();
        }
    }

    public static String defaultDetails(){
        if(ConfigReader.rpCustomDetails.equals("ServerIP")){
            return "IP: play.mineclub.com (1.17+)";
        }else if(ConfigReader.rpCustomDetails.equals("Username")){
            return "Playing as " + MinecraftClient.getInstance().getSession().getUsername();
        }else if(ConfigReader.rpCustomDetails.equals("Mod Version")){
            return "Using Version v" + MineclubExpanded.modVersion;
        }else{
            return "IP: play.mineclub.com (1.17+)";
        }
    }

    private static String getPresenceImageText(){
        if(ConfigReader.rpCustomDetails.equals("Mod Version")){
            return "play.mineclub.com";
        }else{
            return "v" + MineclubExpanded.modVersion;
        }
    }
}
