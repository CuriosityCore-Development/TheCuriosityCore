package io.curiositycore.thecuriositycore.commands;

import org.bukkit.command.Command;
import org.bukkit.command.CommandSender;
import org.bukkit.command.TabExecutor;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
//TODO finish class
/**
 * Abstract to the define the generalisation of any manager for a group of <code>ExecutableCommand</code>s.
 */
public abstract class CommandManager implements TabExecutor {
    //TODO Do the javadocs for this
    /**
     *
     */
    protected Map<String, CommandExecuteable> commandMap = new HashMap<>();


    @Override
    public boolean onCommand(@NotNull CommandSender sender, @NotNull Command command, @NotNull String label, @NotNull String[] args) {
        if(!this.commandMap.containsKey(args[0])){
            return false;
        }

        CommandExecuteable baseCommand = getCommand(args[0]);

        // If there is another argument, try to get it as a sub-command.
        if (args.length > 1) {
            CommandExecuteable subCommand = baseCommand.getSubCommand(args[1]);

            if (subCommand != null) {
                // Here we are cutting off the first two arguments (base command and sub-command)
                String[] subArgs = Arrays.copyOfRange(args, 2, args.length);

                // Execute the sub-command
                subCommand.perform(sender, subArgs);
            } else {
                // If there is no such sub-command, perform the base command normally
                baseCommand.perform(sender, args);
            }
        } else {
            // If there are no extra arguments, perform the base command
            baseCommand.perform(sender, args);
        }

        return true;
    }

    @Nullable
    @Override
    public List<String> onTabComplete(@NotNull CommandSender sender, @NotNull Command command, @NotNull String label, @NotNull String[] args) {
        if(args.length == 1){
            return this.commandMap.keySet().stream().toList();
        }

        if(!this.commandMap.containsKey(args[0])){
            return null;
        }

        return this.commandMap.get(args[0]).getTabCompletesForCommand(args);
    }
    protected CommandExecuteable getCommand(String commandName){
        return this.commandMap.get(commandName);
    }
}
