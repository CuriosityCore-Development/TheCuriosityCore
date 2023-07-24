package io.curiositycore.thecuriositycore.configurations.test.configs.caches;

import io.curiositycore.thecuriositycore.configurations.cached.CachedConfig;
import io.curiositycore.thecuriositycore.configurations.test.configs.enums.CustomWarValue;
import org.bukkit.configuration.file.FileConfiguration;


public class CustomWarCacheTest extends CachedConfig<CustomWarValue> {

    public CustomWarCacheTest(Class<CustomWarValue> enumClass,  FileConfiguration configFile) {
        super(enumClass, configFile);
    }

    @Override
    protected String initialiseName() {
        return "CustomWarCache";
    }
}
