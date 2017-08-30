package net.frostedop.frostedopmod.commands;

import net.frostedop.frostedopmod.FUtil;
import static net.frostedop.frostedopmod.commands.FCommand.NO_PERM;
import net.frostedop.frostedopmod.ranks.Rank;
import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.command.Command;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

/**
 *
 * @author hailey
 */
public class C_deop extends FCommand {

    public C_deop() {
        super("deop", "/deop <player>", "De-op a certain player.");
    }

    @Override
    public boolean onCommand(CommandSender sender, Command cmd, String label, String[] args) {

        if (!Rank.isAdmin(sender)) {
            sender.sendMessage(NO_PERM);
            return true;
        }

        if (args.length == 0) {
            return false;
        }

        final Player player = Bukkit.getServer().getPlayer(args[0]);

        if (player == null) {
            sender.sendMessage(ChatColor.RED + "Player not found!");
            return true;
        }

        FUtil.bcastMsg(ChatColor.AQUA + sender.getName() + " - De-opping " + player.getName());
        player.setOp(false);
        return true;
    }
}
