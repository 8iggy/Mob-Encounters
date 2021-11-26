package me.piggy.mobencounters.Config;

import me.piggy.mobencounters.Mobencounters;
import org.bukkit.configuration.file.FileConfiguration;
import org.bukkit.configuration.file.YamlConfiguration;

import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.logging.Level;

public class DataManager {

    private Mobencounters plugin;
    private FileConfiguration dataconfig = null;
    private File configfile = null;

    public DataManager(Mobencounters plugin) {
        this.plugin = plugin;
        saveDefaultConfig();
    }

    public void reloadConfig() {
        if (this.configfile == null) {
            this.configfile = new File(this.plugin.getDataFolder(), "encounters.yml");
        }

        this.dataconfig = YamlConfiguration.loadConfiguration(this.configfile);
        InputStream defaultStream = this.plugin.getResource("encounters.yml");

        if (defaultStream != null) {
            YamlConfiguration defaultConfig = YamlConfiguration.loadConfiguration(new InputStreamReader(defaultStream));
            this.dataconfig.setDefaults(defaultConfig);
        }
    }

    public FileConfiguration getConfig() {
        if (this.dataconfig == null) {
            reloadConfig();
        }
        return this.dataconfig;
    }

    public void saveConfig() {
        if (this.dataconfig == null || this.configfile == null) {
            return;
        }
        try {
            this.getConfig().save(this.configfile);
        } catch (IOException e) {
            this.plugin.getLogger().log(Level.SEVERE, "Could Not Save Config To " + this.configfile, e);
        }
    }

    public void saveDefaultConfig() {
        if (this.configfile == null) {
            this.configfile = new File(this.plugin.getDataFolder(), "encounters.yml");
        }
        if (!this.configfile.exists()) {
            this.plugin.saveResource("encounters.yml", false);
        }
    }
}
