package me.playbosswar.timedteleport.teleporter;

import me.playbosswar.timedteleport.utils.Messages;
import org.bukkit.Bukkit;
import org.bukkit.entity.Player;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;
import java.util.UUID;

public class TeleporterManager {
    public static ArrayList<Teleporter> teleporters = new ArrayList<>();
    public static Map<UUID, Integer> teleportSchedules = new HashMap<>();

    public static void addTeleporter(Teleporter t) {
        teleporters.add(t);
    }

    public static ArrayList<Teleporter> getAllTeleporters() {
        return teleporters;
    }

    public static Teleporter getTeleporter(String name) {
        for(Teleporter t : teleporters) {
            if(t.getName().equals(name)) {
                return t;
            }
        }
        return null;
    }

    public static void removeTeleporter(String name) {
        teleporters.remove(getTeleporter(name));
    }

    public static void addSchedule(Player p,  int id) {
        teleportSchedules.put(p.getUniqueId(), id);
        Messages.sendMessageToPlayer(p, "You are in the teleport loop");
    }

    public static void stopSchedule(Player p) {
        int scheduleId = teleportSchedules.get(p.getUniqueId());

        Bukkit.getScheduler().cancelTask(scheduleId);
        Messages.sendMessageToPlayer(p, "You are not being teleported anymore");
    }
}
