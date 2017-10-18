package net.frostedop.frostedopmod.commands;

import net.frostedop.frostedopmod.config.ConfigEntry;
import net.frostedop.frostedopmod.config.ConfigFiles;
import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.command.Command;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

// This needs to be fixed soon :/
public class C_admin extends FCommand {

    public C_admin() {
        super("admin", "/admin [add - remove - setrank] <player> [rank]", "Main admin cmd");
    }

    @Override
    public boolean onCommand(CommandSender sender, Command cmd, String label, String[] args) {
        if (args.length < 1) {
            return false;
        }

        if (args[0].equals("add")) {
            if (args.length < 2) {
                return false;
            }
            if (args.length == 0) {
                sender.sendMessage("Usage: /admin add <player>");
                return true;
            }
            final Player player = Bukkit.getPlayer(args[1]);
            if (player == null) {
                sender.sendMessage(PLAYER_NOT_FOUND);
            }
            add(player, sender);
            return true;
        } else if (args[0].equals("setrank")) {
            if (args.length == 0) {
                sender.sendMessage("Usage: /admin setrank [telnet / senior] <player>");
                return true;
            }
            final Player player = Bukkit.getPlayer(args[1]);
            if (args.length > 1) {
                if (args[2].equals("telnet")) {
                    setTelnet(player, sender);
                    return true;
                }
                if (args[2].equals("senior")) {
                    setSenior(player, sender);
                    return true;
                }
            }
        }
        return true;
    }

    public void add(Player player, CommandSender sender) {

        String UUID = player.getUniqueId().toString();

        if (!ConfigEntry.AdminConfig().contains(UUID)) {

            adminConfig().set(UUID + ".name", player.getName().toLowerCase());
            adminConfig().set(UUID + ".ip", player.getAddress().getHostString());
            adminConfig().set(UUID + ".shoutcolor", "&d");
            adminConfig().set(UUID + ".loginmsg", null);
            adminConfig().set(UUID + ".cmdspy", false);
            adminConfig().set(UUID + ".isactive", true);
            adminConfig().set(UUID + ".isadmin", true);
            adminConfig().set(UUID + ".istelnet", false);
            adminConfig().set(UUID + ".issenior", false);
            adminConfig().set(UUID + ".executive", false);
            adminConfig().set(UUID + ".developer", false);
            adminConfig().set(UUID + ".owner", false);
            ConfigFiles.getAdmins().saveConfig();

            bcastMsg(ChatColor.RED + sender.getName() + " adding " + player.getName() + " to the admin list!");
        } else {
            adminConfig().set(UUID + ".isactive", true);
            adminConfig().set(UUID + ".isadmin", true);
            ConfigFiles.getAdmins().saveConfig();

            bcastMsg(ChatColor.RED + sender.getName() + " Re-adding " + player.getName() + " to the admin list!");
        }
    }

    public void setTelnet(Player player, CommandSender sender) {
        String UUID = player.getUniqueId().toString();

        if (!ConfigEntry.AdminConfig().contains(UUID)) {
            sender.sendMessage(ChatColor.GRAY + "Player must be super admin to add them to telnet!");
        } else {
            adminConfig().set(UUID + ".name", player.getName().toLowerCase());
            adminConfig().set(UUID + ".ip", player.getAddress().getHostString());
            adminConfig().set(UUID + ".istelnet", true);
            ConfigFiles.getAdmins().saveConfig();

            bcastMsg(ChatColor.RED + sender.getName() + " setting " + player.getName() + "'s rank to Telnet Admin!");
        }
    }

    public void setSenior(Player player, CommandSender sender) {
        String UUID = player.getUniqueId().toString();

        if (!ConfigEntry.AdminConfig().contains(UUID)) {
            sender.sendMessage(ChatColor.GRAY + "Player must be super admin to add them to telnet!");
        } else {
            adminConfig().set(UUID + ".name", player.getName().toLowerCase());
            adminConfig().set(UUID + ".ip", player.getAddress().getHostString());
            adminConfig().set(UUID + ".istelnet", true);
            adminConfig().set(UUID + ".issenior", true);
            ConfigFiles.getAdmins().saveConfig();

            bcastMsg(ChatColor.RED + sender.getName() + " setting " + player.getName() + "'s rank to Senior Admin!");
        }
    }
}
