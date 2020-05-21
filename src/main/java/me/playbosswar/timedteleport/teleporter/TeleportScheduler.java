package me.playbosswar.timedteleport.teleporter;

import me.playbosswar.timedteleport.Main;
import org.bukkit.Bukkit;
import org.bukkit.entity.Player;

public class TeleportScheduler {
    public static void startTeleportationCycle(Player p, Teleporter t) {
        int id = Bukkit.getScheduler().scheduleSyncRepeatingTask(Main.getInstance(), () -> p.teleport(t.getNextLocation()), 1l, t.getDuration() * 20);
        TeleporterManager.addSchedule(p, id);
    }
}
