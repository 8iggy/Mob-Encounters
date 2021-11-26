package me.piggy.mobencounters;

import io.lumine.xikage.mythicmobs.MythicMobs;
import io.lumine.xikage.mythicmobs.api.exceptions.InvalidMobTypeException;
import org.bukkit.ChatColor;
import org.bukkit.Location;
import org.bukkit.entity.Player;

import java.util.Random;

public class Encounter {

    private String name;
    private String mob;
    private String triggermob;

    private int percentage;

    public Encounter(String name, String mob, String triggermob, int percentage) {

        this.name = name;
        this.mob = mob;
        this.triggermob = triggermob;
        this.percentage = percentage;

    }

    public void checkRate(Player p, Location loc) {

        Random random = new Random();

        if (random.nextInt(100) <= percentage) {
            try {
                MythicMobs.inst().getAPIHelper().spawnMythicMob(mob, loc);
            } catch (InvalidMobTypeException e) {
                p.sendMessage(ChatColor.RED + "Invalid Mob. Mob = " + mob);
                e.printStackTrace();
            }
        }
    }

    public String getName() {
        return name;
    }

    public String getMob() {
        return mob;
    }

    public String getTriggermob() {
        return triggermob;
    }

    public int getPercentage() {
        return percentage;
    }
}
