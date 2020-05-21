package me.playbosswar.timedteleport;

import me.playbosswar.timedteleport.commands.Command;
import me.playbosswar.timedteleport.utils.Messages;
import org.bukkit.plugin.Plugin;
import org.bukkit.plugin.java.JavaPlugin;

public class Main extends JavaPlugin {
    private static Plugin instance;

    @Override
    public void onEnable() {
        instance = this;
        getConfig().options().copyDefaults();
        saveDefaultConfig();
        getCommand("timedteleport").setExecutor(new Command());
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
