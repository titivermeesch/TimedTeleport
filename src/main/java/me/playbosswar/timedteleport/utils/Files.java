package me.playbosswar.timedteleport.utils;

import me.playbosswar.timedteleport.Main;

import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;

import me.playbosswar.timedteleport.teleporter.Teleporter;
import me.playbosswar.timedteleport.teleporter.TeleporterManager;
import org.bukkit.Bukkit;
import org.bukkit.Location;
import org.bukkit.World;
import org.bukkit.entity.Player;

import org.json.simple.JSONObject;
import org.json.simple.JSONArray;
import org.json.simple.parser.JSONParser;
import org.json.simple.parser.ParseException;

public class Files {
    static String pluginFolderPath = Main.getInstance().getDataFolder().getPath();

    public static void createDataFolders() {
        File file = new File(pluginFolderPath + "/teleporters");
        file.mkdir();
    }

    public static String getTeleporterFilePath(String name) {
        return pluginFolderPath + "/teleporters/" + name + ".json";
    }

    public static Boolean teleportFileExists(String name) {
        File file = new File(getTeleporterFilePath(name));
        return file.exists();
    }

    public static void createNewTimedTeleportDataFile(Player p, String name) {
        try {
            File file = new File(getTeleporterFilePath(name));

            if (file.exists()) {
                Messages.sendMessageToPlayer(p, Main.getInstance().getConfig().getString("nameAlreadyUsed"));
                return;
            }

            FileWriter jsonFile = new FileWriter(getTeleporterFilePath(name));
            JSONObject timed = new JSONObject();

            timed.put("name", name);
            timed.put("duration", 1);

            JSONArray locations = new JSONArray();
            timed.put("locations", locations);

            jsonFile.write(timed.toJSONString());
            jsonFile.flush();

            Teleporter t = new Teleporter(name, new ArrayList<>(), 1);
            TeleporterManager.addTeleporter(t);

            Messages.sendMessageToPlayer(p, Main.getInstance().getConfig().getString("created"));
        } catch (IOException e) {
            Messages.sendMessageToPlayer(p, Main.getInstance().getConfig().getString("error"));
            e.printStackTrace();
        }
    }

    public static void removeExistingTimedTeleport(Player p, String name) {
        File file = new File(getTeleporterFilePath(name));

        if (!teleportFileExists(name)) {
            Messages.sendMessageToPlayer(p, Main.getInstance().getConfig().getString("timerNotExists"));
            return;
        }

        if (file.delete()) {
            TeleporterManager.removeTeleporter(name);
            Messages.sendMessageToPlayer(p, Main.getInstance().getConfig().getString("deleted"));
            return;
        }

        Messages.sendMessageToPlayer(p, Main.getInstance().getConfig().getString("error"));
    }

    public static void addPointToTimedTeleport(Player p, String teleportName) {
        if (!teleportFileExists(teleportName)) {
            Messages.sendMessageToPlayer(p, Main.getInstance().getConfig().getString("timerNotExists"));
            return;
        }

        try {
            FileReader reader = new FileReader(getTeleporterFilePath(teleportName));
            JSONParser parser = new JSONParser();

            JSONObject timer = (JSONObject) parser.parse(reader);
            Location loc = p.getLocation();

            JSONArray locations = (JSONArray) timer.get("locations");

            JSONObject locationObject = new JSONObject();
            locationObject.put("world", loc.getWorld().getName());
            locationObject.put("x", loc.getX());
            locationObject.put("y", loc.getY());
            locationObject.put("z", loc.getZ());
            locationObject.put("yaw", loc.getYaw());
            locationObject.put("pitch", loc.getPitch());
            locations.add(locationObject);
            timer.put("locations", locations);

            FileWriter fw = new FileWriter(getTeleporterFilePath(teleportName));
            fw.write(timer.toJSONString());
            fw.flush();

            TeleporterManager.getTeleporter(teleportName).addLocation(loc);

            Messages.sendMessageToPlayer(p, Main.getInstance().getConfig().getString("locationAdded"));
        } catch (IOException e) {
            Messages.sendMessageToPlayer(p, Main.getInstance().getConfig().getString("error"));
            e.printStackTrace();
        } catch (ParseException e) {
            Messages.sendMessageToPlayer(p, Main.getInstance().getConfig().getString("error"));
            e.printStackTrace();
        }
    }

    public static void setTimerDuration(Player p, int duration, String teleportName) {
        if (!teleportFileExists(teleportName)) {
            Messages.sendMessageToPlayer(p, Main.getInstance().getConfig().getString("timerNotExists"));
            return;
        }

        try {
            FileReader reader = new FileReader(getTeleporterFilePath(teleportName));
            JSONParser parser = new JSONParser();

            JSONObject timer = (JSONObject) parser.parse(reader);
            timer.put("duration", duration);

            FileWriter fw = new FileWriter(getTeleporterFilePath(teleportName));
            fw.write(timer.toJSONString());
            fw.flush();

            TeleporterManager.getTeleporter(teleportName).setDuration(duration);

            Messages.sendMessageToPlayer(p, "Duration has been updated");
        } catch (IOException e) {
            Messages.sendMessageToPlayer(p, Main.getInstance().getConfig().getString("error"));
            e.printStackTrace();
        } catch (ParseException e) {
            Messages.sendMessageToPlayer(p, Main.getInstance().getConfig().getString("error"));
            e.printStackTrace();
        }
    }

    public static void deserializeJsonFilesIntoTeleporters() {
        File dir = new File(pluginFolderPath + "/teleporters");
        File[] directoryListing = dir.listFiles();
        JSONParser jsonParser = new JSONParser();

        try {
            if (directoryListing != null && directoryListing.length > 0) {
                for (File file : directoryListing) {
                    Messages.sendMessageToConsole("Loading teleporter " + file.getName());
                    if(!file.exists() || !file.getName().contains("json")) {
                        continue;
                    }

                    FileReader fr = new FileReader(file.getPath());
                    JSONObject o = (JSONObject) jsonParser.parse(fr);

                    JSONArray locations = (JSONArray) o.get("locations");
                    ArrayList<Location> locs = new ArrayList<>();

                    for(Object s : locations) {
                        JSONObject fileLoc = (JSONObject) s;
                        World w = Bukkit.getWorld((String) fileLoc.get("world"));
                        double x = (Double) fileLoc.get("x");
                        double y = (Double) fileLoc.get("y");
                        double z = (Double) fileLoc.get("z");
                        double yaw = (Double) fileLoc.get("yaw");
                        double pitch = (Double) fileLoc.get("pitch");
                        Location currLocation = new Location(w, x, y, z, (float) yaw, (float) pitch);
                        locs.add(currLocation);
                    }


                    Teleporter t = new Teleporter((String) o.get("name"), locs, ((Long) o.get("duration")).intValue());
                    TeleporterManager.addTeleporter(t);
                }
            }
        } catch (IOException | ParseException e) {
            e.printStackTrace();
        }
    }
}
