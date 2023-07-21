package io.curiositycore.thecuriositycore.configurations.test.configs.caches;

import io.curiositycore.thecuriositycore.configurations.cached.CachedConfig;
import io.curiositycore.thecuriositycore.configurations.test.configs.enums.ActivityScanSettings;
import org.bukkit.configuration.file.FileConfiguration;


public class ActivityScanSettingsCacheTest extends CachedConfig<ActivityScanSettings> {

    public ActivityScanSettingsCacheTest(Class<ActivityScanSettings> enumClass, FileConfiguration configFile) {
        super(enumClass, configFile);
    }

    @Override
    protected String initialiseName() {
        return "ActivityScanCache";
    }
}
