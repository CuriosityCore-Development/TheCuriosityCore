package io.curiositycore.thecuriositycore.configurations.cached;

import io.curiositycore.thecuriositycore.configurations.interfaces.ConfigEnum;
import org.bukkit.configuration.ConfigurationSection;
import org.bukkit.configuration.file.YamlConfiguration;

import java.util.HashMap;
import java.util.Map;

class EnumToMapConverter<E extends Enum<E> & ConfigEnum> {
    public Map<E, Object> convertToMap(Class<E> enumClass, YamlConfiguration configFile) {
        Map<E, Object> map = new HashMap<>();
        //TODO look into making this a hashset
        E[] enumValues = enumClass.getEnumConstants();

        for (E enumValue : enumValues) {
            ConfigurationSection configSection = configFile.getConfigurationSection(enumValue.getSectionAddress());
            Object value = configSection.getObject(enumValue.getValueAddress(),enumValue.getClassType());
            map.put(enumValue, value);
        }

        return map;
    }
}
