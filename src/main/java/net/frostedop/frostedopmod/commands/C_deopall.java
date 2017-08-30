package net.frostedop.frostedopmod.commands;

import static net.frostedop.frostedopmod.commands.FCommand.NO_PERM;
import net.frostedop.frostedopmod.ranks.Rank;
import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.command.Command;
import org.bukkit.command.CommandSender;

public class C_deopall extends FCommand {

    public C_deopall() {
        super("deopall", "/deopall", "De-ops all players on the server");
    }

    @Override
    public boolean onCommand(CommandSender sender, Command cmd, String label, String[] args) {

        if (!Rank.isAdmin(sender)) {
            sender.sendMessage(NO_PERM);
            return false;
        }

        if (args.length == 0) {
            Bukkit.getOnlinePlayers().stream().forEach((player) -> {
                player.sendMessage(ChatColor.AQUA + sender.getName() + " - De-opping all players on the server!");
                player.sendMessage(DEOP);
                player.setOp(false);
            });
            return true;
        }
        return true;
    }
}
