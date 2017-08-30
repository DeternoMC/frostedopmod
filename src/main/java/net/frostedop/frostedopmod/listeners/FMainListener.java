package net.frostedop.frostedopmod.listeners;

import java.util.Arrays;
import net.frostedop.frostedopmod.FUtil;
import net.frostedop.frostedopmod.FrostedOPMod;
import net.frostedop.frostedopmod.config.ConfigEntry;
import static net.frostedop.frostedopmod.config.ConfigEntry.S_MOTD_L1;
import static net.frostedop.frostedopmod.config.ConfigEntry.S_MOTD_L2;
import static net.frostedop.frostedopmod.config.ConfigEntry.S_NAME;
import net.frostedop.frostedopmod.ranks.Rank;
import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.EventPriority;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerCommandPreprocessEvent;
import org.bukkit.event.player.PlayerMoveEvent;
import org.bukkit.event.server.ServerListPingEvent;

public class FMainListener implements Listener {

    FrostedOPMod plugin;

    @SuppressWarnings("LeakingThisInConstructor")
    public FMainListener() {
        Bukkit.getPluginManager().registerEvents(this, FrostedOPMod.plugin);
    }

    @EventHandler
    public void onPlayerMove(PlayerMoveEvent event) {

        if (ConfigEntry.PlayerConfig().getBoolean(event.getPlayer().getUniqueId().toString() + ".frozen")) {
            event.getPlayer().sendMessage(ChatColor.GRAY + "You can't move while frozen!");
            event.setCancelled(true);
            event.getPlayer().teleport(event.getPlayer());
        }
    }

    @EventHandler
    public void onCommandPreprocess(PlayerCommandPreprocessEvent event) {
        if (event.getMessage().split(" ")[0].contains(":")) {
            event.getPlayer().sendMessage(ChatColor.GRAY + "You cannot send plugin specific commands.");
            event.setCancelled(true);
        }

        if (ConfigEntry.PlayerConfig().getBoolean(event.getPlayer().getUniqueId().toString() + ".cmdsblcked")) {
            event.getPlayer().sendMessage(ChatColor.GRAY + "Your commands are currently blocked!");
            event.setCancelled(true);
        }

        Bukkit.getOnlinePlayers().stream().forEach((player) -> {
            if (ConfigEntry.AdminConfig().getBoolean(player.getUniqueId().toString() + ".cmdspy") && Rank.isAdmin(player) && !Rank.isAdmin(event.getPlayer())) {
                player.sendMessage(ChatColor.GRAY + event.getPlayer().getName() + ": " + event.getMessage().toLowerCase());
            }
        });
    }

    @EventHandler(priority = EventPriority.HIGHEST)
    public void onServerPing(ServerListPingEvent event) {

        if (Arrays.asList(Bukkit.getOnlinePlayers()).size() >= Bukkit.getMaxPlayers()) {
            event.setMotd(FUtil.color(ConfigEntry.MainConfig().getString("server.motd-full-server").replace("%servername%", ConfigEntry.MainConfig().getString("server.name"))));
        }

        String lineone = ConfigEntry.MainConfig().getString(S_MOTD_L1);
        String linetwo = ConfigEntry.MainConfig().getString(S_MOTD_L2).replace("%servername%", ConfigEntry.MainConfig().getString(S_NAME));
        String Motd = lineone + " \n" + linetwo;
        Motd = FUtil.color(Motd);
        event.setMotd(Motd.trim());
    }
}
