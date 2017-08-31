package net.frostedop.frostedopmod;

import net.frostedop.frostedopmod.bridge.WorldEditBridge;
import net.frostedop.frostedopmod.listeners.FBlockListener;
import net.frostedop.frostedopmod.commands.FCommandLoader;
import net.frostedop.frostedopmod.config.ConfigEntry;
import static net.frostedop.frostedopmod.config.ConfigEntry.T_AUTODAY;
import static net.frostedop.frostedopmod.config.ConfigEntry.T_NO_RAIN;
import net.frostedop.frostedopmod.config.ConfigFiles;
import net.frostedop.frostedopmod.listeners.FBanListener;
import net.frostedop.frostedopmod.listeners.FChatListener;
import net.frostedop.frostedopmod.listeners.FMainListener;
import net.frostedop.frostedopmod.listeners.FPlayerListener;
import net.frostedop.frostedopmod.listeners.FRankListener;
import net.frostedop.frostedopmod.worlds.WorldManager;
import org.bukkit.Bukkit;
import org.bukkit.configuration.file.FileConfiguration;
import org.bukkit.entity.Player;
import org.bukkit.plugin.java.JavaPlugin;
import org.bukkit.scheduler.BukkitRunnable;

public class FrostedOPMod extends JavaPlugin {

    public static FrostedOPMod plugin;

    @Override
    public void onLoad() {
        plugin = this;
        ConfigFiles.setup();
    }

    @Override
    @SuppressWarnings("ResultOfObjectAllocationIgnored")
    public void onEnable() {
        ConfigFiles.setup();
        FLog.info("FrostedOPMod has been enabled!");
        FLog.info("Created by: Savnith");
        FLog.info("Plugin State: " + FUtil.NOT_STABLE);

        // Start Main Services
        this.weather();
        new WorldEditBridge();
        new WorldManager();
        new FBlockListener();
        new FBlockListener();
        new FChatListener();
        new FMainListener();
        new FPlayerListener();
        new FRankListener();
        new FCommandLoader();
        new FBanListener();

        try {
            
            if (ConfigEntry.MainConfig().getBoolean("announcer.enabled")) {
                FAnnouncer.Broadcast(this);
            } else {
                // ignore this
            }
        } catch (Exception e) {
            FLog.severe(e);
        }
        
        if (!Bukkit.getPluginManager().isPluginEnabled("WorldEdit")) {
            FLog.info("WorldEdit cannot be found, expect errors!");
        }

        // If the player is a imposter add them.
        for (Player player : Bukkit.getOnlinePlayers()) {
            FileConfiguration config = ConfigFiles.getAdmins().getConfig();
            if (config.getBoolean(player.getUniqueId().toString() + ".imposter")) {
                FUtil.imposters.add(player.getName());
            }
        }
    }

    @Override
    public void onDisable() {
        // do nothing as there is nothing to stop?
    }

    void weather() {
        for (final Player all : Bukkit.getOnlinePlayers()) {
            if (ConfigEntry.MainConfig().getBoolean(T_NO_RAIN)) {
                new BukkitRunnable() {
                    @Override
                    public void run() {
                        all.getWorld().setWeatherDuration(0);
                        all.getWorld().setThunderDuration(0);
                    }
                }.runTaskTimer(this, 0L, 1L);
            }
            if (ConfigEntry.MainConfig().getBoolean(T_AUTODAY)) {
                new BukkitRunnable() {
                    @Override
                    public void run() {
                        Bukkit.getOnlinePlayers().stream().forEach((all) -> {
                            all.getWorld().setTime(600L);
                        });
                    }
                }.runTaskTimer(this, 0L, 1L);
            }
        }
    }
}
