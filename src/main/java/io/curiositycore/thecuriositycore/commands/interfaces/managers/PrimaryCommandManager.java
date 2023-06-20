package io.curiositycore.thecuriositycore.commands.interfaces.managers;

import io.curiositycore.thecuriositycore.commands.interfaces.executables.CommandExecutable;
import org.bukkit.command.Command;
import org.bukkit.command.CommandSender;
import org.bukkit.command.TabExecutor;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

import java.util.*;

/**
 * Abstract to the define the generalisation of any manager for a group of <code>CommandExecutable</code> instances.
 * This class should be treated as a typical <code>TabExecutor</code> (i.e. registered on enable as a primary command).
 */
public abstract class PrimaryCommandManager implements TabExecutor {

    /**
     * Map containing key-value pairs of command executable names and command executables respectively.
     */
    protected Map<String, CommandExecutable> commandMap = new HashMap<>();


    @Override
    public boolean onCommand(@NotNull CommandSender sender, @NotNull Command command, @NotNull String label, @NotNull String[] args) {
        if(!this.commandMap.containsKey(args[0])){
            return false;
        }

        CommandExecutable baseCommand = getCommand(args[0]);

        if(baseCommand == null){
            return false;
        }

        if(args.length == 1){
            baseCommand.perform(sender, args);
            return false;
        }

        if(baseCommand.getSubCommands().size() > 1 && baseCommand.getSubCommands().get(args[1]) != null){
            return potentialSubCommandExecution(sender,command,label,args,baseCommand);
        }


        return true;
    }



    @Override
    public List<String> onTabComplete(@NotNull CommandSender sender, @NotNull Command command, @NotNull String label, @NotNull String[] args) {
        List<String> tabsToReturn = new ArrayList<>();

        if(args.length == 1){
            return this.commandMap.keySet().stream().toList();
        }

        if(!this.commandMap.containsKey(args[0])){
            return Collections.emptyList();
        }


            tabsToReturn.addAll(this.commandMap.get(args[0]).getTabCompletesForCommand(args));
            return tabsToReturn;


    }
    protected CommandExecutable getCommand(String commandName){
        return this.commandMap.get(commandName);
    }
    //TODO Just because bukkit does it, doesnt mean its right, make a class to store the many parameters of command.
    /**
     * Attempts to execute the potential sub-command-executable that has been called.
     * @param sender Sender of the command that the sub-command is a part of.
     * @param command The command the <code>ExecutableCommand</code> is a part of.
     * @param label The label of the command the sub-command is a part of.
     * @param args The arguments of the command the <code>ExecutableCommand</code> is a part of.
     * @param commandExecutable the executable that has called the sub-command-executable
     * @return The boolean that confirms if the sub-command-executable executed successfully.
     */
    protected boolean potentialSubCommandExecution(@NotNull CommandSender sender, @NotNull Command command, @NotNull String label, @NotNull String[] args, CommandExecutable commandExecutable){
        CommandExecutable subCommandExecutable = commandExecutable.getSubCommands().get(args[1]);
        String[] subArgs = Arrays.copyOfRange(args, 1, args.length);
        if(subArgs.length < 1){
            sender.sendMessage(subCommandExecutable.getSyntax());
            return false;
        }
        subCommandExecutable.perform(sender, subArgs);

        return true;

    }

    /**
     * An abstract method that defines any tests that require conducting before execution of the
     * <code>CommandExecutable</code>
     * @return The boolean representing the success or failure of the checks.
     */
    protected abstract boolean preCommandTests();
}
