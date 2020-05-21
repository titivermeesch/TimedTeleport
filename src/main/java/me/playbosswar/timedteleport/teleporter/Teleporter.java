package me.playbosswar.timedteleport.teleporter;

import org.bukkit.Location;

import java.util.ArrayList;

public class Teleporter {
    private ArrayList<Location> locations;
    private int duration;

    public Teleporter(ArrayList<Location> locations, int duration) {
        this.locations = locations;
        this.duration = duration;
    }

    public ArrayList<Location> getLocations() {
        return locations;
    }

    public void setLocations(ArrayList<Location> locations) {
        this.locations = locations;
    }

    public int getDuration() {
        return duration;
    }

    public void setDuration(int duration) {
        this.duration = duration;
    }
}
