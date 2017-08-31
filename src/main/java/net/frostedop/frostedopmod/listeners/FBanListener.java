package net.frostedop.frostedopmod.listeners;

import net.frostedop.frostedopmod.events.BanningEvent;
import net.frostedop.frostedopmod.FrostedOPMod;
import net.frostedop.frostedopmod.config.ConfigEntry;
import net.frostedop.frostedopmod.ranks.Rank;
import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerLoginEvent;

public class FBanListener implements Listener {

    FrostedOPMod plugin;

    @SuppressWarnings("LeakingThisInConstructor")
    public FBanListener() {
        Bukkit.getPluginManager().registerEvents(this, FrostedOPMod.plugin);
    }

    @EventHandler
    public void onPlayerLogin(PlayerLoginEvent event) {
        
        Player player = event.getPlayer();

        if (ConfigEntry.MainConfig().getBoolean("server.lockdown")) {
            event.disallow(PlayerLoginEvent.Result.KICK_OTHER, ChatColor.GOLD + "FrostedOP is currently in Lockdown mode!");
        }

        if (ConfigEntry.MainConfig().getBoolean("server.adminmode") && !Rank.isAdmin(player)) {
            event.disallow(PlayerLoginEvent.Result.KICK_OTHER, "Server is currently only open to admins");
        }

        if (BanningEvent.isBanned(player) && !ConfigEntry.MainConfig().contains("famous_players") && !Rank.isAdmin(player)) {
            event.disallow(PlayerLoginEvent.Result.KICK_BANNED, ChatColor.GOLD + "You are currently banned from this server! "
                    + "\nAppeal at: " + ConfigEntry.MainConfig().getString("server.gtfo-url")
                    + ChatColor.RED + "\nReason: " + ChatColor.GOLD + BanningEvent.getBanReason(player) + ChatColor.RED
                    + "\nBanned by: " + ChatColor.GOLD + BanningEvent.getBanner(player));
        }
        
        if (ConfigEntry.PermbanConfig().getStringList("uuids").contains(player.getUniqueId().toString())) {
            event.disallow(PlayerLoginEvent.Result.KICK_BANNED, ChatColor.RED + "Your UUID is permanently banned from this server"
                    + "\nRelease procedures are available at" + ChatColor.GOLD
                    + "\n" + ConfigEntry.MainConfig().getString("server.perm-ban-url"));
        }
        
        if (ConfigEntry.PermbanConfig().getStringList("ips").contains(player.getAddress().getHostString())) {
            event.disallow(PlayerLoginEvent.Result.KICK_BANNED, ChatColor.RED + "Your IP is permanently banned from this server"
                    + "\nRelease procedures are available at" + ChatColor.GOLD
                    + "\n" + ConfigEntry.MainConfig().getString("server.perm-ban-url"));
        }
    }
}
