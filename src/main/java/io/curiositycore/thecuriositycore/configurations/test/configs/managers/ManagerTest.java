package io.curiositycore.thecuriositycore.configurations.test.configs.managers;

import io.curiositycore.thecuriositycore.configurations.CachedConfigManager;
import io.curiositycore.thecuriositycore.configurations.test.configs.caches.ActivityScanSettingsCacheTest;
import io.curiositycore.thecuriositycore.configurations.test.configs.caches.CustomWarCacheTest;
import io.curiositycore.thecuriositycore.configurations.test.configs.enums.ActivityScanSettings;
import io.curiositycore.thecuriositycore.configurations.test.configs.enums.CustomWarValue;
import org.bukkit.configuration.file.YamlConfiguration;
import org.bukkit.plugin.java.JavaPlugin;

public class ManagerTest extends CachedConfigManager {

    public ManagerTest(JavaPlugin landlordPlugin) {
        super(landlordPlugin);
        addCacheToManager(new ActivityScanSettingsCacheTest(ActivityScanSettings.class,this.config));
        addCacheToManager(new CustomWarCacheTest(CustomWarValue.class, this.config));
    }
}
