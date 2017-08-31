package net.frostedop.frostedopmod.commands;

import net.frostedop.frostedopmod.config.ConfigEntry;
import net.frostedop.frostedopmod.config.ConfigFiles;
import net.frostedop.frostedopmod.ranks.Rank;
import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.command.Command;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

/**
 *
 * @author Savnith
 */
public class C_adminmode extends FCommand {

    public C_adminmode() {
        super("adminmode", "/adminmode [on - off]", "Adminmode");
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
                sender.sendMessage(ChatColor.GRAY + "Adminmode mode has been enabled!");
                ConfigEntry.MainConfig().set("server.adminmode", true);
                ConfigFiles.getMConfig().saveConfig();
                
                for (Player player : Bukkit.getOnlinePlayers()) {
                    if (!Rank.isAdmin(player)) {
                        player.kickPlayer("Server is currently only open to admins");
                    }
                }
                
                bcastMsg(ChatColor.RED + "Adminmode has been enabled!");
                return true;
            }
            
            if (args[0].equals("off")) {
                sender.sendMessage(ChatColor.GRAY + "Lockdown mode has been disabled!");
                ConfigEntry.MainConfig().set("server.adminmode", false);
                ConfigFiles.getMConfig().saveConfig();
                bcastMsg(ChatColor.RED + "Adminmode has been disabled!");
                return true;
            }
        }
        return true;
    }
}
