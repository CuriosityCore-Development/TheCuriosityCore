package io.curiositycore.thecuriositycore.commands.executables;

import lombok.Getter;
import org.bukkit.command.CommandSender;

import java.util.*;

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
     * Method that grabs pre-defined variables for the tabCompletes, based on the <code>trueCommandArgs</code>, an
     * altered args list that contains only the arguments after (and including) the name of the executable.
     * @param arguments The arguments of the parent executable.
     * @return The tab completer for this particular executable.
     */
    public List<String> getTabCompletesForCommand(String[] arguments){
        String[] trueExecutableArgs = initTrueExecutableArgs(arguments);
        Set<String> subCommandSet = this.subCommands.keySet();

        if(subCommandSet.isEmpty()){
            return determineTabCompletes(trueExecutableArgs);
        }

        if(!subCommandSet.contains(trueExecutableArgs[1]) && trueExecutableArgs.length == 2){
            return subCommands.keySet().stream().toList();
        }

        if(trueExecutableArgs.length > 2 && !subCommandSet.contains(trueExecutableArgs[1])){
            return Collections.emptyList();
        }


        return this.subCommands.get(trueExecutableArgs[1]).getTabCompletesForCommand(trueExecutableArgs);

    }

    /**
     * Initialises the true arguments for this command executable. This array only includes the name of the
     * executable and the arguments after it within the original arguments array.
     * @param arguments The arguments of the parent executable.
     * @return The true arguments array for the executable.
     */
    protected String[] initTrueExecutableArgs(String[] arguments){
        boolean indexFound = false;
        List<String> argsToReturn = new ArrayList<>();
        ListIterator<String> argumentIterator = Arrays.stream(arguments).toList().listIterator();
        while(argumentIterator.hasNext()){
            String currentArgument = argumentIterator.next();
            if(indexFound || currentArgument.contains(this.name)){
                indexFound = true;
                argsToReturn.add(currentArgument);

            }


        }
        return argsToReturn.toArray(new String[0]);
    }

    /**
     * Abstract method to define the generalisation of determining the tab completes for the executable if there are no
     * other sub-executables to choose from a layer down from this executable.
     * @param trueCommandArgs The true arguments for this specific executable.
     * @return The tab completes to be sent to the <code>Player</code>.
     */
    protected abstract List<String> determineTabCompletes(String[] trueCommandArgs);

}
