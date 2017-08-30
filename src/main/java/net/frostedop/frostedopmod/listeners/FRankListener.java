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
        if (Rank.isImpostor(player)) {
            FUtil.bcastMsg(player.getName() + " is " + Rank.getRank(player).getLoginMessage(), ChatColor.AQUA);
            player.setPlayerListName(Rank.getRank(player).getColor() + "IMP " + player.getName());
            ConfigEntry.AdminConfig().set(player.getUniqueId().toString() + ".tag", Rank.getRank(player).getRawTag());
            ConfigFiles.getPlayer().saveConfig();
        } else if (Rank.isAdmin(player)) {
            FUtil.bcastMsg(player.getName() + " is " + Rank.getRank(player).getLoginMessage(), ChatColor.AQUA);
            ConfigEntry.AdminConfig().set(player.getUniqueId().toString() + ".tag", Rank.getRank(player).getRawTag());
            ConfigFiles.getPlayer().saveConfig();

            switch (Rank.getRank(player)) {
                case SUPER_ADMIN: {
                    player.setPlayerListName(ChatColor.GOLD + "SA " + player.getName());
                    break;
                }
                case TELNET_ADMIN: {
                    player.setPlayerListName(ChatColor.DARK_GREEN + "STA " + player.getName());
                    break;
                }
                case SENIOR_ADMIN: {
                    player.setPlayerListName(ChatColor.LIGHT_PURPLE + "SrA " + player.getName());
                    break;
                }
                case EXECUTIVE: {
                    player.setPlayerListName(ChatColor.YELLOW + "EXEC " + player.getName());
                    break;
                }
                case DEVELOPER: {
                    player.setPlayerListName(ChatColor.DARK_PURPLE + "Dev " + player.getName());
                    break;
                }
                case OWNER: {
                    player.setPlayerListName(ChatColor.BLUE + "Owner " + player.getName());
                    break;
                }
                default: {
                    break;
                }
            }
        }
    }
}
