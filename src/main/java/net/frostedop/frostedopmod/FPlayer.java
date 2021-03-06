package net.frostedop.frostedopmod;

import net.frostedop.frostedopmod.config.ConfigEntry;
import org.bukkit.entity.Player;

public class FPlayer {

    public static String getIPAddress(Player player) {
        return player.getAddress().getHostString();
    }

    public static String getName(Player player) {
        return player.getName();
    }

    public static String getUuid(Player player) {
        return player.getUniqueId().toString();
    }

    public static String getTag(Player player) {
        return ConfigEntry.PlayerConfig().getString(player.getUniqueId().toString() + ".tag");
    }
}
