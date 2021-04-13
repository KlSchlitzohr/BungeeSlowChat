package de.klschlitzohr.events;

import de.klschlitzohr.BungeeSlowChat;
import de.klschlitzohr.SlowChat;
import net.md_5.bungee.api.ChatMessageType;
import net.md_5.bungee.api.chat.TextComponent;
import net.md_5.bungee.api.connection.ProxiedPlayer;
import net.md_5.bungee.api.event.ChatEvent;
import net.md_5.bungee.api.plugin.Listener;
import net.md_5.bungee.event.EventHandler;

public class ChatListener implements Listener {

    private SlowChat slowChat;

    public ChatListener() {
        this.slowChat = BungeeSlowChat.getBungeeSlowChat().getSlowChat();
    }

    @EventHandler
    public void onChat(ChatEvent event) {

        ProxiedPlayer player = (ProxiedPlayer) event.getSender();

        if (!player.hasPermission("bsc.bypass")) {
            if (player.getServer().getInfo().getPlayers().size() >= slowChat.getPlayerCount()) {
                if (slowChat.getLastMessage().containsKey(player.getName())) {
                    long time = slowChat.getLastMessage().get(player.getName()) + slowChat.getDelay();
                    if (System.currentTimeMillis() < time) {
                        String secound = (int)((slowChat.getLastMessage().get(player.getName()) + slowChat.getDelay() - System.currentTimeMillis()) / 1000) + 1 + "s";
                        player.sendMessage(ChatMessageType.CHAT,new TextComponent(slowChat.getMessage().replace("%second%",secound)));
                        event.setCancelled(true);
                    }
                }
                slowChat.getLastMessage().put(player.getName(),System.currentTimeMillis());
            }
        }
    }
}
