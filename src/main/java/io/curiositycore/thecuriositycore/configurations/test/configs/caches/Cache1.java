package io.curiositycore.thecuriositycore.configurations.test.configs.caches;

import io.curiositycore.thecuriositycore.configurations.cached.CachedConfigFile;
import io.curiositycore.thecuriositycore.configurations.test.configs.enums.ActivityScanSettings;
import org.bukkit.configuration.file.YamlConfiguration;

public class Cache1 extends CachedConfigFile<ActivityScanSettings> {

    public Cache1(Class<ActivityScanSettings> enumClass, YamlConfiguration configFile) {
        super(enumClass, configFile);
    }
}
