package me.blueslime.pixelmotd.initialization.bungeecord;

import me.blueslime.pixelmotd.PixelMOTD;
import me.blueslime.pixelmotd.SlimeFile;
import me.blueslime.pixelmotd.status.ServerStatusChecker;
import me.blueslime.pixelmotd.status.StatusChecker;
import dev.mruniverse.slimelib.SlimePlatform;
import net.md_5.bungee.api.plugin.Plugin;

@SuppressWarnings("unused")
public final class BungeeMOTD extends Plugin {

    private static BungeeMOTD bungeeMOTD;

    private ServerStatusChecker checker = null;

    private PixelMOTD<Plugin> instance;

    @Override
    public void onEnable() {
        bungeeMOTD = this;

        instance = new PixelMOTD<>(
                SlimePlatform.BUNGEECORD,
                this,
                getDataFolder()
        );

        if (instance.getConfigurationHandler(SlimeFile.SETTINGS).getStatus("settings.server-status.toggle")) {
            checker = new ServerStatusChecker(instance);
        }
    }

    @Override
    public void onDisable() {
        instance.getLoader().shutdown();
    }

    public static BungeeMOTD getInstance() {
        return bungeeMOTD;
    }

    public StatusChecker getChecker() {
        return checker;
    }

    public PixelMOTD<Plugin> getPlugin() {
        return instance;
     }
}
