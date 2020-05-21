package me.playbosswar.timedteleport.utils;

import me.playbosswar.timedteleport.Main;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;

import org.json.simple.JSONObject;

public class Files {
    static String pluginFolderPath = Main.getInstance().getDataFolder().getPath();

    public static void createDataFolder() {
        File file = new File(pluginFolderPath + "/data");
        file.mkdir();
    }

    public static void createNewTimedTeleportDataFile(String name) {
        try {
            FileWriter jsonFile = new FileWriter(pluginFolderPath + "/" + name + ".json");
            JSONObject timed = new JSONObject();

            timed.put("name", name);

            jsonFile.write(timed.toJSONString());
            jsonFile.flush();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
