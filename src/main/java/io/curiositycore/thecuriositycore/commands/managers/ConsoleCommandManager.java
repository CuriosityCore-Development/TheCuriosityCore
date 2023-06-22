package io.curiositycore.thecuriositycore.commands.managers;

import io.curiositycore.thecuriositycore.commands.managers.interfaces.RequiresConsole;
/**
 * Child-class of {@link CommandManager} that only accepts <code>ConsoleCommandSender</code> instances as valid
 * <code>CommandSender</code>s.
 */
public abstract class ConsoleCommandManager extends CommandManager implements RequiresConsole {

    @Override
    protected boolean preCommandTests() {
        return isMessengerConsole(this.commandInputs.getSender());
    }
}
