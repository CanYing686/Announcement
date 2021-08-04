package com.announcement.announcement;

import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.EventPriority;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerJoinEvent;

public class Events implements Listener {

    @EventHandler(priority = EventPriority.HIGHEST)
    public static void playerJoin(PlayerJoinEvent e) {
        Player p = e.getPlayer();
        if (Methods.Boolean("JoinServerTitle.Enable")) {
            p.sendTitle(Methods.chatColor(Methods.config("JoinServerTitle.title").replace("[p]", p.getName()))
                    , Methods.chatColor(Methods.config("JoinServerTitle.subtitle").replace("[p]", p.getName()))
                    , 20, 20, 20);
            }
        if (Methods.Boolean("JoinServerMessage.Enable")) {
            for (String msg : Methods.configList("JoinServerMessage.Message"))
                p.sendMessage(Methods.chatColor(msg).replace("[p]",p.getName()));
        }
    }
}

