package io.curiositycore.thecuriositycore.testclasses.testCommand;

import io.curiositycore.thecuriositycore.commands.interfaces.managers.PrimaryCommandManager;
import io.curiositycore.thecuriositycore.testclasses.testCommand.subs.PrimaryExecutableA;

/**
 * Responsible for managing all the primary commands for the plugin. This
 */
public class PrimaryPrimaryCommandAManager extends PrimaryCommandManager {
    public PrimaryPrimaryCommandAManager(){
        this.commandMap.put("primary_executable_a",new PrimaryExecutableA());
    }

    @Override
    protected boolean preCommandTests() {
        return true ;
    }
}
