package home.lifeonblack;

import home.lifeonblack.commands.MyCommandExecutor;
import home.lifeonblack.files.Language;
import org.bukkit.plugin.java.JavaPlugin;

/**
 * Created by Raymart on 6/20/2017.
 */
public class Home extends JavaPlugin {
    static Home instance;
    Language language = Language.getInstance();

    public void onEnable() {
        this.instance = this;
        getLogger().info("has been enabled");
        getCommand("home").setExecutor(new MyCommandExecutor());
        language.setup(this);
    }

    public void onDisable() {
        getLogger().info("has been disabled");
    }

    public static Home getInstance() {
        return instance;
    }

}
