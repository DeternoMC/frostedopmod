package net.frostedop.frostedopmod.commands;

import java.util.Arrays;
import org.bukkit.ChatColor;
import org.bukkit.GameMode;
import org.bukkit.command.Command;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

/**
 *
 * @author Savnith
 */
public class C_survival extends FCommand {

    public C_survival() {
        super("survival", "/survival <player>", Arrays.asList("gms"));
    }

    @Override
    public boolean onCommand(CommandSender sender, Command cmd, Player playersender, String label, String[] args) {

        if (args.length == 0) {
            sender.sendMessage(ChatColor.YELLOW + "You are now in GMS!");
            ((Player) sender).setGameMode(GameMode.SURVIVAL);
        }
        return true;
    }
        final Player player = getPlayer(args[0]);

        if (player == null) {
            sender.sendMessage(ChatColor.RED + "Player not found!");
            return true;
        }
            sender.sendMessage(ChatColor.YELLOW + "Setting " + player.getName() + " to game mode survival");
            player.sendMessage(ChatColor.YELLOW + sender.getName() + " set your game mode to survival");
            ((Player) player).setGameMode(GameMode.SURVIVAL);

        return true;
    }
}
