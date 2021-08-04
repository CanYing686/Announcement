package com.announcement.announcement;

import org.bukkit.plugin.java.JavaPlugin;

public final class Announcement extends JavaPlugin {

    public static Announcement main;

    @Override
    public void onEnable() {
            main = this;
            saveDefaultConfig();
            reloadConfig();
            Methods.info("插件启用");
            getServer().getPluginManager().registerEvents(new Events(),this);
            Runnable.time();
            Runnable.Title();
            Methods.setCommand("Announcement");
            Methods.setCommand("Amt");
    }

    @Override
    public void onDisable() {
        Methods.info("插件已卸载");
    }


}

