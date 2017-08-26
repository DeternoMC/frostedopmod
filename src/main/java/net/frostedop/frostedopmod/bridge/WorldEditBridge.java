package net.frostedop.frostedopmod.bridge;

import com.sk89q.worldedit.LocalSession;
import com.sk89q.worldedit.bukkit.WorldEditPlugin;
import net.frostedop.frostedopmod.FLog;
import org.bukkit.Bukkit;
import org.bukkit.entity.Player;
import org.bukkit.plugin.Plugin;

/**
 *
 * @author Savnith
 */
public class WorldEditBridge {

    // Starting to work with worldedit...
    private static WorldEditPlugin worldedit = null;

    private static WorldEditPlugin getWorldEdit() {
        // Try to see if WorldEdit is present
        try {
            Plugin we = Bukkit.getServer().getPluginManager().getPlugin("WorldEdit");
            if (we != null) {
                if (we instanceof WorldEditPlugin) {
                    worldedit = (WorldEditPlugin) we;
                }
            }
        } catch (Exception e) {
            FLog.severe(e);
        }
        return worldedit;
    }

    public static void setLimit(Player player, int limit) {

        final LocalSession s = getWorldEditSession(player);

        if (s != null) {
            s.setBlockChangeLimit(limit);
        }
    }

    private static LocalSession getWorldEditSession(Player player) {

        final WorldEditPlugin wep = getWorldEdit();
        if (wep == null) {
            return null;
        }

        return wep.getSession(player);
    }
}
