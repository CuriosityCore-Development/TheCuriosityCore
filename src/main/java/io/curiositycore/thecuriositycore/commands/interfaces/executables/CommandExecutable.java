package io.curiositycore.thecuriositycore.commands.interfaces.executables;

import lombok.Getter;
import org.bukkit.command.CommandSender;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Getter
/**
 * Abstract to the define the generalisation of any executeable within a command within a Curiosity Core
 * <code>Plugin</code>. This generalisation applies to any type of command executeable (including sub-executeables).<br>
 *  * <i>(Supplementary interfaces can be utilised to add additional functionality to these executables)</i>
 */
public abstract class CommandExecutable {

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

    /**
     * Map containing key-value pairs of sub-command executable names and sub-command executables respectively.
     */
    protected Map<String, CommandExecutable> subCommands = new HashMap<>();


    /**
     * Constructor that initializes the command's descriptive fields.
     */
    public CommandExecutable(){
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
     * Add a sub-subCommandExecutable to this subCommandExecutable.
     *
     * @param subCommandExecutable the sub-subCommandExecutable to be added
     */
    public void addSubCommand(CommandExecutable subCommandExecutable) {
        subCommands.put(subCommandExecutable.getName(), subCommandExecutable);
    }

    /**
     * Get a sub-command based on its name.
     *
     * @param subCommandExecutableName the name of the command
     * @return the CommandExecuteable instance or null if it doesn't exist
     */
    public CommandExecutable getSubCommand(String subCommandExecutableName) {
        return subCommands.get(subCommandExecutableName);
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

    /**
     * Abstract method that defines the generalisation of getting the tab completer for this particular command
     * executable. This allows for more dynamic tab-completes for complex commands with multiple sub-command executables.
     * @param arguments The arguments of the command that executed this command executable.
     * @return The tab completer for this particular executable.
     */
    public abstract List<String> getTabCompletesForCommand(String[] arguments);

}
