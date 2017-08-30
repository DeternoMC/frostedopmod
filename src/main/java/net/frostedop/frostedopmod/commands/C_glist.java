package net.frostedop.frostedopmod.commands;

import java.util.Arrays;
import net.frostedop.frostedopmod.FUtil;
import net.frostedop.frostedopmod.events.BanningEvent;
import static net.frostedop.frostedopmod.commands.FCommand.NO_PERM;
import net.frostedop.frostedopmod.config.ConfigEntry;
import net.frostedop.frostedopmod.config.ConfigFiles;
import net.frostedop.frostedopmod.ranks.Rank;
import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.OfflinePlayer;
import org.bukkit.command.Command;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

// This is broken.
public class C_glist extends FCommand {

    public C_glist() {
        super("glist", "/glist [unban, ban] <playername>", "Unban someone or ban someone", Arrays.asList("alist"));
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

        OfflinePlayer target = Bukkit.getServer().getOfflinePlayer(args[0]);

        if (args.length == 1) {
            if ("unban".equals(args[1])) {
                FUtil.bcastMsg(sender.getName() + " - Unbanning " + target.getName() + " and IP: "
                        + ConfigEntry.PlayerConfig().getString(target.getUniqueId().toString() + ".ip"), ChatColor.RED);
                BanningEvent.removeBan((Player) target);
                ConfigFiles.getBans().saveConfig();
            }

            if ("ban".equals(args[0])) {
                FUtil.bcastMsg(sender.getName() + " - Banning " + target.getName() + " and IP: "
                        + ConfigEntry.PlayerConfig().getString(target.getUniqueId().toString() + ".ip"), ChatColor.RED);
                BanningEvent.addBan((Player) target, sender);
                ConfigFiles.getBans().saveConfig();
            }
        }
        return true;
    }
}
