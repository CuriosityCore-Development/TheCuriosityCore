package io.curiositycore.thecuriositycore;

import net.kyori.adventure.platform.bukkit.BukkitAudiences;
import org.bukkit.plugin.java.JavaPlugin;
import org.checkerframework.checker.nullness.qual.NonNull;

public final class TheCuriosityCore extends JavaPlugin {
    private BukkitAudiences adventure;
    @Override
    public void onEnable() {
        this.adventure = adventure();

    }

    @Override
    public void onDisable() {

        if(this.adventure != null) {
            this.adventure.close();
            this.adventure = null;
        }
    }
    private @NonNull BukkitAudiences adventure() {
        if(this.adventure == null) {
            throw new IllegalStateException("Tried to access Adventure when the plugin was disabled!");
        }
        return this.adventure;
    }


}
