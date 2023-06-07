package io.curiositycore.thecuriositycore.commands.subcommands;

import io.curiositycore.thecuriositycore.commands.ExecutableCommand;
/**
 * Abstract to the define the generalisation of any sub-command executed within a Curiosity Core <code>Plugin</code>. Each
 * child class of this Abstract must have their own constructor to define the sub-command's unique values for
 * command tab completion.
 */
public abstract class ExecutableSubCommand extends ExecutableCommand {
    /**
     * An arguments fed through from the <code>ExecutableCommand</code> defining what to tab-completes to show a <code>Player</code>
     * when the sub-command parameter has been called by its parent command within the chat bar.
     */
    protected String[] parameters;
    /**
     * The parent <code>ExecutableCommand</code> of this sub-command. Initialised at construction within the parent's
     * corresponding <code>ExecutableCommandManager</code>.
     */
    protected final ExecutableCommand parentCommand;

    /**
     * Constructor for initialising the <code>parentCommand</code> that called for the construction of the class.
     * @param parentCommand the parent <code>ExecutableCommand</code> of the sub-command.
     */
    public ExecutableSubCommand(ExecutableCommand parentCommand) {
        this.parentCommand = parentCommand;
    }

    /**
     * Get the Tabs to show to a <code>Player</code> instance, based on the parameters of the Sub-Command.
     * @return A <code>String[]</code> to show to the player.
     */
    public abstract String[] getTabCompleteParameters();
}
