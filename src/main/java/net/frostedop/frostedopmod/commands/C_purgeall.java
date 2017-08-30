package net.frostedop.frostedopmod.commands;

import static net.frostedop.frostedopmod.commands.FCommand.NO_PERM;
import net.frostedop.frostedopmod.config.ConfigEntry;
import net.frostedop.frostedopmod.config.ConfigFiles;
import net.frostedop.frostedopmod.ranks.Rank;
import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.command.Command;
import org.bukkit.command.CommandSender;

public class C_purgeall extends FCommand {

    public C_purgeall() {
        super("purgeall", "/purgeall", "Purge all players punishments");
    }

    @Override
    public boolean onCommand(CommandSender sender, Command cmd, String label, String[] args) {
        if (!Rank.isAdmin(sender)) {
            sender.sendMessage(NO_PERM);
            return true;
        }

        Bukkit.getOnlinePlayers().stream().map((target) -> {
            target.sendMessage(ChatColor.RED + sender.getName() + " - Purging all player data");
            return target;
        }).map((target) -> {
            ConfigEntry.PlayerConfig().set(target.getUniqueId().toString() + ".muted", false);
            return target;
        }).map((target) -> {
            ConfigEntry.PlayerConfig().set(target.getUniqueId().toString() + ".frozen", false);
            return target;
        }).map((target) -> {
            ConfigEntry.PlayerConfig().set(target.getUniqueId().toString() + ".cmdsblcked", false);
            return target;
        }).forEach((_item) -> {
            ConfigFiles.getPlayer().saveConfig();
        });
        return true;
    }
}
