package home.lifeonblack.commands;

import home.lifeonblack.Util.Util;
import home.lifeonblack.files.Language;
import org.bukkit.command.Command;
import org.bukkit.command.CommandSender;
import org.bukkit.configuration.ConfigurationSection;

/**
 * Created by Raymart on 6/20/2017.
 */
public class HelpCommand extends AbstractCommand {

    public static final String NAME = "Help";

    public static final String DESCRIPTION = "Displays help information.";

    public static final String PERMISSION = "home.help";

    public static final String USAGE = "/home help";

    public static final String[] subPermissions = {""};

    public HelpCommand(CommandSender sender) {
        super(sender, NAME, DESCRIPTION, PERMISSION, subPermissions, USAGE);
    }

    @Override
    public void execute(CommandSender sender, Command cmd, String label, String[] args) {
        if(!hasPermission()) {
            sender.sendMessage(Util.getLocalizedMessage("No Permission"));
            return;
        }
        Language lang = Language.getInstance();
        ConfigurationSection help = lang.getConfig().getConfigurationSection(Util.getLanguage() + ".Help");

        for(String s : help.getKeys(false)) {
            if(sender.hasPermission("home." + s)) {
                sender.sendMessage(Util.colorize(lang.getConfig().getString(Util.getLanguage() + ".Help." + s)));
            }
        }
    }
}
