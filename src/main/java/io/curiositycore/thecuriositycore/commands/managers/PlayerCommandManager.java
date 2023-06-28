package io.curiositycore.thecuriositycore.commands.managers;

import io.curiositycore.thecuriositycore.commands.managers.interfaces.RequiresPlayer;

/**
 * Child-class of {@link CommandManager} that only accepts <code>Player</code> instances as valid
 * <code>CommandSender</code>s.
 */
public abstract class PlayerCommandManager extends CommandManager implements RequiresPlayer {

    @Override
    protected boolean preCommandTests() {
        return isMessengerPlayer(this.commandInputs.getSender());
    }
}
