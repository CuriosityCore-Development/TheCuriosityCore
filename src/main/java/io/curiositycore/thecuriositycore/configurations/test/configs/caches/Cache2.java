package io.curiositycore.thecuriositycore.configurations.test.configs.caches;

import io.curiositycore.thecuriositycore.configurations.cached.CachedConfigFile;
import io.curiositycore.thecuriositycore.configurations.test.configs.enums.ToShowDelta;
import org.bukkit.configuration.file.YamlConfiguration;

public class Cache2 extends CachedConfigFile<ToShowDelta> {

    public Cache2(Class<ToShowDelta> enumClass, YamlConfiguration configFile) {
        super(enumClass, configFile);
    }
}
