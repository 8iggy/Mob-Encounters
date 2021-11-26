package me.piggy.mobencounters.Utils;

import org.bukkit.ChatColor;
import org.bukkit.entity.Player;

public class MessageUtils {

    public void sendWaterMarkMessage(Player p, String msg) {

        p.sendMessage(ChatColor.RED + "[Mob Encounters] " + msg);

    }

}
