package net.frostedop.frostedopmod.commands;

import java.util.Arrays;
import net.frostedop.frostedopmod.ranks.Rank;
import org.apache.commons.lang.ArrayUtils;
import org.apache.commons.lang.StringUtils;
import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.command.Command;
import org.bukkit.command.CommandSender;

/**
 *
 * @author Savnith
 */
public class C_adminchat extends FCommand {

    public C_adminchat() {
        super("adminchat", "/adminchat", "Talk in adminchat!", Arrays.asList("ac"));
    }

    @Override
    public boolean onCommand(CommandSender sender, Command cmd, String label, String[] args) {

        if (!Rank.isAdmin(sender)) {
            sender.sendMessage(NO_PERM);
            return true;
        }

        final String message = StringUtils.join(ArrayUtils.subarray(args, 0, args.length), " ");

        Bukkit.getOnlinePlayers().stream().filter((player) -> (Rank.isAdmin(sender))).forEach((player) -> { // This is weird..
            player.sendMessage("[" + ChatColor.AQUA + "STAFF" + ChatColor.WHITE + "] " + ChatColor.GRAY + "[" + Rank.getRank(sender).getColor() + Rank.getRank(sender).lmsg + ChatColor.GRAY + "] "
                    + ChatColor.DARK_RED + sender.getName() + ChatColor.WHITE + ": " + ChatColor.AQUA + message); // This should work?

        });
        return true;
    }
}
