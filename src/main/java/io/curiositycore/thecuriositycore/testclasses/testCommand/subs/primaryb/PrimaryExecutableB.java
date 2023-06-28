package io.curiositycore.thecuriositycore.testclasses.testCommand.subs.primaryb;

import io.curiositycore.thecuriositycore.commands.executables.CommandExecutable;
import java.util.Collections;

import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

import java.util.List;

public class PrimaryExecutableB extends CommandExecutable {
    public PrimaryExecutableB() {
        addSubCommand(new SubExecutableC());
        addSubCommand(new SubExecutableD());
    }
    @Override
    public void perform(CommandSender sender, String[] commandArguments) {

        Player player = (Player) sender;
        player.sendMessage(this.getSyntax());
    }
    @Override
    protected String initName() {
        return "primary_executable_b";
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
    protected List<String> determineTabCompletes(String[] arguments) {
        return Collections.emptyList();
    }
}
