package io.curiositycore.thecuriositycore.testclasses.testCommand;

import io.curiositycore.thecuriositycore.commands.CommandManager;
import io.curiositycore.thecuriositycore.testclasses.testCommand.subs.GreetCommand;
import io.curiositycore.thecuriositycore.testclasses.testCommand.subs.SalutationsCommand;

public class GreetingsCommand extends CommandManager {
    public GreetingsCommand(){
        this.commandMap.put("greet",new GreetCommand());

    }
}
