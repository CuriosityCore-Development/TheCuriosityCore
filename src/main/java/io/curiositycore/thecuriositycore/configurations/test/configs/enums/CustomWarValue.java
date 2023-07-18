package io.curiositycore.thecuriositycore.configurations.test.configs.enums;

import io.curiositycore.thecuriositycore.configurations.interfaces.ConfigValuesEnum;
import org.jetbrains.annotations.NotNull;

public enum CustomWarValue implements ConfigValuesEnum {
    AREA_CAPTURE_RADIUS(Integer.class,"area_capture_radius"),
    AREA_INFLUENCE_REQUIREMENT(Integer.class,"area_influence_requirement");

    String sectionAddress;
    String valueAddress;
    Class<?> classType;

    CustomWarValue(Class<?> classType, String valueAddress){
        this.sectionAddress = getSectionAddress();
        this.valueAddress = valueAddress;
        this.classType = classType;
    }
    @Override
    public @NotNull String getSectionAddress() {
        return "custom_war";
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
