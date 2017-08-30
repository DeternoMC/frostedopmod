package net.frostedop.frostedopmod.commands;

import java.util.Arrays;
import net.frostedop.frostedopmod.config.ConfigEntry;
import org.bukkit.ChatColor;
import org.bukkit.command.Command;
import org.bukkit.command.CommandSender;

/**
 *
 * @author hailey
 */
public class C_admininformation extends FCommand {

    public C_admininformation() {
        super("admininformation", "/admininformation", "Learn how to get admin.", Arrays.asList("ai"));
    }

    @Override
    public boolean onCommand(CommandSender sender, Command cmd, String label, String[] args) {

        if (args.length > 1) {
            return false;
        }

        String servername = ConfigEntry.MainConfig().getString("server.name");
        String forumlink = ConfigEntry.MainConfig().getString("server.forumlink");
        String forumdays = ConfigEntry.MainConfig().getString("server.forumlink");
        //
        sender.sendMessage(ChatColor.GREEN + "To get administrator on " + servername + " you'll have to apply on their forums here: " + forumlink);
        sender.sendMessage(ChatColor.RED + "You must be registered on the forums for a minimum of " + forumdays + " before you can apply.");

        return true;
    }
}
