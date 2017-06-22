package home.lifeonblack.Listeners;

import org.bukkit.command.Command;
import org.bukkit.command.CommandSender;
import org.bukkit.command.TabCompleter;
import org.bukkit.util.StringUtil;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;

/**
 * Created by Raymart on 6/21/2017.
 */
public class HomeTabCompleter implements TabCompleter {

    private final String[] COMMANDS = {"help", "set", "go", "list", "replace", "invite", "visit", "delete", "vtoggle", "fvisit", "clear", "reload"};

    @Override
    public List<String> onTabComplete(CommandSender sender, Command cmd, String label, String[] args) {
        if(sender.isOp() || sender.hasPermission("home.admin") || sender.hasPermission("home." + args[0])) {
            List<String> completion = new ArrayList<>();

            if(args.length == 1) {
                String partialCommand = args[0];
                List<String> commands = new ArrayList<>(Arrays.asList(COMMANDS));
                StringUtil.copyPartialMatches(partialCommand, commands, completion);
            }

            Collections.sort(completion);
            return completion;
        }
        return null;
    }
}
