package io.curiositycore.thecuriositycore.configurations.interfaces;

import org.jetbrains.annotations.NotNull;

/**
 * The different components of a Configuration File address.
 */
public interface ConfigValuesEnum {
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

    /**
     * Gets the class type of the cached config value, allowing for generic retrieval of any value type stored in the config.
     * @return The class type of the cached config value.
     */
    Class<?> getClassType();

}
