package io.curiositycore.thecuriositycore.testclasses.testCommand.subs;

import io.curiositycore.thecuriositycore.commands.CommandExecuteable;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

import java.util.List;

public class SalutationsCommand extends CommandExecuteable {
    @Override
    public void perform(CommandSender sender, String[] commandArguments) {
        if(sender instanceof Player){
            Player player = (Player) sender;
            player.sendMessage("Salutations, " + player.getName() + "!");
        }
    }

    @Override
    protected String initName() {
        return "salutations";
    }

    @Override
    protected String initDescription() {
        return "Says salutations";
    }

    @Override
    protected String initSyntax() {
        return null;
    }

    @Override
    protected List<String> getTabCompletesForCommand(String[] arguments) {
        return null;
    }
}
