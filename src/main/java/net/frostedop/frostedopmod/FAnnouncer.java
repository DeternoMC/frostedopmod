package net.frostedop.frostedopmod;

import net.frostedop.frostedopmod.config.ConfigEntry;
import org.bukkit.plugin.Plugin;
import org.bukkit.scheduler.BukkitRunnable;

public class FAnnouncer {

    public static void Broadcast(Plugin pl) {
        new BukkitRunnable() {
            int number = 0;

            @Override
            public void run() {
                if (this.number >= ConfigEntry.MainConfig().getStringList("announcer.messages").size()) {
                    this.number = 0;
                }

                String prefix = ConfigEntry.MainConfig().getString("announcer.prefix");
                String message_color = ConfigEntry.MainConfig().getString("announcer.message-color");
                String message = (String) ConfigEntry.MainConfig().getStringList("announcer.messages").get(this.number);

                this.number += 1;

                FUtil.bcastMsg(FUtil.color(prefix + " " + message_color + message));
            }
        }.runTaskTimer(pl, 100L, 20 * pl.getConfig().getInt("announcer.delay"));
    }
}
