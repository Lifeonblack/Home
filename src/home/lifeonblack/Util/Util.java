package home.lifeonblack.Util;

import home.lifeonblack.files.Language;
import org.bukkit.ChatColor;

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
}
