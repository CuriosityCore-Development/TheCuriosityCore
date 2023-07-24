package io.curiositycore.thecuriositycore.configurations.cached;

import io.curiositycore.thecuriositycore.configurations.interfaces.ConfigValuesEnum;
import lombok.Getter;
import org.bukkit.configuration.file.FileConfiguration;
import org.bukkit.configuration.file.YamlConfiguration;

import java.util.HashMap;
import java.util.Map;

/**
 * Represents a collection of cached values taken from the configuration files of a plugin. The values are decided based
 * on <code>Enums</code> that implement the
 * {@linkplain ConfigValuesEnum ConfigValuesEnum} interface. <br>
 * <br>
 * The <code>Enum</code> relating to the cache is stated as a parameter within the child-classes of this abstract, which
 * ensures the correct
 * {@linkplain io.curiositycore.thecuriositycore.configurations.cached.EnumToMapConverter EnumToMapConverter}.
 * @param <T> An Enum implementing the ConfigValuesEnum interface.
 */
@Getter
public abstract class CachedConfig<T extends Enum<T> & ConfigValuesEnum> {
    /**
     * The name designating the values that can be found within this configuration cache.
     */
    protected String configName;
    /**
     * The Map of cached values from the Config. Stored values are Object instances to ensure a singular collection for
     * the cache.<br><br> <i>(These Objects are automatically casted to their respective type via calling the desired type within
     * the {@linkplain io.curiositycore.thecuriositycore.configurations.CachedConfigManager CachedConfigManager}) </i>
     */
    protected Map<Enum<?>,Object> valueMap = new HashMap<>();

    /**
     * Constructor that initializes both the EnumToMapConverter and puts all applicable <code>Enum</code> values into the
     * cache Map.
     * @param enumClass The class of ConfigValuesEnum the cached values are derived from.
     * @param configFile The configuration file to cache values from.
     */
    protected CachedConfig(Class<T> enumClass, FileConfiguration configFile){
        EnumToMapConverter<T> converter = new EnumToMapConverter<>();
        this.valueMap.putAll(converter.convertToMap(enumClass,(YamlConfiguration) configFile));
        this.configName = initialiseName();
    }

    /**
     * Gets an object from the cache map (to be cast correctly within a subclass of the CachedConfigManager.
     * @param key The Enum value to get the cached value for.
     * @return The cached value as an Object.
     */
    public Object getValueAsObject(Enum<?> key){
        return this.valueMap.get(key);

    }

    /**
     * Initialises the name of the configuration cache.
     * @return The name of the configuration cache.
     */
    protected abstract String initialiseName();


}
