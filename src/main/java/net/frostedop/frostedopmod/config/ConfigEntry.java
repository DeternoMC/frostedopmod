package net.frostedop.frostedopmod.config;

import org.bukkit.configuration.file.FileConfiguration;

public class ConfigEntry {

    public static final String S_NAME = "server.name";
    public static final String S_MOTD_L1 = "server.motd-line-1";
    public static final String S_MOTD_L2 = "server.motd-line-2";
    public static final String S_FULL_MOTD = "server.motd-full-server";
    public static final String S_PERMBAN_URL = "server.perm-ban-url";
    public static final String L_FORUM_LINK = "server.forumlink";
    public static final String L_FORUM_DAYS = "server.forumdays";
    public static final String S_BAN_URL = "server.gtfo-url";
    public static final String T_AUTODAY = "toggles.autoday";
    public static final String T_NO_RAIN = "toggles.no-rain";
    public static final String F_DJUMP = "fun.djump";
    public static final String D_CMDBLOCK = "disabled.cmdblock";
    public static final String D_LAVAPLACE = "disabled.lava-place";
    public static final String D_WATERPLACE = "disabled.water-place";

    public static final FileConfiguration PlayerConfig() {
        return ConfigFiles.getPlayer().getConfig();
    }

    public static final FileConfiguration BansConfig() {
        return ConfigFiles.getBans().getConfig();
    }

    public static final FileConfiguration PermbanConfig() {
        return ConfigFiles.getPermbans().getConfig();
    }

    public static final FileConfiguration MainConfig() {
        return ConfigFiles.getMConfig().getConfig();
    }

    public static final FileConfiguration AdminConfig() {
        return ConfigFiles.getAdmins().getConfig();
    }

    public static final FileConfiguration DonatorConfig() {
        return ConfigFiles.getDonators().getConfig();
    }

    public static final FileConfiguration LogConfig() {
        return ConfigFiles.getLogs().getConfig();
    }
}
