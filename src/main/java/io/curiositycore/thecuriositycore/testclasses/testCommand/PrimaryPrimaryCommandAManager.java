package io.curiositycore.thecuriositycore.testclasses.testCommand;

import io.curiositycore.thecuriositycore.commands.interfaces.managers.PrimaryCommandManager;
import io.curiositycore.thecuriositycore.testclasses.testCommand.subs.primarya.PrimaryExecutableA;
import io.curiositycore.thecuriositycore.testclasses.testCommand.subs.primaryb.PrimaryExecutableB;


/**
 * Responsible for managing all the primary commands for the plugin. This
 */
public class PrimaryPrimaryCommandAManager extends PrimaryCommandManager {
    public PrimaryPrimaryCommandAManager(){

        this.commandMap.put("primary_executable_a",new PrimaryExecutableA());
        this.commandMap.put("primary_executable_b",new PrimaryExecutableB());
    }

    @Override
    protected boolean preCommandTests() {
        return true ;
    }
}
