package de.klschlitzohr;

import lombok.Getter;
import lombok.SneakyThrows;
import net.md_5.bungee.api.connection.ProxiedPlayer;
import net.md_5.bungee.config.Configuration;
import net.md_5.bungee.config.ConfigurationProvider;
import net.md_5.bungee.config.YamlConfiguration;

import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.nio.file.Files;
import java.util.HashMap;

@Getter
public class SlowChat {

    private final BungeeSlowChat bungeeSlowChat;
    private final HashMap<String, Long> lastMessage;
    private String message;
    private int playerCount;
    private long delay;

    public SlowChat() {
        bungeeSlowChat = BungeeSlowChat.getBungeeSlowChat();
        lastMessage = new HashMap<>();
        playerCount = 5;
        delay = 1000;
        message = "Chat is in slow chat mode. You can write again %second%.";
        checkConfig();
        loadConfig();
    }

    private void checkConfig() {
        if (!bungeeSlowChat.getDataFolder().exists())
            bungeeSlowChat.getDataFolder().mkdir();

        File file = new File(bungeeSlowChat.getDataFolder(), "config.yml");


        if (!file.exists()) {
            try (InputStream in = bungeeSlowChat.getResourceAsStream("config.yml")) {
                Files.copy(in, file.toPath());
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }

    @SneakyThrows
    private void loadConfig() {
        Configuration configuration = ConfigurationProvider.getProvider(YamlConfiguration.class).load(new File(bungeeSlowChat.getDataFolder(), "config.yml"));
        playerCount = configuration.getInt("playercount");
        delay = configuration.getLong("delay");
        message = configuration.getString("message");
        message = message.replace("&", "\u00A7");
    }

    public void quit(ProxiedPlayer proxiedPlayer) {
        lastMessage.remove(proxiedPlayer.getName());
    }
}
