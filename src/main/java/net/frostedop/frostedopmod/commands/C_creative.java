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
        super("creative", "/creative", "", Arrays.asList("gmc"));
    }

    @Override
    public boolean onCommand(CommandSender sender, Command cmd, String label, String[] args) {

        if (args.length == 0) {
            sender.sendMessage(ChatColor.YELLOW + "You are now in GMC!");
            ((Player) sender).setGameMode(GameMode.CREATIVE);
        }
        return true;
    }
}
