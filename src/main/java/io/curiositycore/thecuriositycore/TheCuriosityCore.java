package io.curiositycore.thecuriositycore;

import io.curiositycore.thecuriositycore.testclasses.testCommand.PrimaryCommandAManager;
import org.bukkit.plugin.PluginDescriptionFile;
import org.bukkit.plugin.java.JavaPlugin;
import org.bukkit.plugin.java.JavaPluginLoader;


import java.io.File;
import java.util.Objects;
import java.util.logging.Logger;

public final class TheCuriosityCore extends JavaPlugin {

    private final boolean unitTest;


    public TheCuriosityCore() {
        super();
        unitTest = false;
    }

    protected TheCuriosityCore(
            JavaPluginLoader loader,
            PluginDescriptionFile description,
            File dataFolder,
            File file) {
        super(loader, description, dataFolder, file);
        unitTest = true;
    }
    @Override
    public void onEnable() {

        Objects.requireNonNull(this.getCommand("primary_command_a")).setExecutor(new PrimaryCommandAManager());
    }

    @Override
    public void onDisable() {


    }



}
