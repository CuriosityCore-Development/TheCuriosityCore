package io.curiositycore.thecuriositycore.testclasses.testCommand;

import io.curiositycore.thecuriositycore.commands.managers.CommandManager;
import io.curiositycore.thecuriositycore.commands.managers.PlayerCommandManager;
import io.curiositycore.thecuriositycore.testclasses.testCommand.subs.primarya.PrimaryExecutableA;
import io.curiositycore.thecuriositycore.testclasses.testCommand.subs.primaryb.PrimaryExecutableB;


/**
 * Responsible for managing all the primary commands for the plugin. This
 */
public class PrimaryCommandAManager extends PlayerCommandManager {
    public PrimaryCommandAManager(){

        this.commandMap.put("primary_executable_a",new PrimaryExecutableA());
        this.commandMap.put("primary_executable_b",new PrimaryExecutableB());
    }


}
