package home.lifeonblack.commands;

import home.lifeonblack.Home;
import home.lifeonblack.Util.Util;
import org.bukkit.command.Command;
import org.bukkit.command.CommandSender;
import org.bukkit.command.ConsoleCommandSender;
import org.bukkit.command.RemoteConsoleCommandSender;
import org.bukkit.entity.Player;

/**
 * Created by Raymart on 6/20/2017.
 */
public abstract class AbstractCommand {

    /**
     * The command's sender.
     */
    private final CommandSender sender;

    /**
     * The command's name.
     */
    private final String name;

    /**
     * The command's description.
     */
    private final String description;

    /**
     * The command's description.
     */
    private final String permission;

    /**
     * The command's usage.
     */
    private final String usage;

    /**
     * The sub permissions.
     */
    private final String[] subPermissions;

    /**
     * Construct the object.
     *
     * @param sender      the sender of the command
     * @param description the description of the command
     * @param permission  the permission to use the command
     * @param usage       the proper usage of the command
     */
    public AbstractCommand(CommandSender sender, String name, String description, String permission, String[] subPermissions, String usage) {
        this.sender = sender;
        this.name = name;
        this.description = description;
        this.permission = permission;
        this.subPermissions = subPermissions;
        this.usage = usage;
    }

    /**
     * Gets the sender of the command.
     *
     * @return the command's sender
     */
    public CommandSender getSender() {
        return sender;
    }

    /**
     * Gets the name of the command.
     *
     * @return the command's name
     */
    public String getName() {
        return name;
    }

    /**
     * Gets the description of the command.
     *
     * @return the command's description
     */
    public String getDescription() {
        return description;
    }

    /**
     * Gets the permission associated with the command.
     *
     * @return the command's permission
     */
    public String getPermission() {
        return permission;
    }

    /**
     * Gets the sub permissions associated with the command.
     *
     * @return the command's sub permissions
     */
    public String[] getSubPermissions() {
        return subPermissions;
    }

    /**
     * Gets the proper usage for the command.
     *
     * @return the command's usage
     */
    public String getUsage() {
        return usage;
    }

    /**
     * Checks whether the sender has permission to do the command.
     *
     * @return does the sender have permission
     */
    public boolean hasPermission() {
        return sender.hasPermission(permission) || isSenderConsole() || isSenderRemoteConsole();
    }

    /**
     * Checks whether the sender has permission to do the command.
     *
     * @param sub the sub permission to check
     * @return does the sender have permission
     */
    public boolean hasPermission(String sub) {
        String permission = this.permission + "." + sub;
        return sender.hasPermission(permission) || isSenderConsole() || isSenderRemoteConsole();
    }

    public boolean isPermissible(String permission) {
        return sender.hasPermission(permission) || isSenderConsole() || isSenderRemoteConsole();
    }

    /**
     * Sends the usage message to the sender.
     */
    public void sendUsage() {
        sender.sendMessage(Util.getLocalizedMessage("Incorrect Usage"));
    }

    /**
     * Checks whether the sender is a .
     *
     * @return is the sender a player
     */
    public boolean isSenderPlayer() {
        return (sender instanceof Player);
    }

    /**
     * Checks whether the sender is the console.
     *
     * @return is the sender console
     */
    public boolean isSenderConsole() {
        return (sender instanceof ConsoleCommandSender);
    }

    /**
     * Checks whether the sender is rcon.
     *
     * @return is the sender rcon
     */
    public boolean isSenderRemoteConsole() {
        return (sender instanceof RemoteConsoleCommandSender);
    }

    /**
     * Executes the command.
     *
     * @param sender  the sender of the command
     * @param command the command being done
     * @param label   the name of the command
     * @param args    the arguments supplied
     */
    public abstract void execute(CommandSender sender, Command command, String label, String[] args);
}
