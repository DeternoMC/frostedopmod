package net.frostedop.frostedopmod.listeners;

import net.frostedop.frostedopmod.FrostedOPMod;
import net.frostedop.frostedopmod.config.ConfigEntry;
import net.frostedop.frostedopmod.config.ConfigFiles;
import net.frostedop.frostedopmod.ranks.Rank;
import org.bukkit.Bukkit;
import org.bukkit.configuration.file.FileConfiguration;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerJoinEvent;
import org.bukkit.event.player.PlayerQuitEvent;

public class FPlayerListener implements Listener {

    FrostedOPMod plugin;

    @SuppressWarnings("LeakingThisInConstructor")
    public FPlayerListener() {
        Bukkit.getPluginManager().registerEvents(this, FrostedOPMod.plugin);
    }

    @EventHandler
    public void onPlayerJoin(PlayerJoinEvent event) {
        String UUID = event.getPlayer().getUniqueId().toString();
        FileConfiguration PlayerConfig = ConfigFiles.getPlayer().getConfig();

        if (!PlayerConfig.contains(UUID)) {
            PlayerConfig.set(UUID + ".name", event.getPlayer().getName().toLowerCase());
            PlayerConfig.set(UUID + ".ip", event.getPlayer().getAddress().getHostString());
            PlayerConfig.set(UUID + ".tag", null);
            PlayerConfig.set(UUID + ".muted", false);
            PlayerConfig.set(UUID + ".frozen", false);
            PlayerConfig.set(UUID + ".cmdsblcked", false);
            ConfigFiles.getPlayer().saveConfig();
        } else if (PlayerConfig.contains(UUID)) {
            PlayerConfig.set(UUID + ".name", event.getPlayer().getName().toLowerCase());
            PlayerConfig.set(UUID + ".ip", event.getPlayer().getAddress().getHostString());
            ConfigFiles.getPlayer().saveConfig();
        }

        if (Rank.isAdmin(event.getPlayer())) {
            if (!PlayerConfig.getString(UUID + ".ip").equals(event.getPlayer().getAddress().getHostString())) {
                PlayerConfig.set(UUID + ".isimposter", true);
                PlayerConfig.set(UUID + ".frozen", true);
                PlayerConfig.set(UUID + ".cmdsblcked", true);
                ConfigFiles.getPlayer().saveConfig();
            }
        }
    }

    @EventHandler
    public void onPlayerQuit(PlayerQuitEvent event) {

        if (Rank.isImpostor(event.getPlayer())) {
            ConfigEntry.PlayerConfig().set(event.getPlayer().getUniqueId().toString() + ".frozen", false);
            ConfigEntry.PlayerConfig().set(event.getPlayer().getUniqueId().toString() + ".cmdsblcked", false);
            ConfigFiles.getPlayer().saveConfig();
        }
    }
}
