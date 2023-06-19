package io.curiositycore.thecuriositycore.testclasses.testCommand.subs;

import io.curiositycore.thecuriositycore.commands.CommandExecuteable;
import io.curiositycore.thecuriositycore.testclasses.testCommand.subs.SayGoodbyeCommand;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

import java.util.List;

public class GreetCommand extends CommandExecuteable {
    public GreetCommand() {
        addSubCommand(new SayGoodbyeCommand());
        addSubCommand(new SalutationsCommand());
    }

    @Override
    public void perform(CommandSender sender, String[] commandArguments) {
        if (sender instanceof Player) {
            Player player = (Player) sender;
            player.sendMessage("Hello, " + player.getName() + "!");
        }
    }

    @Override
    protected String initName() {
        return "greet";
    }

    @Override
    protected String initDescription() {
        return "Greet the player";
    }

    @Override
    protected String initSyntax() {
        return "/greet";
    }

    @Override
    protected List<String> getTabCompletesForCommand(String[] arguments) {
        // Return a list of suggested completions here...
        return null;
    }
}
