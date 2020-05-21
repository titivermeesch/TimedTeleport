package me.playbosswar.timedteleport.utils;

import me.playbosswar.timedteleport.Main;
import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.entity.Player;

public class Messages {
    public static void sendMessageToConsole(String message) {
        Bukkit.getConsoleSender().sendMessage("TimedTeleport > " + message);
    }

    public static void sendMessageToPlayer(Player p, String message) {
        p.sendMessage(ChatColor.translateAlternateColorCodes('&', Main.getInstance().getConfig().getString("prefix") + message));
    }

    public static void sendHelpMenu(Player p) {
        sendMessageToPlayer(p, "&6Welcome to TimedTeleport v1.1 by PlayBossWar");
        sendMessageToPlayer(p, Main.getInstance().getConfig().getString("help1"));
        sendMessageToPlayer(p, Main.getInstance().getConfig().getString("help2"));
        sendMessageToPlayer(p, Main.getInstance().getConfig().getString("help3"));
        sendMessageToPlayer(p, Main.getInstance().getConfig().getString("help4"));
        sendMessageToPlayer(p, Main.getInstance().getConfig().getString("help5"));
        sendMessageToPlayer(p, Main.getInstance().getConfig().getString("help6"));
        sendMessageToPlayer(p, Main.getInstance().getConfig().getString("help7"));
        sendMessageToPlayer(p, Main.getInstance().getConfig().getString("help8"));
        sendMessageToPlayer(p, Main.getInstance().getConfig().getString("help9"));
        sendMessageToPlayer(p, Main.getInstance().getConfig().getString("help10"));
    }
}
