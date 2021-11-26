package me.piggy.mobencounters.MainUtility;

import me.piggy.mobencounters.Encounter;
import me.piggy.mobencounters.Mobencounters;
import org.bukkit.entity.Entity;
import org.bukkit.entity.EntityType;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.entity.EntityDeathEvent;

public class Event implements Listener {

    @EventHandler
    public void onPlayerKill(EntityDeathEvent e) {
        if (e.getEntity().getKiller() != null) {
            Entity dead = e.getEntity();
            Player p = e.getEntity().getKiller();
            for (Encounter en : Mobencounters.getInstance().encounters.values()) {
                if (dead.getType() == EntityType.valueOf(en.getTriggermob())) {
                    en.checkRate(p, e.getEntity().getLocation());
                }
            }
        }
    }

}
