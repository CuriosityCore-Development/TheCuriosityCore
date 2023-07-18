package io.curiositycore.thecuriositycore.configurations.test.configs.enums;

import io.curiositycore.thecuriositycore.configurations.interfaces.ConfigValuesEnum;
import org.jetbrains.annotations.NotNull;

public enum ActivityScanSettings implements ConfigValuesEnum {
    ENABLED(Boolean.class,"enabled" ),
    ACTIVITY_REQUIREMENT(Integer.class,"activity_requirement");

    private final Class<?> classType;
    private final String valueAddress;

    ActivityScanSettings(Class<?> classType, String valueAddress){
        this.valueAddress = valueAddress;
        this.classType = classType;
    }
    @Override
    public @NotNull String getSectionAddress() {
        return "activity_scan";
    }

    @Override
    public @NotNull String getValueAddress() {
        return this.valueAddress;
    }

    @Override
    public Class<?> getClassType() {
        return this.classType;
    }
}
