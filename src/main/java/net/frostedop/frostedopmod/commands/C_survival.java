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
        super("survival", "/survival", "", Arrays.asList("gms"));
    }

    @Override
    public boolean onCommand(CommandSender sender, Command cmd, String label, String[] args) {

        if (args.length == 0) {
            sender.sendMessage(ChatColor.YELLOW + "You are now in GMS!");
            ((Player) sender).setGameMode(GameMode.SURVIVAL);
        }
        return true;
    }
}
