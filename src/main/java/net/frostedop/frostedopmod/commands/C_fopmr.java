package net.frostedop.frostedopmod.commands;

import java.util.Arrays;
import net.frostedop.frostedopmod.FLog;
import net.frostedop.frostedopmod.config.ConfigFiles;
import net.frostedop.frostedopmod.ranks.Rank;
import net.frostedop.frostedopmod.FUtil;
import org.bukkit.ChatColor;
import org.bukkit.command.Command;
import org.bukkit.command.CommandSender;

public class C_fopmr extends FCommand {

    public C_fopmr() {
        super("fopmr", "/fopmr <reload>", "Main CMD ;D", Arrays.asList("fopm", "frostedopmodr"));
    }

    @Override
    public boolean onCommand(CommandSender sender, Command cmd, String label, String[] args) {
        if (args.length == 1) {

            if (!args[0].equals("reload")) {
                return false;
            }

            if (!Rank.isAdmin(sender)) {
                sender.sendMessage(NO_PERM);
                return true;
            }

            ConfigFiles.getAdmins().reloadConfig();
            ConfigFiles.getDonators().reloadConfig();
            ConfigFiles.getMConfig().reloadConfig();
            ConfigFiles.getPlayer().reloadConfig();
            FLog.info("Plugin has been reloaded, by: " + sender.getName());
            sender.sendMessage(ChatColor.GRAY + "FrostedOPMod: Remastered has been reloaded!");
            return true;
        }

        if (args.length == 0) {
            sender.sendMessage(FUtil.color("&8&m--------------------------------"));
            sender.sendMessage(FUtil.color("&b&lThis is FrostedOPMod: Remastered!"));
            sender.sendMessage(FUtil.color("&3&lVersion: &bBeta: 3.0.1, "));
            sender.sendMessage(FUtil.color("&3&lPlugin State: &b" + FUtil.NOT_STABLE)); // Beta versions are unstable till it is fully finished.
            sender.sendMessage(FUtil.color("&3&lMade by: &bSavnith&3, &bHitzzForSenior &3and &bFocusvity"));
            sender.sendMessage(FUtil.color("&8&m--------------------------------"));

            return true;
        }
        return true;
    }
}
