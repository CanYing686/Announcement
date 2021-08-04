package com.announcement.announcement;

import org.bukkit.Bukkit;
import org.bukkit.command.Command;
import org.bukkit.command.CommandSender;
import org.bukkit.command.ConsoleCommandSender;
import org.bukkit.command.TabExecutor;
import org.bukkit.entity.Player;
import org.jetbrains.annotations.NotNull;

import java.util.ArrayList;
import java.util.List;

public class Commands implements TabExecutor {

    @Override
    public boolean onCommand(@NotNull CommandSender sender, @NotNull Command command, @NotNull String label, String[] args) {
        switch (args.length) {
            case 0:
                if (sender instanceof Player || sender instanceof ConsoleCommandSender) {
                    Player p = (Player) sender;
                    p.performCommand("amt help");
                }
                return true;
            case 1:
                switch (args[0]) {
                    case "reload":
                        if (sender instanceof Player || sender instanceof ConsoleCommandSender) {
                            if (sender.hasPermission("Announcement.reload")) {
                                Announcement.main.reloadConfig();
                                Bukkit.getScheduler().cancelTasks(Announcement.main);
                                Runnable.time();
                                Runnable.Title();
                                sender.sendMessage(Methods.chatColor(Methods.Prefix() + Methods.configColor("Reload")));
                            } else sender.sendMessage(Methods.configColor("NoPermission"));
                        }
                        return true;
                    case "help":
                        if (sender instanceof Player || sender instanceof ConsoleCommandSender) {
                            for (String msg : Methods.configList("Help")) {
                                sender.sendMessage(Methods.chatColor(msg));
                            }
                        }
                }
            default:
                switch (args[0]) {
                    case "Broadcast":
                        if (Methods.Boolean("Broadcast.Enable")) {
                            if (sender instanceof Player || sender instanceof ConsoleCommandSender) {
                                if (sender.hasPermission("Announcement.Broadcast")) {
                                    StringBuilder msg = new StringBuilder();
                                    if (args.length > 1) {
                                        for (int i = 1; i < args.length; i++) msg.append(args[i]).append(" ");
                                        for (Player p : Announcement.main.getServer().getOnlinePlayers()) {
                                            if (Bukkit.getPluginManager().getPlugin("PlaceholderAPI") != null) {
                                                String papi = Methods.PAPI(p, String.valueOf(msg).replace("[p]",p.getName()));
                                                p.sendMessage(Methods.configColor("Broadcast.Prefix") + Methods.chatColor(papi));
                                            } else
                                                p.sendMessage(Methods.configColor("Broadcast.Prefix") + Methods.chatColor(String.valueOf(msg)));
                                        }
                                    } else sender.sendMessage(Methods.configColor("Broadcast.WrongCommand"));
                                } else sender.sendMessage(Methods.configColor("Broadcast.NoPermission"));
                                return true;
                            }
                        } else sender.sendMessage(Methods.configColor("Broadcast.Disable"));
                        return true;
                    case "BroadcastTitle":
                        if (Methods.Boolean("BroadcastTitle.Enable")) {
                            if (sender instanceof Player || sender instanceof ConsoleCommandSender) {
                                if (sender.hasPermission("Announcement.BroadcastTitle")) {
                                    StringBuilder msg = new StringBuilder();
                                    if (args.length > 1) {
                                        for (int i = 1; i < args.length; i++) msg.append(args[i]).append(" ");
                                        for (Player p : Announcement.main.getServer().getOnlinePlayers()) {
                                            if (Bukkit.getPluginManager().getPlugin("PlaceholderAPI") != null) {
                                                String papi = Methods.PAPI(p, String.valueOf(msg));
                                                p.sendTitle(Methods.chatColor(papi), "", 20, 20, 20);
                                            } else
                                                p.sendTitle(Methods.chatColor(String.valueOf(msg).replace("[p]",p.getName())), "", 20, 20, 20);
                                        }
                                    } else sender.sendMessage(Methods.configColor("BroadcastTitle.WrongCommand"));
                                } else sender.sendMessage(Methods.configColor("BroadcastTitle.NoPermission"));
                                return true;
                            }
                        } else sender.sendMessage(Methods.configColor("BroadcastTitle.Disable"));
                        return true;
                }
        }

        return false;
    }

    @Override
    public List<String> onTabComplete(@NotNull CommandSender sender, @NotNull Command command, @NotNull String alias, String[] args) {
        List<String> list = new ArrayList<>();
        if (args.length == 1) {
            if (sender.hasPermission("Announcement.tab")) {
                list.add("reload");
                list.add("help");
                list.add("Broadcast");
                list.add("BroadcastTitle");
            }
            return list;
        }
        return null;
    }
}
