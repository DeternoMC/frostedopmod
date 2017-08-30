package net.frostedop.frostedopmod.commands;

import net.frostedop.frostedopmod.FUtil;
import static net.frostedop.frostedopmod.commands.FCommand.NO_PERM;
import net.frostedop.frostedopmod.ranks.Rank;
import org.apache.commons.lang.ArrayUtils;
import org.apache.commons.lang.StringUtils;
import org.bukkit.Bukkit;
import org.bukkit.command.Command;
import org.bukkit.command.CommandSender;

public class C_rawsay extends FCommand {

    public C_rawsay() {
        super("rawsay", "/rawsay <message>", "rawsay");
    }

    @Override
    public boolean onCommand(CommandSender sender, Command cmd, String label, String[] args) {

        if (!Rank.isSeniorAdmin(sender)) {
            sender.sendMessage(NO_PERM);
            return true;
        }

        if (args.length == 0) {
            return false;
        }

        final String message = StringUtils.join(ArrayUtils.subarray(args, 0, args.length), " ");

        Bukkit.getOnlinePlayers().stream().forEach((player) -> {
            player.sendMessage(FUtil.color(message));
        });
        return true;
    }
}
