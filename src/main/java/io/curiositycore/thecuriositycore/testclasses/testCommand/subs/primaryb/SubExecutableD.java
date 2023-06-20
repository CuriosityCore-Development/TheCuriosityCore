package io.curiositycore.thecuriositycore.testclasses.testCommand.subs.primaryb;

import io.curiositycore.thecuriositycore.commands.interfaces.executables.CommandExecutable;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

import java.util.Collections;
import java.util.List;

public class SubExecutableD extends CommandExecutable{
    @Override
    public void perform(CommandSender sender, String[] commandArguments) {
        if (sender instanceof Player) {
            Player player = (Player) sender;
            player.sendMessage("Sub-Executable-B executed via command by " + player.getName() + "!");
        }
    }

    @Override
    protected String initName() {
        return "sub_executable_d";
    }

    @Override
    protected String initDescription() {
        return "Is the 2nd sub-executeable";
    }

    @Override
    protected String initSyntax() {
        return "/PrimaryCommand PrimaryExecutableA SubExecutableB args";
    }



    @Override
    protected List<String> determineTabCompletes(String[] arguments) {
        return Collections.emptyList();
    }
}
