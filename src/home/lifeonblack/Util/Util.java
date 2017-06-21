package home.lifeonblack.Util;

import home.lifeonblack.files.Language;
import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.Location;
import org.bukkit.World;
import org.bukkit.configuration.ConfigurationSection;
import org.bukkit.configuration.file.FileConfiguration;
import org.bukkit.configuration.file.YamlConfiguration;
import org.bukkit.entity.Player;
import org.bukkit.plugin.Plugin;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Set;

/**
 * Created by Raymart on 6/20/2017.
 */
public class Util {
    static Language lang = Language.getInstance();
    static String language = lang.getConfig().getString("Language");

    public static String colorize(String toColor) {
        return ChatColor.translateAlternateColorCodes('&', toColor);
    }

    public static String getLocalizedMessage(String path) {
        return colorize(lang.getConfig().getString("lang-" + language.toLowerCase() + "." + path));
    }

    public static String getLanguage() {
        return "lang-" + language.toLowerCase();
    }

    public static void saveHome(Location location, String playername, Plugin plugin, String homenam) {
        String homename = homenam.toLowerCase();
        String world = location.getWorld().getName();
        double x = location.getX();
        double y = location.getY();
        double z = location.getZ();
        double yaw = location.getYaw();
        double pitch = location.getPitch();
        String name = playername;

        plugin.getDataFolder().mkdir();
        File file = new File(plugin.getDataFolder() + "/homes", name + ".yml");
        if(!file.exists()) {
            try {
                file.createNewFile();
            }catch(IOException e) {
                e.printStackTrace();
            }
        }
        FileConfiguration config = YamlConfiguration.loadConfiguration(file);
        config.set(homename + ".world", world);
        config.set(homename + ".x", x);
        config.set(homename + ".y", y);
        config.set(homename + ".z", z);
        config.set(homename + ".yaw", yaw);
        config.set(homename + ".pitch", pitch);
        try {
            config.save(file);
        }catch(IOException e) {
            e.printStackTrace();
        }
    }

    public static boolean homeExisted(String playername, Plugin plugin, String homenam) {
        String homename = homenam.toLowerCase();
        String name = playername;
        File file = new File(plugin.getDataFolder() + "/homes", name + ".yml");
        if(!file.exists()) {
            return false;
        }
        FileConfiguration config = YamlConfiguration.loadConfiguration(file);
        if(config.contains(homename)) {
            return true;
        }
        return false;
    }

    public static Location getHome(String playername, Plugin plugin, String homenam) {
        String homename = homenam.toLowerCase();
        String name = playername;
        File file = new File(plugin.getDataFolder() + "/homes", name + ".yml");
        FileConfiguration config = YamlConfiguration.loadConfiguration(file);
        if(!file.exists()) {
            return null;
        }
        World world = Bukkit.getWorld(config.getString(homename + ".world"));
        double x = config.getDouble(homename + ".x");
        double y = config.getDouble(homename + ".y");
        double z = config.getDouble(homename + ".z");
        double yaw = config.getDouble(homename + ".yaw");
        double pitch = config.getDouble(homename + ".pitch");
        Location location = new Location(world, x, y, z, (float) yaw, (float)pitch);
        return location;
    }

    public static Integer getHomeCount(String playername, Plugin plugin) {
        String name = playername;
        File file = new File(plugin.getDataFolder() + "/homes", name + ".yml");
        FileConfiguration config = YamlConfiguration.loadConfiguration(file);
        Set<String> set = config.getKeys(false);
        if(set == null) {
            return 0;
        }
        List<String> list = new ArrayList<>(set);
        return list.size();
    }

    public static void deleteHome(String playername, Plugin plugin, String homenam) {
        String homename = homenam.toLowerCase();
        File file = new File(plugin.getDataFolder() + "/homes", playername + ".yml");
        FileConfiguration config = YamlConfiguration.loadConfiguration(file);
        config.set(homename, null);
    }

    public static void clearHome(String playername, Plugin plugin) {
        File file = new File(plugin.getDataFolder() + "/homes", playername + ".yml");
        FileConfiguration config = YamlConfiguration.loadConfiguration(file);
        for(String homenames : config.getKeys(false)) {
            config.set(homenames, null);
        }
    }
}
