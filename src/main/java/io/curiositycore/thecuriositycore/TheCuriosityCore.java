package io.curiositycore.thecuriositycore;


import io.curiositycore.thecuriositycore.configurations.CachedConfigManager;
import io.curiositycore.thecuriositycore.configurations.test.configs.managers.ManagerTest;
import org.bukkit.plugin.PluginDescriptionFile;
import org.bukkit.plugin.java.JavaPlugin;


public final class TheCuriosityCore extends JavaPlugin {
    @Getter
    private FileConfiguration fileConfiguration;

    @Override
    public void onEnable() {
        this.fileConfiguration = this.getConfig();
    }

    @Override
    public void onDisable(){ 
    }

}
