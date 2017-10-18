package net.frostedop.frostedopmod.commands;

import java.util.Arrays;
import net.frostedop.frostedopmod.ranks.Rank;
import org.bukkit.ChatColor;
import org.bukkit.GameMode;
import org.bukkit.command.Command;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

/**
 *
 * @author Savnith
 */

public class C_spectator extends FCommand {

    public C_spectator() {
        super("spectator", "/spectator <player>", "", Arrays.asList("spec"));
    }

    @Override
    public boolean onCommand(CommandSender sender, Command cmd, String label, String[] args) {

        if (!Rank.isAdmin(sender)) {
            sender.sendMessage(NO_PERM);
            return true;
        }

        if (args.length == 0) {
            sender.sendMessage(ChatColor.YELLOW + "You are now in Spectator!");
            ((Player) sender).setGameMode(GameMode.SPECTATOR);
        }
        return true;
        
    }
        final Player player = getPlayer(args[0]);

        if (player == null) {
            sender.sendMessage(ChatColor.RED + "Player not found!");
            return true;
        }
            sender.sendMessage(ChatColor.YELLOW + "Setting " + player.getName() + " to game mode spectator");
            player.sendMessage(ChatColor.YELLOW + sender.getName() + " set your game mode to spectator");
            ((Player) player).setGameMode(GameMode.SPECTATOR);

        return true;
    }
}
