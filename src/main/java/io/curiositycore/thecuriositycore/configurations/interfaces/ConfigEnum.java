package io.curiositycore.thecuriositycore.configurations.interfaces;

import org.bukkit.configuration.ConfigurationSection;
import org.bukkit.configuration.file.YamlConfiguration;
import org.jetbrains.annotations.NotNull;

/**
 * The different components of a Configuration File address.
 */
public interface ConfigEnum {
    /**
     * Gets the address of the section the value is in.
     * @return The address of the value's section as a <code>String</code>.
     */
    @NotNull
    String getSectionAddress();
    /**
     * Gets the sub-address of the value within the specified section.
     * @return The sub-address of the value as a <code>String</code>.
     */
    @NotNull
    String getValueAddress();

    Class<?> getClassType();


    /**
     * Get a <code>String[]</code> of the 2 address components needed to find the value within the config file.
     * @return The 2 address components as a <code>String[]</code>
     */
    default String[] getPathArray(){
        return new String[]{getSectionAddress(),getValueAddress()};
    }

}
