package io.curiositycore.thecuriositycore.configurations.test.configs.managers;

import io.curiositycore.thecuriositycore.configurations.CachedConfigManager;
import io.curiositycore.thecuriositycore.configurations.cached.CachedConfigFile;
import org.bukkit.configuration.file.YamlConfiguration;
import org.bukkit.plugin.java.JavaPlugin;

public class ManagerTest extends CachedConfigManager {

    public ManagerTest(JavaPlugin landlordPlugin) {
        super(landlordPlugin);
    }
}
