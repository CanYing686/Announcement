package com.announcement.announcement;

import me.clip.placeholderapi.PlaceholderAPI;
import org.bukkit.command.PluginCommand;
import org.bukkit.entity.Player;

import java.util.List;

public class Methods {

    /**
     * @param path yml位置
     * @return 布尔值
     */
    public static Boolean Boolean(String path) {
        return Announcement.main.getConfig().getBoolean(path);
    }

    /**
     * @param path yml位置
     * @return List 列表
     */
    public static List<String> configList(String path) {
        return Announcement.main.getConfig().getStringList(path);
    }

    /**
     * @param path yml位置
     */
    public static String config(String path) {
        return Announcement.main.getConfig().getString(path);
    }

    /**
     * @param command 指令
     */

    public static void setCommand(String command) {
        PluginCommand pluginCommand = Announcement.main.getCommand(command);
        if (pluginCommand != null) {
            pluginCommand.setExecutor(new Commands());
            pluginCommand.setTabCompleter(new Commands());
        }
    }

    /**
     * @param path 内容
     * @return 替换内容符号
     */
    public static String chatColor(String path) {
        return path.replace("&", "§");
    }


    /**
     * @param Msg 信息
     */
    public static void info(String Msg) {
        Announcement.main.getLogger().info(Msg);
    }

    /**
     * @return yml内容 前缀
     */
    public static String Prefix(){
        return chatColor(Methods.config("Prefix"));
    }

    /**
     * @param path yml
     * @return 颜色+c
     */
    public static String configColor(String path){
        return chatColor(Methods.config(path));
    }

    public static String PAPI(Player player,String s){
        return PlaceholderAPI.setPlaceholders(player,s);
    }
}
