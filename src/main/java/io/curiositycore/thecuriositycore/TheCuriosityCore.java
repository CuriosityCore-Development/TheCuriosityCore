package io.curiositycore.thecuriositycore;

//import io.curiositycore.thecuriositycore.configurations.CachedConfigManager;

import lombok.Getter;
import org.bukkit.configuration.file.FileConfiguration;
import org.bukkit.plugin.PluginDescriptionFile;
import org.bukkit.plugin.java.JavaPlugin;
import org.bukkit.plugin.java.JavaPluginLoader;

import java.io.File;
import java.util.Objects;

public final class TheCuriosityCore extends JavaPlugin {
    @Getter
    private FileConfiguration fileConfiguration;
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
        this.fileConfiguration = this.getConfig();
    }

    @Override
    public void onDisable() {


    }

    //public CachedConfigManager getConfigManager(){
        //return new CachedConfigManager(this);
    //}
}
