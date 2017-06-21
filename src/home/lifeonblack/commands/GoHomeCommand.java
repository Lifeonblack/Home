package home.lifeonblack.commands;

import home.lifeonblack.Home;
import home.lifeonblack.Util.Util;
import org.bukkit.Location;
import org.bukkit.command.Command;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

/**
 * Created by Raymart on 6/21/2017.
 */
public class GoHomeCommand extends AbstractCommand {
    public static final String NAME = "Go";

    public static final String DESCRIPTION = "Go home.";

    public static final String PERMISSION = "home.go";

    public static final String USAGE = "/home go";

    public static final String[] subPermissions = {""};

    public GoHomeCommand(CommandSender sender) {
        super(sender, NAME, DESCRIPTION, PERMISSION, subPermissions, USAGE);
    }

    @Override
    public void execute(CommandSender sender, Command cmd, String label, String[] args) {
        if(!hasPermission()) {
            sender.sendMessage(Util.getLocalizedMessage("No Permission"));
            return;
        }
        if(isSenderConsole()) {
            sender.sendMessage(Util.getLocalizedMessage("Console Error"));
            return;
        }

        // home go
        if(args.length == 1 || args.length == 0) {
            Player player = (Player) sender;
            if(!Util.homeExisted(player.getName(), Home.getInstance(), "default")) {
                player.sendMessage(Util.getLocalizedMessage("No Home"));
                return;
            }
            Location home = Util.getHome(player.getName(), Home.getInstance(), "default");
            player.teleport(home);
            player.sendMessage(Util.getLocalizedMessage("Home Teleport").replace("%home%", "default"));
            return;
        }

        // home go <name>
        if(args.length == 2) {
            Player player = (Player) sender;
            if(!Util.homeExisted(player.getName(), Home.getInstance(), args[1])) {
                player.sendMessage(Util.getLocalizedMessage("No Home"));
                return;
            }
            Location home = Util.getHome(player.getName(), Home.getInstance(), args[1].toLowerCase());
            player.teleport(home);
            player.sendMessage(Util.getLocalizedMessage("Home Teleport").replace("%home%", args[1]));
            return;
        }
        return;
    }
}
