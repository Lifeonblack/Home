package home.lifeonblack.commands;

import home.lifeonblack.Util.Util;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;

/**
 * Created by Raymart on 6/20/2017.
 */
public class MyCommandExecutor implements CommandExecutor {

    @Override
    public boolean onCommand(CommandSender sender, Command cmd, String label, String[] args) {
        AbstractCommand command = new HelpCommand(sender);

        if(args.length == 0) {
            command.execute(sender, cmd, label, args);
            return true;
        }

        switch(args[0].toLowerCase()) {
            case "help":
                command = new HelpCommand(sender);
                command.execute(sender, cmd, label, args);
                break;
            case "set":
                command = new SetCommand(sender);
                command.execute(sender, cmd, label, args);
            default:
                sender.sendMessage(Util.getLocalizedMessage("Unknown Command"));
        }
        return false;
    }
}
