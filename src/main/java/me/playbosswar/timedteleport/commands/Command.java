package me.playbosswar.timedteleport.commands;

import me.playbosswar.timedteleport.Main;
import me.playbosswar.timedteleport.utils.Files;
import me.playbosswar.timedteleport.utils.Messages;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

public class Command implements CommandExecutor {
    @Override
    public boolean onCommand(CommandSender sender, org.bukkit.command.Command command, String s, String[] args) {
        if(!(sender instanceof Player)) {
            Messages.sendMessageToConsole("You cannot use this command in the console");
        }

        Player p = (Player) sender;

        if(args.length ==0) {
            Messages.sendHelpMenu(p);
            return true;
        }

        if(args.length == 1) {
            if(args[0].equals("stop")) {
                // TODO: Stop teleporter for user
                return true;
            }
            Messages.sendHelpMenu(p);
            return true;
        }

        if(args.length == 2) {
            if(args[0].equals("create")) {
                Files.createNewTimedTeleportDataFile(args[1]);
                Messages.sendMessageToPlayer(p, "A new timed teleport has been created.");
                return true;
            }

            if(args[0].equals("remove")) {
                return true;
            }

            if(args[0].equals("addpoint")) {
                return true;
            }

            if(args[0].equals("duration")) {
                return true;
            }

            if(args[0].equals("start")) {

            }
        }

        Messages.sendHelpMenu(p);
        return true;
    }
}
