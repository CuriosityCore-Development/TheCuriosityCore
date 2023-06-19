package io.curiositycore.thecuriositycore.commands;

import lombok.Getter;
import org.bukkit.command.CommandSender;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Getter
/**
 * Abstract to the define the generalisation of any command executed within a Curiosity Core <code>Plugin</code>. Each
 * child class of this Abstract must have their own constructor to define the <code>fields</code>
 */
public abstract class CommandExecuteable {

    /**
     * The name of the <code>SubCommand</code>, as a <code>String</code>.
     */
    protected final String name;
    /**
     * The detailed description of the <code>SubCommand</code> functionality, as a <code>String</code>.
     */
    protected final String description;
    /**
     * The syntax to use when executing a command that includes this <code>SubCommand</code>, as a <code>String</code>.
     */
    protected final String syntax;
    protected Map<String, CommandExecuteable> subCommands = new HashMap<>();


    /**
     * Constructor that initializes the command's descriptive fields.
     */
    public CommandExecuteable(){
        this.name = initName();
        this.description = initDescription();
        this.syntax = initSyntax();
    }

    /**
     * Performs the functionality of the command. Always called upon by the command's.
     * respective <code>ExecutableCommandManager</code>.
     * @param sender the sender who executed the command.
     * @param commandArguments the arguments the sender inputted upon execution of the command.
     */
    public abstract void perform(CommandSender sender,String[] commandArguments);
    /**
     * Add a sub-command to this command.
     *
     * @param command the sub-command to be added
     */
    public void addSubCommand(CommandExecuteable command) {
        subCommands.put(command.getName(), command);
    }

    /**
     * Get a sub-command based on its name.
     *
     * @param commandName the name of the command
     * @return the CommandExecuteable instance or null if it doesn't exist
     */
    public CommandExecuteable getSubCommand(String commandName) {
        return subCommands.get(commandName);
    }

    /**
     * Initiate the <code>name</code> field of the class.
     * @return the name of the command.
     */
    protected abstract String initName();
    /**
     * Initiate the <code>description</code> field of the class.
     * @return the description of the command.
     */
    protected abstract String initDescription();
    /**
     * Initiate the <code>syntax</code> field of the class.
     * @return the syntax used when executing the command.
     */
    protected abstract String initSyntax();

    protected abstract List<String> getTabCompletesForCommand(String[] arguments);

}
