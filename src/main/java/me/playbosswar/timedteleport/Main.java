package me.playbosswar.timedteleport;

import me.playbosswar.timedteleport.commands.Command;
import me.playbosswar.timedteleport.utils.Files;
import me.playbosswar.timedteleport.utils.Messages;
import org.bukkit.plugin.Plugin;
import org.bukkit.plugin.java.JavaPlugin;

public class Main extends JavaPlugin {
    private static Plugin instance;

    @Override
    public void onEnable() {
        instance = this;
        getCommand("timedteleport").setExecutor(new Command());
        getConfig().options().copyDefaults(true);
        saveDefaultConfig();
        Files.createDataFolders();
        Files.deserializeJsonFilesIntoTeleporters();
        Messages.sendMessageToConsole("enabled");
    }

    @Override
    public void onDisable() {
        Messages.sendMessageToConsole("disabled");
        instance = null;
    }

    public static Plugin getInstance() {
        return instance;
    }
}
