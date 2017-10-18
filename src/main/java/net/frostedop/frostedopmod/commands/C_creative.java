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
public class C_creative extends FCommand {

    public C_creative() {
        super("creative", "/creative <player>", Arrays.asList("gmc"));
    }

    @Override
    public boolean onCommand(CommandSender sender, Command cmd, Player playersender, String label, String[] args) {

        if (args.length == 0) {
            sender.sendMessage(ChatColor.YELLOW + "You are now in GMC!");
            ((Player) sender).setGameMode(GameMode.CREATIVE);
        }
        return true;
    }
        final Player player = getPlayer(args[0]);

        if (player == null) {
            sender.sendMessage(ChatColor.RED + "Player not found!");
            return true;
        }
            sender.sendMessage(ChatColor.YELLOW + "Setting " + player.getName() + " to game mode creative");
            player.sendMessage(ChatColor.YELLOW + sender.getName() + " set your game mode to creative");
            ((Player) player).setGameMode(GameMode.CREATIVE);

        return true;
    }
}    
