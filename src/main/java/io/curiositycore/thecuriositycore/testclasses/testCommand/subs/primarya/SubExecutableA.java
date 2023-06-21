package io.curiositycore.thecuriositycore.testclasses.testCommand.subs.primarya;

import io.curiositycore.thecuriositycore.commands.executables.CommandExecutable;

import java.util.Collections;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

import java.util.List;

public class SubExecutableA extends CommandExecutable{
    @Override
    public void perform(CommandSender sender, String[] commandArguments) {
        if(sender instanceof Player){
            Player player = (Player) sender;
            player.sendMessage("Sub-Executable-A executed via command by " + player.getName() + "!");

        }
    }

    @Override
    protected String initName() {
        return "sub_executable_a";
    }

    @Override
    protected String initDescription() {
        return "Is the first sub executeable";
    }

    @Override
    protected String initSyntax() {
        return "/PrimaryCommand PrimaryExecutableA SubExecutableA args";
    }

    @Override
    protected List<String> determineTabCompletes(String[] arguments) {
        return Collections.emptyList();
    }
}
