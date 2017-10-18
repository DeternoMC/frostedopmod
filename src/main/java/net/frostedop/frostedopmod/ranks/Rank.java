package net.frostedop.frostedopmod.ranks;

import net.frostedop.frostedopmod.FUtil;
import net.frostedop.frostedopmod.config.ConfigEntry;
import org.bukkit.ChatColor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

public enum Rank {
    
    IMPOSTOR("an", "Impostor", "IMP", ChatColor.YELLOW, "&e"),
    NON_OP("a", "Non-Op", "", ChatColor.WHITE, "&f"),
    OP("an", "Operator", "", ChatColor.RED, "&c"),
    MASTER_BUILDER("a", "Master Builder", "Master Builder", ChatColor.GOLD, "&6"),
    SUPER_ADMIN("a", "Super Admin", "Super Admin", ChatColor.GOLD, "&6"),
    TELNET_ADMIN("a", "Telnet Admin", "Telnet Admin", ChatColor.DARK_GREEN, "&2"),
    SENIOR_ADMIN("a", "Senior Admin", "Senior Admin", ChatColor.LIGHT_PURPLE, "&d"),
    EXECUTIVE("an", "", "Executive", ChatColor.YELLOW, "&e"),
    DEVELOPER("a", "Developer", "Dev", ChatColor.DARK_PURPLE, "&5"),
    OWNER("the", "Owner", "Owner", ChatColor.BLUE, "&9"),
    CONSOLE("the", "Console", "Console", ChatColor.DARK_PURPLE, "&5");

    public final String lmsg_pre;
    public final String lmsg;
    public final String tag;
    public final ChatColor rankcolor;
    public final String code;

    private Rank(String lmsg_pre, String lmsg, String tag, ChatColor rankcolor, String code) {
        this.lmsg_pre = lmsg_pre;
        this.lmsg = lmsg;
        this.tag = tag;
        this.rankcolor = rankcolor;
        this.code = code;
    }

    public boolean isAtLeast(Rank rank) {
        return this.getLevel() >= rank.getLevel();
    }

    public int getLevel() {
        return this.ordinal();
    }

    public ChatColor getColor() {
        return rankcolor;
    }

    public String getColorCode() {
        return code;
    }

    public String getLoginMessage() {
        return lmsg_pre + " " + getColor() + lmsg;
    }

    public String getTag() {
        return ChatColor.DARK_GRAY + "[" + getColor() + tag + ChatColor.DARK_GRAY + "]";
    }

    public String getRawTag() {
        return "&8[" + getColorCode() + tag + "&8]";
    }

    public static Rank getRank(Player player) {
        if (ConfigEntry.AdminConfig().getBoolean(player.getUniqueId().toString() + ".imposter")) {
            return Rank.IMPOSTOR;
        }

        if (ConfigEntry.AdminConfig().getBoolean(player.getUniqueId().toString() + ".owner")) {
            return Rank.OWNER;
        }

        if (ConfigEntry.AdminConfig().getBoolean(player.getUniqueId().toString() + ".developer")) {
            return Rank.DEVELOPER;
        }

        if (ConfigEntry.AdminConfig().getBoolean(player.getUniqueId().toString() + ".executive")) {
            return Rank.EXECUTIVE;
        }

        if (ConfigEntry.AdminConfig().getBoolean(player.getUniqueId().toString() + ".issenior")) {
            return Rank.SENIOR_ADMIN;
        }

        if (ConfigEntry.AdminConfig().getBoolean(player.getUniqueId().toString() + ".istelnet")) {
            return Rank.TELNET_ADMIN;
        }

        if (ConfigEntry.AdminConfig().getBoolean(player.getUniqueId().toString() + ".isadmin")) {
            return Rank.SUPER_ADMIN;
        }

        if (isMasterBuilder(player)) {
            return Rank.MASTER_BUILDER; // If this is here, we can't have a duplicate title and admin title will override this
        }

        return player.isOp() ? Rank.OP : Rank.NON_OP;
    }

    public static Rank getRank(CommandSender sender) {
        if (sender instanceof Player) {
            return getRank((Player) sender);
        }

        return Rank.CONSOLE;
    }

    // TODO: find a cleaner way to do this 
    public static boolean isImpostor(Player player) {
        return getRank(player) == Rank.IMPOSTOR;
    }

    public static boolean isAdmin(Player player) {
        return getRank(player).isAtLeast(Rank.SUPER_ADMIN);
    }

    public static boolean isTelnetAdmin(Player player) {
        return getRank(player).isAtLeast(Rank.TELNET_ADMIN);
    }

    public static boolean isSeniorAdmin(Player player) {
        return getRank(player).isAtLeast(Rank.SENIOR_ADMIN);
    }

    public static boolean isExecutive(Player player) {
        return getRank(player).isAtLeast(Rank.EXECUTIVE);
    }

    public static boolean isDeveloper(Player player) {
        return getRank(player).isAtLeast(Rank.DEVELOPER);
    }

    public static boolean isOwner(Player player) {
        return getRank(player).isAtLeast(Rank.OWNER);
    }

    public static boolean isMasterBuilder(Player player) {
        return FUtil.MASTERBUILDERS.contains(player.getName());
    }

    public static boolean isImpostor(CommandSender sender) {
        return getRank(sender) == Rank.IMPOSTOR;
    }

    public static boolean isAdmin(CommandSender sender) {
        return getRank(sender).isAtLeast(Rank.SUPER_ADMIN);
    }

    public static boolean isTelnetAdmin(CommandSender sender) {
        return getRank(sender).isAtLeast(Rank.TELNET_ADMIN);
    }

    public static boolean isSeniorAdmin(CommandSender sender) {
        return getRank(sender).isAtLeast(Rank.SENIOR_ADMIN);
    }

    public static boolean isExecutive(CommandSender sender) {
        return getRank(sender).isAtLeast(Rank.EXECUTIVE);
    }

    public static boolean isDeveloper(CommandSender sender) {
        return getRank(sender).isAtLeast(Rank.DEVELOPER);
    }

    public static boolean isOwner(CommandSender sender) {
        return getRank(sender).isAtLeast(Rank.OWNER);
    }

    public static boolean isMasterBuilder(CommandSender sender) {
        return FUtil.MASTERBUILDERS.contains(sender.getName());
    }
}
