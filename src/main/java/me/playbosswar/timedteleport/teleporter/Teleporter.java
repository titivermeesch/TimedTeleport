package me.playbosswar.timedteleport.teleporter;

import org.bukkit.Location;

import java.util.ArrayList;

public class Teleporter {
    private String name;
    private ArrayList<Location> locations;
    private int duration;
    private int latestLocationIndex = 0;

    public Teleporter(String name, ArrayList<Location> locations, int duration) {
        this.name = name;
        this.locations = locations;
        this.duration = duration;
    }

    public void addLocation(Location loc) {
        locations.add(loc);
    }

    public int getDuration() {
        return duration;
    }

    public void setDuration(int duration) {
        this.duration = duration;
    }

    public String getName() {
        return name;
    }

    public Location getNextLocation() {
        Location l = locations.get(latestLocationIndex);

        if(latestLocationIndex >= locations.size() - 1) {
            latestLocationIndex = 0;
        } else {
            latestLocationIndex++;
        }

        return l;
    }
}
