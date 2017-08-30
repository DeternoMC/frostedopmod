package net.frostedop.frostedopmod.listeners;

import static net.frostedop.frostedopmod.FUtil.color;
import net.frostedop.frostedopmod.FrostedOPMod;
import net.frostedop.frostedopmod.config.ConfigEntry;
import static net.frostedop.frostedopmod.config.ConfigEntry.P_CHATCOLOR;
import static net.frostedop.frostedopmod.config.ConfigEntry.P_MUTED;
import static net.frostedop.frostedopmod.config.ConfigEntry.P_TAG;
import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.AsyncPlayerChatEvent;

public class FChatListener implements Listener {

    FrostedOPMod plugin;

    @SuppressWarnings("LeakingThisInConstructor")
    public FChatListener() {
        Bukkit.getPluginManager().registerEvents(this, FrostedOPMod.plugin);
    }

    @EventHandler
    public void onPlayerChat(AsyncPlayerChatEvent event) {

        if (ConfigEntry.PlayerConfig().getBoolean(event.getPlayer().getUniqueId().toString() + P_MUTED)) {
            event.getPlayer().sendMessage(ChatColor.GRAY + "You can't talk while muted!");
            event.setCancelled(true);
        }

        if (ConfigEntry.PlayerConfig().getString(event.getPlayer().getUniqueId().toString() + P_TAG) == null) {
            String chatformat = color(
                    ChatColor.GRAY + event.getPlayer().getDisplayName() + ChatColor.DARK_GRAY + " » " + ChatColor.GRAY + event.getMessage().trim());
            event.setFormat(chatformat);
        } else {
            String chatformat = color(
                    ConfigEntry.PlayerConfig().getString(event.getPlayer().getUniqueId().toString() + P_TAG) + " "
                    + ChatColor.GRAY + event.getPlayer().getDisplayName() + ChatColor.DARK_GRAY + " » "
                    + ChatColor.GRAY + event.getMessage().trim());
            event.setFormat(chatformat);
        }
    }
}
