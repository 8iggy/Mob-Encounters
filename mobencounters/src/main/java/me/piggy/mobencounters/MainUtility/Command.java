package me.piggy.mobencounters.MainUtility;

import me.piggy.mobencounters.Encounter;
import me.piggy.mobencounters.Mobencounters;
import me.piggy.mobencounters.Utils.MessageUtils;
import org.bukkit.ChatColor;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.EntityType;
import org.bukkit.entity.Player;
import org.jetbrains.annotations.NotNull;

public class Command implements CommandExecutor {

    MessageUtils msg = new MessageUtils();

    @Override
    public boolean onCommand(@NotNull CommandSender sender, @NotNull org.bukkit.command.Command command, @NotNull String label, @NotNull String[] args) {

        if (sender instanceof Player) {

            Player p = (Player) sender;

            if (args.length > 0) {
                if (args[0].equalsIgnoreCase("create")) {
                    if (p.hasPermission("mobencounters.create")) {
                        if (args.length == 2) {
                            Encounter encounter = new Encounter(args[1], "MOB", "MOB", 0);
                            Mobencounters.getInstance().encounters.put(args[1], encounter);
                            Mobencounters.getInstance().data.setEncounters();
                            msg.sendWaterMarkMessage(p, ChatColor.GREEN + "Created " + args[1]);
                        } else {
                            msg.sendWaterMarkMessage(p, ChatColor.RED + "Invalid Amount Of Arguments");
                        }
                    } else {
                        msg.sendWaterMarkMessage(p, ChatColor.RED + "Insufficient Permission");
                    }
                } else if (args[0].equalsIgnoreCase("test")) {
                    if (p.hasPermission("mobencounters.test")) {
                        if (args.length == 2) {
                            p.getWorld().spawnEntity(p.getLocation(), EntityType.valueOf(Mobencounters.getInstance().encounters.get(args[1]).getTriggermob()));
                            msg.sendWaterMarkMessage(p, ChatColor.GREEN + "Spawned Test Entity!");
                        } else {
                            msg.sendWaterMarkMessage(p, ChatColor.RED + "Invalid Amount Of Arguments");
                        }
                    } else {
                        msg.sendWaterMarkMessage(p, ChatColor.RED + "Insufficient Permission");
                    }
                } else if (args[0].equalsIgnoreCase("help")) {
                    if (args.length == 1) {
                        p.sendMessage(ChatColor.RED + "Mob Encounters");
                        p.sendMessage(ChatColor.RED + "-----------------------");
                        p.sendMessage(ChatColor.RED + "/me create [name of encounter]");
                        p.sendMessage(ChatColor.RED + "/me test [name of encounter]");
                        p.sendMessage(ChatColor.RED + "-----------------------");
                    }
                } else {
                    msg.sendWaterMarkMessage(p, ChatColor.RED + "Invalid Arguments");
                }
            } else {
                p.performCommand("me help");
            }
        }
        return true;
    }
}
