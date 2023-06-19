package io.curiositycore.thecuriositycore.testclasses.testCommand.subs;

import io.curiositycore.thecuriositycore.commands.interfaces.executables.CommandExecutable;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

import java.util.List;

public class SubExecutableB extends CommandExecutable {
    @Override
    public void perform(CommandSender sender, String[] commandArguments) {
        if (sender instanceof Player) {
            Player player = (Player) sender;
            player.sendMessage("Sub-Executable-B executed via command by " + player.getName() + "!");
        }
    }

    @Override
    protected String initName() {
        return "sub_executable_b";
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
    public List<String> getTabCompletesForCommand(String[] arguments) {
        // Return a list of suggested completions here...
        return null;
    }
}
