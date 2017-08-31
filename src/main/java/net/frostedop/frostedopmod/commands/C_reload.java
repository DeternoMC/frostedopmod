package net.frostedop.frostedopmod.commands;

import net.frostedop.frostedopmod.FUtil;
import static net.frostedop.frostedopmod.commands.FCommand.NO_PERM;
import net.frostedop.frostedopmod.ranks.Rank;
import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.command.Command;
import org.bukkit.command.CommandSender;

/**
 *
 * @author hailey
 */
public class C_reload extends FCommand {

    public C_reload(String command) {
        super("reload", "/reload", "Reload the server files");
    }

    @Override
    public boolean onCommand(CommandSender sender, Command cmd, String label, String[] args) {

        if (!Rank.isAdmin(sender)) {
            sender.sendMessage(NO_PERM);
            return true;
        }

        if (args.length < 1) {
            return false;
        }

        FUtil.bcastMsg(ChatColor.GREEN + sender.getName() + " - Reloading the server.");
        Bukkit.reload();
        FUtil.bcastMsg(ChatColor.RED + "Reload Complete.");
        return true;
    }
}
