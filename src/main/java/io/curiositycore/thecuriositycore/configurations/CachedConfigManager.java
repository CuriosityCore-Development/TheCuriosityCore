package io.curiositycore.thecuriositycore.configurations;

import io.curiositycore.thecuriositycore.configurations.cached.CachedConfigFile;
import io.curiositycore.thecuriositycore.configurations.interfaces.ConfigCache;
import io.curiositycore.thecuriositycore.configurations.interfaces.ConfigEnum;
import io.curiositycore.thecuriositycore.configurations.test.configs.caches.Cache1;
import io.curiositycore.thecuriositycore.configurations.test.configs.caches.Cache2;
import io.curiositycore.thecuriositycore.configurations.test.configs.enums.ActivityScanSettings;
import io.curiositycore.thecuriositycore.configurations.test.configs.enums.ToShowDelta;
import org.bukkit.configuration.file.FileConfiguration;
import org.bukkit.configuration.file.YamlConfiguration;
import org.bukkit.plugin.java.JavaPlugin;

import java.util.HashMap;
import java.util.Map;

/**
 * Manager for all the config files within a plugin, can create cached config values for quicker load times<br>
 * <i>(Controlled tests had over a 1000% increase in retrieval speed)</i><br>
 *
 */
public abstract class CachedConfigManager {
    protected Map<String, CachedConfigFile<?>> configFileMap = new HashMap<>();
    protected FileConfiguration config;
    protected CachedConfigManager(JavaPlugin landlordPlugin){
        this.config = landlordPlugin.getConfig();

        //this.configFileMap.put("TestCache",new Cache1(ActivityScanSettings.class, (YamlConfiguration) this.config));
        //this.configFileMap.put("TestCache2", new Cache2(ToShowDelta.class, (YamlConfiguration) this.config));
    }

    public <C> C getObject(String configName, Class<C> clazz, Enum<?> key) {
        Object object;
        object = this.configFileMap.get(configName).getValueAsObject(key);
        if (clazz.isInstance(object)) {
            return clazz.cast(object);
        }
        return null;
    }

}
