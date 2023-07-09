package io.curiositycore.thecuriositycore.inventorymenus.buttons;

import net.kyori.adventure.text.Component;
import org.bukkit.Material;

import java.util.Collections;
import java.util.List;

public class AirButton extends BaseInventoryButton{
    @Override
    public Material initButtonMaterial() {
        return Material.AIR;
    }

    @Override
    public List<Component> initButtonLore() {
        return Collections.emptyList();
    }

    @Override
    public Component initButtonName() {
        return Component.text("");
    }
}
