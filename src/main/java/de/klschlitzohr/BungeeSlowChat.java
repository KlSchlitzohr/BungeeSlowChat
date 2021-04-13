package de.klschlitzohr;

import de.klschlitzohr.events.ChatListener;
import de.klschlitzohr.events.PlayerDisconnectListener;
import lombok.Getter;
import net.md_5.bungee.api.plugin.Plugin;

@Getter
public class BungeeSlowChat extends Plugin {

    @Getter
    private static BungeeSlowChat bungeeSlowChat;
    private SlowChat slowChat;

    @Override
    public void onEnable() {
        super.onEnable();
        bungeeSlowChat = this;
        slowChat = new SlowChat();
        getProxy().getPluginManager().registerListener(this,new ChatListener());
        getProxy().getPluginManager().registerListener(this,new PlayerDisconnectListener());
        System.out.println("Successfully loaded BungeeSlowChat.");
    }

    @Override
    public void onDisable() {
        super.onDisable();
        System.out.println("Successfully unloaded BungeeSlowChat.");
    }
}
