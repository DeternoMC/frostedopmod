package net.frostedop.frostedopmod.listeners;

import net.frostedop.frostedopmod.FrostedOPMod;
import net.frostedop.frostedopmod.config.ConfigEntry;
import static net.frostedop.frostedopmod.config.ConfigEntry.P_CHATCOLOR;
import static net.frostedop.frostedopmod.config.ConfigEntry.P_CMDSBLOCKED;
import static net.frostedop.frostedopmod.config.ConfigEntry.P_FROZEN;
import static net.frostedop.frostedopmod.config.ConfigEntry.P_IP;
import static net.frostedop.frostedopmod.config.ConfigEntry.P_MUTED;
import static net.frostedop.frostedopmod.config.ConfigEntry.P_NAME;
import static net.frostedop.frostedopmod.config.ConfigEntry.P_TAG;
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
            PlayerConfig.set(UUID + P_NAME, event.getPlayer().getName().toLowerCase());
            PlayerConfig.set(UUID + P_IP, event.getPlayer().getAddress().getHostString());
            PlayerConfig.set(UUID + P_TAG, null);
            PlayerConfig.set(UUID + P_MUTED, false);
            PlayerConfig.set(UUID + P_FROZEN, false);
            PlayerConfig.set(UUID + P_CMDSBLOCKED, false);
            PlayerConfig.set(UUID + P_CHATCOLOR, "&7");
            ConfigFiles.getPlayer().saveConfig();
        } else if (PlayerConfig.contains(UUID)) {
            PlayerConfig.set(UUID + P_NAME, event.getPlayer().getName().toLowerCase());
            PlayerConfig.set(UUID + P_IP, event.getPlayer().getAddress().getHostString());
            ConfigFiles.getPlayer().saveConfig();
        }

        if (!PlayerConfig.getString(UUID + P_IP).equals(event.getPlayer().getAddress().getHostString())) {
            PlayerConfig.set(UUID + ".isimposter", true);
            PlayerConfig.set(UUID + P_FROZEN, true);
            PlayerConfig.set(UUID + P_CMDSBLOCKED, true);
            ConfigFiles.getPlayer().saveConfig();
        }
    }

    @EventHandler
    public void onPlayerQuit(PlayerQuitEvent event) {

        if (Rank.isImpostor(event.getPlayer())) {
            ConfigEntry.PlayerConfig().set(event.getPlayer().getUniqueId().toString() + P_FROZEN, false);
            ConfigEntry.PlayerConfig().set(event.getPlayer().getUniqueId().toString() + P_CMDSBLOCKED, false);
            ConfigFiles.getPlayer().saveConfig();
        }
    }
}
