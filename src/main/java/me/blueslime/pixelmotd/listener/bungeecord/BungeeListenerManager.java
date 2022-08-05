package me.blueslime.pixelmotd.listener.bungeecord;

import me.blueslime.pixelmotd.ListenerManager;
import me.blueslime.pixelmotd.PixelMOTD;
import me.blueslime.pixelmotd.listener.Ping;
import me.blueslime.pixelmotd.listener.bungeecord.events.type.login.NormalLoginListener;
import me.blueslime.pixelmotd.listener.bungeecord.events.ProxyPingListener;
import me.blueslime.pixelmotd.listener.bungeecord.events.abstracts.AbstractLoginListener;
import me.blueslime.pixelmotd.listener.bungeecord.events.abstracts.AbstractServerConnectListener;
import me.blueslime.pixelmotd.listener.bungeecord.events.type.server.NormalServerListener;
import dev.mruniverse.slimelib.logs.SlimeLogs;
import net.md_5.bungee.api.plugin.Plugin;
import net.md_5.bungee.api.plugin.PluginManager;

public class BungeeListenerManager implements ListenerManager {

    private final PixelMOTD<Plugin> slimePlugin;

    private final ProxyPingListener listener;

    private final AbstractLoginListener loginListener;

    private final AbstractServerConnectListener serverListener;

    private final SlimeLogs logs;

    @SuppressWarnings("unchecked")
    public <T> BungeeListenerManager(PixelMOTD<T> plugin, SlimeLogs logs) {
        this.logs = logs;

        this.slimePlugin   = (PixelMOTD<Plugin>) plugin;

        this.listener      = new ProxyPingListener(slimePlugin, logs);
        this.loginListener = new NormalLoginListener(slimePlugin);
        this.serverListener= new NormalServerListener(slimePlugin);


    }

    @Override
    public void register() {
        final Plugin plugin = slimePlugin.getPlugin();

        PluginManager manager = plugin.getProxy().getPluginManager();

        manager.registerListener(plugin, listener);
        manager.registerListener(plugin, loginListener);
        manager.registerListener(plugin, serverListener);

        logs.info("ProxyPingListener has been registered to the proxy.");
    }

    @Override
    public void update() {
        listener.update();
        loginListener.update();
        serverListener.update();
    }

    @Override
    public Ping getPing() {
        return listener;
    }

}