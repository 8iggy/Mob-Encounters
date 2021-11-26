package me.piggy.mobencounters.Config;

import me.piggy.mobencounters.Encounter;
import me.piggy.mobencounters.Mobencounters;

import java.util.ArrayList;
import java.util.List;

public class Data {

    DataManager datamanager;

    public Data() {

        datamanager = Mobencounters.getInstance().datamanager;

    }

    public void setEncounters() {
        for (Encounter en : Mobencounters.getInstance().encounters.values()) {
            datamanager.getConfig().set("encounters." + en.getName() + ".triggerentity", en.getTriggermob());
            datamanager.getConfig().set("encounters." + en.getName() + ".mob", en.getMob());
            datamanager.getConfig().set("encounters." + en.getName() + ".percentage", en.getPercentage() + "%");
        }
        datamanager.saveConfig();
    }

    public void getEncounters() {
        List<String> names = new ArrayList<>();
        if (datamanager.getConfig().getConfigurationSection("encounters") != null) {
            names.addAll(datamanager.getConfig().getConfigurationSection("encounters").getKeys(false));
            for (String string : names) {

                String triggerentity = datamanager.getConfig().getString("encounters." + string + ".triggerentity");
                String mob = datamanager.getConfig().getString("encounters." + string + ".mob");

                String fraction = datamanager.getConfig().getString("encounters." + string + ".percentage");
                String fractionraw = fraction.replace("%", "");
                int percentage = Integer.parseInt(fractionraw);

                Encounter encounter = new Encounter(string, mob, triggerentity, percentage);
                Mobencounters.getInstance().encounters.put(string, encounter);

            }
        }
    }

}
