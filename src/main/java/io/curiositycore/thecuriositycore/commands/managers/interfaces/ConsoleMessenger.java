package io.curiositycore.thecuriositycore.commands.managers.interfaces;

import net.kyori.adventure.text.Component;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;
/**
 * Interface provides the capability to send messages to the server's console. This interface should be
 * utilised within <code>CommandExecutor</code> instances that require messages to be sent to said console.
 */
public interface ConsoleMessenger {
    /**
     * Sends a message to the <code>CommandSender</code> if it is an instance of the console. If the
     * <code>CommandSender</code> is not a console instance, an error message will be sent to the <code>Player</code>.
     * @param sender   the sender who executed the command.
     * @param message  the message to be sent to the console.
     */
    default void sendConsoleMessage(CommandSender sender, String message){
        if(isMessenger(sender)){
            sender.sendMessage("This command cannot be executed in-game. Please execute this command from the console!");
            return;
        }
        sender.sendMessage(constructMessage(message));
    }

    /**
     * Checks to see if the <code>CommandSender</code> is actually an instance of the console.
     * @param sender The <code>CommandSender</code> who executed the command.
     * @return A <code>boolean</code> to represent the result of console check.
     */
    private boolean isMessenger(CommandSender sender){
        return sender instanceof Player;
    }

    /**
     * Abstract method representing the construction of the message being sent to the console.<br>
     * <i>(This method expects a <code>Component</code> from the Adventure-API to be what is constructed to send to the
     * console. As TheCuriosityCore Library is built in Paper, components can be applied to message methods by default)</i>
     * @param message The unformatted message to send to the <code>Player</code> instance.
     * @return The formatted message, as a <code>Component</code> to send to the <code>Player</code>.
     */
    Component constructMessage(String message);
}
