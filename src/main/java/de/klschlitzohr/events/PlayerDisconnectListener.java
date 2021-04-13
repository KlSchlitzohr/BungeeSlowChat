package de.klschlitzohr.events;

import de.klschlitzohr.BungeeSlowChat;
import net.md_5.bungee.api.event.PlayerDisconnectEvent;
import net.md_5.bungee.api.plugin.Listener;
import net.md_5.bungee.event.EventHandler;

public class PlayerDisconnectListener implements Listener {

    @EventHandler
    public void onChat(PlayerDisconnectEvent event) {
        BungeeSlowChat.getBungeeSlowChat().getSlowChat().quit(event.getPlayer());
    }

}
