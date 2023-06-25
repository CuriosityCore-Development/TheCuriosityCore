package io.curiositycore.thecuriositycore.configurations.cached;

import io.curiositycore.thecuriositycore.configurations.interfaces.ConfigEnum;
import lombok.Getter;
import org.bukkit.configuration.file.YamlConfiguration;

import java.util.HashMap;
import java.util.Map;

@Getter
public abstract class CachedConfigFile<T extends Enum<T> & ConfigEnum> {
    protected String configName;
    Map<Enum<?>,Object> valueMap = new HashMap<>();

    protected CachedConfigFile(Class<T> enumClass, YamlConfiguration configFile){
        EnumToMapConverter<T> converter = new EnumToMapConverter<>();
        this.valueMap.putAll(converter.convertToMap(enumClass,configFile));
    }
    public Object getValueAsObject(Enum<?> key){
        return this.valueMap.get(key);

    }

}
