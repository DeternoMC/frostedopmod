package net.frostedop.frostedopmod.commands;

import net.frostedop.frostedopmod.config.ConfigEntry;
import net.frostedop.frostedopmod.config.ConfigFiles;
import net.frostedop.frostedopmod.ranks.Rank;
import org.bukkit.ChatColor;
import org.bukkit.command.Command;
import org.bukkit.command.CommandSender;

/**
 *
 * @author Savnith
 */
public class C_lockdown extends FCommand {

    public C_lockdown() {
        super("lockdown", "/lockdown [on - off]", "Lockdown mode");
    }

    @Override
    public boolean onCommand(CommandSender sender, Command cmd, String label, String[] args) {
        
        if (!Rank.isAdmin(sender)) {
            sender.sendMessage(NO_PERM);
            return true;
        }
        
        if (args.length == 0) {
            return false;
        }
        
        if (args.length == 1) {
            if (args[0].equals("on")) {
                sender.sendMessage(ChatColor.GRAY + "Lockdown mode has been enabled!");
                ConfigEntry.MainConfig().set("server.lockdown", true);
                ConfigFiles.getMConfig().saveConfig();
                return true;
            }
            
            if (args[0].equals("off")) {
                sender.sendMessage(ChatColor.GRAY + "Lockdown mode has been disabled!");
                ConfigEntry.MainConfig().set("server.lockdown", false);
                ConfigFiles.getMConfig().saveConfig();
                return true;
            }
        }
        return true;
    }
}
