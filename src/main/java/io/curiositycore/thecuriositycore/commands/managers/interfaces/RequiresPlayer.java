package io.curiositycore.thecuriositycore.commands.managers.interfaces;

import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

/**
 * Interface that should be implemented by command related classes that require a <code>Player</code> instance
 * for operation.
 */
public interface RequiresPlayer {

    /**
     * Determines whether the command was executed by a 'Player' instance or not.
     * @param sender Represents the entity that executed the command.
     * @return Returns 'true' if the command was executed by a 'Player' instance, 'false' otherwise.
     */
    default boolean isMessengerPlayer(CommandSender sender){
        return sender instanceof Player;
    }

}
