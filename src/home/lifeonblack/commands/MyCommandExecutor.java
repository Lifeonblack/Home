package home.lifeonblack.commands;

import home.lifeonblack.Home;
import home.lifeonblack.Util.Util;
import org.bukkit.Location;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.command.ConsoleCommandSender;
import org.bukkit.entity.Player;

/**
 * Created by Raymart on 6/20/2017.
 */
public class MyCommandExecutor implements CommandExecutor {

    @Override
    public boolean onCommand(CommandSender sender, Command cmd, String label, String[] args) {
        AbstractCommand command = new HelpCommand(sender);

        if(args.length == 0) {
            if(sender instanceof ConsoleCommandSender) {
                command.execute(sender, cmd, label, args);
                return true;
            }
            if(!sender.hasPermission("home.go")) {
                sender.sendMessage(Util.getLocalizedMessage("No Permission"));
                return true;
            }
            Player player = (Player) sender;
            if(!Util.homeExisted(player.getName(), Home.getInstance(), "default")) {
                player.sendMessage(Util.getLocalizedMessage("No Home"));
                return true;
            }
            Location home = Util.getHome(player.getName(), Home.getInstance(), "default");
            player.teleport(home);
            player.sendMessage(Util.getLocalizedMessage("Home Teleport").replace("%home%", "default"));
            return true;
        }

        if(args[0].equalsIgnoreCase("help")) {
            command = new HelpCommand(sender);
            command.execute(sender, cmd, label, args);
            return true;
        }
        if(args[0].equalsIgnoreCase("set")) {
            command = new SetCommand(sender);
            command.execute(sender, cmd, label, args);
            return true;
        }
        if(args[0].equalsIgnoreCase("go")) {
            command = new GoHomeCommand(sender);
            command.execute(sender, cmd, label, args);
            return true;
        }
        sender.sendMessage(Util.getLocalizedMessage("Incorrect Usage"));
        return false;
    }
}
