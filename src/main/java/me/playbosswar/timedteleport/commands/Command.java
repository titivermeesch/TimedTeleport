package me.playbosswar.timedteleport.commands;

import me.playbosswar.timedteleport.Main;
import me.playbosswar.timedteleport.teleporter.TeleportScheduler;
import me.playbosswar.timedteleport.teleporter.Teleporter;
import me.playbosswar.timedteleport.teleporter.TeleporterManager;
import me.playbosswar.timedteleport.utils.Files;
import me.playbosswar.timedteleport.utils.Messages;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

public class Command implements CommandExecutor {
    @Override
    public boolean onCommand(CommandSender sender, org.bukkit.command.Command command, String s, String[] args) {
        if (!(sender instanceof Player)) {
            Messages.sendMessageToConsole("You cannot use this command in the console");
        }

        Player p = (Player) sender;

        if (args.length == 0) {
            Messages.sendHelpMenu(p);
            return true;
        }

        if (args.length == 1) {
            if (args[0].equals("stop")) {
                TeleporterManager.stopSchedule(p);
                return true;
            }

            if(args[0].equals("list")) {
                for(Teleporter t : TeleporterManager.getAllTeleporters()) {
                    Messages.sendMessageToPlayer(p, t.getName());
                }
                return true;
            }
        }

        if (args.length == 2) {
            if (args[0].equals("create")) {
                Files.createNewTimedTeleportDataFile(p, args[1]);
                return true;
            }

            if (args[0].equals("remove")) {
                Files.removeExistingTimedTeleport(p, args[1]);
                return true;
            }

            if (args[0].equals("addpoint")) {
                Files.addPointToTimedTeleport(p, args[1]);
                return true;
            }

            if (args[0].equals("start")) {
                Teleporter t = TeleporterManager.getTeleporter(args[1]);

                if(t == null) {
                    Messages.sendMessageToPlayer(p, "&cThis timer does not exist");
                    return true;
                }

                TeleportScheduler.startTeleportationCycle(p, t);
                return true;
            }
        }

        if (args.length == 3) {
            if (args[0].equals("setduration")) {
                Files.setTimerDuration(p, Integer.parseInt(args[2]), args[1]);
                return true;
            }
        }

        Messages.sendHelpMenu(p);
        return true;
    }
}
