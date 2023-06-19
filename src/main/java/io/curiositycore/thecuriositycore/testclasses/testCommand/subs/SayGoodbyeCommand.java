package io.curiositycore.thecuriositycore.testclasses.testCommand.subs;

import io.curiositycore.thecuriositycore.commands.CommandExecuteable;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

import java.util.List;

public class SayGoodbyeCommand extends CommandExecuteable {
    @Override
    public void perform(CommandSender sender, String[] commandArguments) {
        if (sender instanceof Player) {
            Player player = (Player) sender;
            player.sendMessage("Goodbye, " + player.getName() + "!");
        }
    }

    @Override
    protected String initName() {
        return "goodbye";
    }

    @Override
    protected String initDescription() {
        return "Say goodbye to the player";
    }

    @Override
    protected String initSyntax() {
        return "/greet goodbye";
    }

    @Override
    protected List<String> getTabCompletesForCommand(String[] arguments) {
        // Return a list of suggested completions here...
        return null;
    }
}
