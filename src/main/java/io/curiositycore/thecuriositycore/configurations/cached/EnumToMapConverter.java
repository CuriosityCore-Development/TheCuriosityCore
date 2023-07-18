package io.curiositycore.thecuriositycore.configurations.cached;

import io.curiositycore.thecuriositycore.configurations.interfaces.ConfigValuesEnum;
import org.bukkit.Bukkit;
import org.bukkit.configuration.ConfigurationSection;
import org.bukkit.configuration.file.YamlConfiguration;

import java.util.HashMap;
import java.util.Map;

/**
 * This class is responsible for converting all values within an enum implementing the {@link
 * io.curiositycore.thecuriositycore.configurations.interfaces.ConfigValuesEnum ConfigValuesEnum} interface into a map
 * to be initialised in a {@link io.curiositycore.thecuriositycore.configurations.cached.CachedConfig CachedConfig}
 * instance.
 *
 * @param <E> The type parameter of an Enum that implements a {@link
 * io.curiositycore.thecuriositycore.configurations.interfaces.ConfigValuesEnum ConfigValuesEnum}.
 */
class EnumToMapConverter<E extends Enum<E> & ConfigValuesEnum> {
    /**
     * Converts the values of a config values Enum into a map of Objects that are defined by their enum value. This is
     * the primary method of universally converting any value into a requested class within an instance of the {@link
     * io.curiositycore.thecuriositycore.configurations.CachedConfigManager CachedConfigManager}.
     * @param enumClass The class of the Enum that is to be utilised for this map of values.
     * @param configFile The configuration file of the plugin. This will be determined within the CachedConfigManager.
     * @return A map of all the values defined within the ConfigValuesEnum.
     */
    public Map<E, Object> convertToMap(Class<E> enumClass, YamlConfiguration configFile) {
        Map<E, Object> map = new HashMap<>();

        E[] enumValues = enumClass.getEnumConstants();

        for (E enumValue : enumValues) {
            ConfigurationSection configSection = configFile.getConfigurationSection(enumValue.getSectionAddress());
            try{
                assert configSection != null;
                Object value = configSection.getObject(enumValue.getValueAddress(),enumValue.getClassType());
                map.put(enumValue, value);
            }
            catch(NullPointerException nullPointerException){
                String pathAdddress = enumValue.getSectionAddress()+enumValue.getSectionAddress();
                //TODO when all branches merged, integrate messages package.
                Bukkit.getLogger().info("The address '" + pathAdddress+"' resulted in a null value.");
            }
        }

        return map;
    }
}
