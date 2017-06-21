package home.lifeonblack.commands;

import home.lifeonblack.Home;
import home.lifeonblack.Util.Util;
import org.bukkit.Bukkit;
import org.bukkit.OfflinePlayer;
import org.bukkit.command.Command;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

/**
 * Created by Raymart on 6/21/2017.
 */
public class SetCommand extends AbstractCommand {
    public static final String NAME = "Set";

    public static final String DESCRIPTION = "Set home location.";

    public static final String PERMISSION = "home.set";

    public static final String USAGE = "/home set";

    public static final String[] subPermissions = {""};


    public SetCommand(CommandSender sender) {
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
        }

        if(args.length == 1) {
            sender.sendMessage(Util.getLocalizedMessage("Incorrect Usage"));
            return;
        }

        // home set [name]
        if(args.length == 2) {
            Player player = (Player) sender;
            if(Util.homeExisted(player.getName(), Home.getInstance(), args[1])) {
                player.sendMessage(Util.getLocalizedMessage("Home Already Existed"));
                return;
            }
            Util.saveHome(player.getLocation(), player.getName(), Home.getInstance(), args[1]);
            player.sendMessage(Util.getLocalizedMessage("Home Saved Successfully"));
            return;
        }
        // home set <player> [name]
        if(args.length == 3) {
            if(!hasPermission(".admin")) {
                sender.sendMessage(Util.getLocalizedMessage("No Permission"));
                return;
            }
            Player psender = (Player) sender;
            Player player = Bukkit.getPlayer(args[1]);
            if(player == null) {
                OfflinePlayer offplayer = Bukkit.getOfflinePlayer(args[1]);
                if(offplayer == null) {
                    sender.sendMessage(Util.getLocalizedMessage("Player Not Exist"));
                    return;
                }
                if(Util.homeExisted(offplayer.getName(), Home.getInstance(), args[2])) {
                    sender.sendMessage(Util.getLocalizedMessage("Home Already Existed"));
                    return;
                }
                Util.saveHome(psender.getLocation(), offplayer.getName(), Home.getInstance(), args[2]);
                psender.sendMessage(Util.getLocalizedMessage("Home Saved Successfully"));
                return;
            }
            if(Util.homeExisted(player.getName(), Home.getInstance(), args[2])) {
                sender.sendMessage(Util.getLocalizedMessage("Home Already Existed"));
                return;
            }
            Util.saveHome(psender.getLocation(), player.getName(), Home.getInstance(), args[2]);
            psender.sendMessage(Util.getLocalizedMessage("Home Saved Successfully"));
            return;
        }
    }
}
