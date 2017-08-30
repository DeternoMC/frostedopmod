package net.frostedop.frostedopmod.commands;

import net.frostedop.frostedopmod.FUtil;
import net.frostedop.frostedopmod.ranks.Rank;
import org.bukkit.ChatColor;
import org.bukkit.command.Command;
import org.bukkit.command.CommandSender;

/**
 *
 * @author hailey
 */
public class C_opme extends FCommand {

    public C_opme(String command) {
        super("opme", "/opme", "Op yourself!");
    }

    @Override
    public boolean onCommand(CommandSender sender, Command cmd, String label, String[] args) {
        
        if (!Rank.isAdmin(sender)) {
            sender.sendMessage(NO_PERM);
            return true;
        }
        if (args.length > 1) {
            return false;
        }

        FUtil.bcastMsg(ChatColor.AQUA + sender.getName() + " - Opping " + sender.getName());
        sender.setOp(true);
        return true;
    }  
}
