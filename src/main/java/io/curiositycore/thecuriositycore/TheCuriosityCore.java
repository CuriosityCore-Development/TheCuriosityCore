package io.curiositycore.thecuriositycore;

import io.curiositycore.thecuriositycore.testclasses.testCommand.GreetingsCommand;
import net.kyori.adventure.platform.bukkit.BukkitAudiences;
import org.bukkit.plugin.PluginDescriptionFile;
import org.bukkit.plugin.java.JavaPlugin;
import org.bukkit.plugin.java.JavaPluginLoader;
import org.checkerframework.checker.nullness.qual.NonNull;

import java.io.File;
import java.util.logging.Logger;

public final class TheCuriosityCore extends JavaPlugin {
    private BukkitAudiences adventure;
    private final boolean unitTest;


    private Logger pluginLogger;
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
        //this.adventure = adventure();
        this.getCommand("messages").setExecutor(new GreetingsCommand());
    }

    @Override
    public void onDisable() {

        if(this.adventure != null) {
            this.adventure.close();
            this.adventure = null;
        }
    }
    private @NonNull BukkitAudiences adventure() {
        if(this.adventure == null) {
            throw new IllegalStateException("Tried to access Adventure when the plugin was disabled!");
        }
        return this.adventure;
    }


}
