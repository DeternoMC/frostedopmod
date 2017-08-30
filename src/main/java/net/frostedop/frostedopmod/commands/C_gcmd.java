package net.frostedop.frostedopmod.commands;

import static net.frostedop.frostedopmod.commands.FCommand.NO_PERM;
import net.frostedop.frostedopmod.ranks.Rank;
import org.apache.commons.lang.StringUtils;
import org.bukkit.Bukkit;
import static org.bukkit.Bukkit.getPlayer;
import org.bukkit.ChatColor;
import org.bukkit.command.Command;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

/**
 *
 * @author hailey
 */
public class C_gcmd extends FCommand {

    public C_gcmd(String command) {
        super("gcmd", "/gcmd <player> | command | message | <command/message>");
    }

    @Override
    public boolean onCommand(CommandSender sender, Command cmd, String label, String[] args) {

        if (!Rank.isAdmin(sender)) {
            sender.sendMessage(NO_PERM);
            return true;
        }

        if (args.length < 2) {
            return false;
        }

        final Player player = getPlayer(args[0]);

        if (player == null) {
            sender.sendMessage(ChatColor.RED + "Player not found!");
            return true;
        }

        if (args[1].equals("command")) {

            final String outCommand = StringUtils.join(args, " ", 1, args.length);

            try {
                sender.sendMessage("Sending command as " + player.getName() + ": " + outCommand);
                if (Bukkit.dispatchCommand(player, outCommand)) {
                    sender.sendMessage("Command sent.");
                } else {
                    sender.sendMessage("Unknown error sending command.");
                }
            } catch (Throwable ex) {
                sender.sendMessage("Error sending command: " + ex.getMessage());
            }

        }

        if (args[1].equals("message")) {

            final String message = StringUtils.join(args, " ", 1, args.length);

            sender.sendMessage(ChatColor.RED + "Successfully sent message.");
            player.chat(message);

        }
        return true;
    }
}
