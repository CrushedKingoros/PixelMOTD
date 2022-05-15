package dev.justjustin.pixelmotd.listener.sponge;

import com.google.inject.Inject;
import dev.justjustin.pixelmotd.ListenerManager;
import dev.justjustin.pixelmotd.listener.sponge.events.ServerPingListener;
import dev.mruniverse.slimelib.SlimePlugin;
import org.spongepowered.api.Server;
import org.spongepowered.api.Sponge;
import org.spongepowered.plugin.PluginContainer;

@SuppressWarnings("unused")
public class SpongeListenerManager implements ListenerManager {

    private final SlimePlugin<Server> slimePlugin;

    @Inject
    private PluginContainer container;

    @SuppressWarnings("unchecked")
    public <T> SpongeListenerManager(T plugin) {
        this.slimePlugin = (SlimePlugin<Server>) plugin;
    }

    @Override
    public void register() {
        Sponge.eventManager().registerListeners(container, new ServerPingListener(slimePlugin));
    }
}