package io.curiositycore.thecuriositycore.testclasses.testCommand.subs;

import io.curiositycore.thecuriositycore.commands.interfaces.executables.CommandExecutable;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

import java.util.List;

public class PrimaryExecutableA extends CommandExecutable {
    public PrimaryExecutableA() {
        addSubCommand(new SubExecutableB());
        addSubCommand(new SubExecutableA());
    }

    @Override
    public void perform(CommandSender sender, String[] commandArguments) {

        Player player = (Player) sender;
        player.sendMessage(this.getSyntax());
    }


    @Override
    protected String initName() {
        return "primary_executable_a";
    }

    @Override
    protected String initDescription() {
        return "Is primary executeable A";
    }

    @Override
    protected String initSyntax() {
        return "/greet";
    }

    @Override
    public List<String> getTabCompletesForCommand(String[] arguments) {
        if(arguments.length == 2){
            return this.subCommands.keySet().stream().toList();
        }
        return null;
    }
}
