package net.frostedop.frostedopmod.listeners;

import net.frostedop.frostedopmod.FUtil;
import net.frostedop.frostedopmod.FrostedOPMod;
import net.frostedop.frostedopmod.config.ConfigEntry;
import net.frostedop.frostedopmod.config.ConfigFiles;
import net.frostedop.frostedopmod.ranks.Rank;
import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerJoinEvent;

public class FRankListener implements Listener {

    FrostedOPMod plugin;

    @SuppressWarnings("LeakingThisInConstructor")
    public FRankListener() {
        Bukkit.getPluginManager().registerEvents(this, FrostedOPMod.plugin);
    }

    @EventHandler
    public void onPlayerJoin(PlayerJoinEvent event) {
        
        final Player player = event.getPlayer();

        switch (Rank.getRank(player)) {
            case IMPOSTOR: {
                player.setPlayerListName(ChatColor.YELLOW + "IMP " + player.getName());
                ConfigEntry.PlayerConfig().set(player.getUniqueId().toString() + ".tag", Rank.IMPOSTOR.getRawTag());
                break;
            }
            case SUPER_ADMIN: {
                player.setPlayerListName(ChatColor.GOLD + "SA " + player.getName());
                ConfigEntry.PlayerConfig().set(player.getUniqueId().toString() + ".tag", Rank.SUPER_ADMIN.getRawTag());
                break;
            }
            case TELNET_ADMIN: {
                player.setPlayerListName(ChatColor.DARK_GREEN + "STA " + player.getName());
                ConfigEntry.PlayerConfig().set(player.getUniqueId().toString() + ".tag", Rank.TELNET_ADMIN.getRawTag());
                break;
            }
            case SENIOR_ADMIN: {
                player.setPlayerListName(ChatColor.LIGHT_PURPLE + "SrA " + player.getName());
                ConfigEntry.PlayerConfig().set(player.getUniqueId().toString() + ".tag", Rank.SENIOR_ADMIN.getRawTag());
                break;
            }
            case EXECUTIVE: {
                player.setPlayerListName(ChatColor.YELLOW + "EXEC " + player.getName());
                ConfigEntry.PlayerConfig().set(player.getUniqueId().toString() + ".tag", Rank.EXECUTIVE.getRawTag());
                break;
            }
            case DEVELOPER: {
                player.setPlayerListName(ChatColor.DARK_PURPLE + "Dev " + player.getName());
                ConfigEntry.PlayerConfig().set(player.getUniqueId().toString() + ".tag", Rank.DEVELOPER.getRawTag());
                break;
            }
            case OWNER: {
                player.setPlayerListName(ChatColor.BLUE + "Owner " + player.getName());
                ConfigEntry.PlayerConfig().set(player.getUniqueId().toString() + ".tag", Rank.OWNER.getRawTag());
                break;
            }
            default: {
                break;
            }
        }
    }
}
