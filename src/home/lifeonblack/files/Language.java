package home.lifeonblack.files;

import org.bukkit.configuration.file.FileConfiguration;
import org.bukkit.configuration.file.YamlConfiguration;
import org.bukkit.plugin.Plugin;

import java.io.File;
import java.io.IOException;

/**
 * Created by Raymart on 6/20/2017.
 */
public class Language {

    File file;
    FileConfiguration config;
    private Language() {}
    static Language instance = new Language();

    public static Language getInstance() {
        return instance;
    }

    public void setup(Plugin plugin) {
        plugin.getDataFolder().mkdir();
        file = new File(plugin.getDataFolder(), "lang.yml");
        if(!file.exists()) {
            plugin.saveResource("lang.yml", false);
        }
        config = YamlConfiguration.loadConfiguration(file);
    }

    public void save() {
        try {
            config.save(file);
        }catch(IOException e) {
            e.printStackTrace();
        }
    }

    public void reload() {
        config = YamlConfiguration.loadConfiguration(file);
    }

    public FileConfiguration getConfig() {
        return config;
    }
}
