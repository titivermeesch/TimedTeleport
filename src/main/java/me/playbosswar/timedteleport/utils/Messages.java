package me.playbosswar.timedteleport.utils;

import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.entity.Player;

public class Messages {
    public static void sendMessageToConsole(String message) {
        Bukkit.getConsoleSender().sendMessage("TimedTeleport > " + message);
    }

    public static void sendMessageToPlayer(Player p, String message) {
        p.sendMessage(ChatColor.translateAlternateColorCodes('&', "&§TimedTeleport > &f" + message));
    }

    public static void sendHelpMenu(Player p) {
        sendMessageToPlayer(p, "&6Welcome to TimedTeleport");
        sendMessageToPlayer(p, "&9Please use one of the following commands");
        sendMessageToPlayer(p, "");
        sendMessageToPlayer(p, "&9/timedteleport : Main help command");
        sendMessageToPlayer(p, "&9/timedteleport create <name> : Create a new teleport cycle");
        sendMessageToPlayer(p, "&9/timedteleport remove <name> : Remove an exisiting teleport cycle");
        sendMessageToPlayer(p, "&9/timedteleport addpoint <name>: Add current location to <name>");
        sendMessageToPlayer(p, "&9/timedteleport duration <seconds>: Set number of seconds between each teleport");
        sendMessageToPlayer(p, "&9/timedteleport start <name> : Start timed teleport for you");
        sendMessageToPlayer(p, "&9/timedteleport stop : Stop timed teleport");
    }
}
