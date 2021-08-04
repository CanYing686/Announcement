package com.announcement.announcement;

import org.bukkit.Bukkit;
import org.bukkit.configuration.ConfigurationSection;
import org.bukkit.entity.Player;
import org.bukkit.event.Listener;

import java.util.List;
import java.util.Random;


public class Runnable implements Listener {
    public static ConfigurationSection sectionKeys = Announcement.main.getConfig().getConfigurationSection("Announcement");


    /**
     * 公告
     */
    public static void time() {

        for (String b : sectionKeys.getKeys(false))
            Bukkit.getScheduler().scheduleSyncRepeatingTask(Announcement.main, () -> {
                if (Methods.Boolean("Announcement." + b + ".Enable")) {
                    if (Methods.Boolean("Announcement." + b + ".Random")) {   // 随机一条
                        Announcement.main.getServer().broadcastMessage(Methods.configColor("Announcement." + b + ".Prefix")
                                + Methods.chatColor(getRandom(Methods.configList("Announcement." + b + ".Message"))));
                    } else // 默认
                        for (String Msg : Methods.configList("Announcement." + b + ".Message"))
                            Announcement.main.getServer().broadcastMessage(Methods.configColor("Announcement." + b + ".Prefix")
                                    + Methods.chatColor(Msg));
                }
            }, tick("Announcement." + b + ".Delay") * 20, tick("Announcement." + b + ".Delay") * 20);
    }

    /**
     * 标题
     */
    public static void Title() {
        Bukkit.getScheduler().scheduleSyncRepeatingTask(Announcement.main, () -> {
            if (Methods.Boolean("AnnouncementTitle.Enable")) {
                for (Player player : Announcement.main.getServer().getOnlinePlayers())
                    player.sendTitle(Methods.chatColor("AnnouncementTitle.title"), Methods.configColor("AnnouncementTitle.subtitle"), 20, 20, 20);
            }
        }, tick("AnnouncementTitle.delay") * 20, tick("AnnouncementTitle.delay") * 20);
    }

    /**
     * @param path 路径
     * @return Long值
     */
    public static Long tick(String path) {
        return Announcement.main.getConfig().getLong(path);
    }

    /**
     * @param list 列表
     * @return 字符串
     */
    public static String getRandom(List<String> list) {
        Random r = new Random();
        return list.get(r.nextInt(list.size()));
    }

}
