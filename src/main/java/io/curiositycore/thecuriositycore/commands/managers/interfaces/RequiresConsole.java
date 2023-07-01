package io.curiositycore.thecuriositycore.commands.managers.interfaces;

import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;
/**
 * Interface that should be implemented by command related classes that require a <code>ConsoleCommandSender</code>
 * instance for operation.
 */
public interface RequiresConsole {
    /**
     * Checks if the class was executed by a <code>ConsoleCommandSender</code>  instance.
     * @param sender Represents the entity that executed the command.
     * @return Returns a <code>boolean</code> representing if the <code>CommandSender</code> was a
     * <code>ConsoleCommandSender</code> instance.
     */
    default boolean isMessengerConsole(CommandSender sender){
        return !(sender instanceof Player);
    }
}
