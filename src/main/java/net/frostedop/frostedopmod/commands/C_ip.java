package net.frostedop.frostedopmod.commands;

import java.util.Arrays;
import net.frostedop.frostedopmod.ranks.Rank;
import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.command.CommandSender;
import org.bukkit.command.Command;
import org.bukkit.entity.Player;

public class C_ip extends FCommand {

    public C_ip() {
        super("ip", "/ip <playername>", "Find someones ip!", Arrays.asList("iphist", "iphistory", "findip"));
    }

    @Override
    public boolean onCommand(CommandSender sender, Command cmd, String label, String[] args) {

        if (!Rank.isAdmin(sender)) {
            sender.sendMessage(NO_PERM);
            return true;
        }

        if (args.length != 1) {
            return false;
        }

        final Player player = Bukkit.getServer().getPlayer(args[0]);

        if (player == null) {
            sender.sendMessage(PLAYER_NOT_FOUND);
            return true;
        }
        
        final String ip = player.getAddress().getAddress().getHostAddress();

        sender.sendMessage(ChatColor.GRAY + player.getName() + " IP: " + ip);
        
        return true;
    }
}
