package io.curiositycore.thecuriositycore.commands;

import org.bukkit.command.CommandSender;

/**
 * Abstract to the define the generalisation of any command executed within a Curiosity Core <code>Plugin</code>. Each
 * child class of this Abstract must have their own constructor to define the <code>fields</code>
 */
public abstract class ExecutableCommand {

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
     * Constructor that initializes the command's descriptive fields.
     */
    public ExecutableCommand(){
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
     * Getter for the <code>name</code> of the command.
     * @return the name of the command.
     */
    public String getName() {
        return name;
    }
    /**
     * Getter for the <code>description</code> of the command.
     * @return the description of the command.
     */
    public String getDescription() {
        return description;
    }
    /**
     * Getter for the <code>syntax</code> of the inputs to execute the command.
     * @return the syntax used when executing the command.
     */
    public String getSyntax() {
        return syntax;
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

}
