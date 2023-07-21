package io.curiositycore.thecuriositycore;

//import io.curiositycore.thecuriositycore.configurations.CachedConfigManager;

import lombok.Getter;
import org.bukkit.configuration.file.FileConfiguration;
import org.bukkit.plugin.java.JavaPlugin;


public final class TheCuriosityCore extends JavaPlugin {
    @Getter
    private FileConfiguration fileConfiguration;

    @Override
    public void onEnable() {
        this.fileConfiguration = this.getConfig();
    }

    @Override
    public void onDisable() {


    }

}
