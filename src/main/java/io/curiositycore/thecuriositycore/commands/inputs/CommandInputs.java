package io.curiositycore.thecuriositycore.commands.inputs;

import lombok.Getter;
import org.bukkit.command.Command;
import org.bukkit.command.CommandSender;
import org.jetbrains.annotations.NotNull;
//TODO in future phases of the Library, convert this into a record (would be good for good code practice.
/**
 * Represents the parameters of the <code>onCommand</code> method of the <code>CommandExecutor</code> interface.<br>
 * <i>(Purpose of this class is to allow for better readability of the Library's
 * {@linkplain  io.curiositycore.thecuriositycore.commands commands} package.)</i>
 */
@Getter
public class CommandInputs {

    /**
     * The sender who initiated the command.
     */
    private final CommandSender sender;

    /**
     * The command that was executed.
     */
    private final Command command;

    /**
     * The label that was used to execute the command.
     */
    private final String label;

    /**
     * The array of arguments provided with the command.
     */
    private final String[] args;

    /**
     * Constructs a new CommandInputs instance.
     *
     * @param sender The sender who initiated the command. This must not be null.
     * @param command The command that was executed. This must not be null.
     * @param label The label that was used to execute the command. This must not be null.
     * @param args The array of arguments provided with the command. This must not be null.
     */
    public CommandInputs(@NotNull CommandSender sender, @NotNull Command command, @NotNull String label, @NotNull String[] args){
        this.sender = sender;
        this.command = command;
        this.label = label;
        this.args = args;

    }
}
