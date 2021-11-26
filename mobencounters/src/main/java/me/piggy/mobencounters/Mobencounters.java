package me.piggy.mobencounters;

import me.piggy.mobencounters.Config.Data;
import me.piggy.mobencounters.Config.DataManager;
import me.piggy.mobencounters.MainUtility.Command;
import me.piggy.mobencounters.MainUtility.Event;
import org.bukkit.plugin.java.JavaPlugin;

import java.util.HashMap;

public final class Mobencounters extends JavaPlugin {

    private static Mobencounters instance;

    public DataManager datamanager;
    public Data data;
    public HashMap<String, Encounter> encounters = new HashMap<>();

    @Override
    public void onEnable() {

        instance = this;
        datamanager = new DataManager(this);
        data = new Data();

        getServer().getPluginManager().registerEvents(new Event(), this);
        getCommand("mobencounters").setExecutor(new Command());

        data.getEncounters();
    }

    @Override
    public void onDisable() {

    }

    public static Mobencounters getInstance() {
        return instance;
    }
}
