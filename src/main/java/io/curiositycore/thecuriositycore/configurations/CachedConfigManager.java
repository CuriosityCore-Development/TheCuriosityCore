package io.curiositycore.thecuriositycore.configurations;

import io.curiositycore.thecuriositycore.configurations.cached.CachedConfig;
import io.curiositycore.thecuriositycore.configurations.interfaces.ConfigValuesEnum;
import org.bukkit.configuration.file.FileConfiguration;
import org.bukkit.plugin.java.JavaPlugin;

import java.util.HashMap;
import java.util.Map;

/**
 * Manager for all the config files within a plugin, can create cached config values for quicker load times<br>
 * <i>(Controlled tests had over a 1000% increase in retrieval speed)</i><br>
 *
 */
public abstract class CachedConfigManager {
    /**
     * Map of all the {@linkplain CachedConfig CachedConfigFile CachedConfigFile} instances active within the plugin
     * the Library is linked to. The <code>key</code>s are the class types of the Cached config <code>values</code>
     * within the map.
     */
    protected Map<Class<CachedConfig<?>>, CachedConfig<?>> configFileMap = new HashMap<>();
    /**
     * The configuration file utilised by this manager.
     */
    protected FileConfiguration config;

    /**
     * Constructor that initialises the configuration file utilised by this manager.
     * @param pluginInstance An instance of the plugin to retrieve the configuration file from, for caching.
     */
    protected CachedConfigManager(JavaPlugin pluginInstance){
        this.config = pluginInstance.getConfig();
    }

    /**
     * Generic method utilised to get values from the defined CachedConfigFile, allowing dynamic definition of the type
     * to retrieve (removing the need for multiple methods per class.
     * @param cacheClazz The class of the config cache to query.
     * @param clazz The class of the type to return from this method call. Allows the retrieval of multiple value types
     *              from a singular method.
     * @param key The Enum value utilised to determine the Object to retrieve.
     * @return The value of the cached config value.
     * @param <C> The class of the desired value tpye to retrieve from the method.
     */
    public <C,T extends CachedConfig<?>> C getObject(Class<T> cacheClazz, Class<C> clazz, Enum<?> key) {
        Object object;
        try{
            object = this.configFileMap.get(cacheClazz).getValueAsObject(key);
            if (clazz.isInstance(object)) {
                return clazz.cast(object);
            }
        }
        catch(NullPointerException nullPointerException){
            nullPointerException.printStackTrace();
        }
        return null;
    }

    /**
     * Adds another cached configuration file to the manager.
     * @param cachedConfig The cached config file to add.
     * @param <T> The type parameter for the cached config file to add.
     */
    protected <T extends Enum<T> & ConfigValuesEnum> void addCacheToManager(CachedConfig<T> cachedConfig){
        Class<CachedConfig<?>> cacheClass = (Class<CachedConfig<?>>) cachedConfig.getClass();
        this.configFileMap.put(cacheClass, cachedConfig);
    }

}
