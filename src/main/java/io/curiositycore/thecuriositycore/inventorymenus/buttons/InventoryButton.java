package io.curiositycore.thecuriositycore.inventorymenus.buttons;

import net.kyori.adventure.text.Component;
import org.bukkit.Material;

import java.util.List;

public interface InventoryButton {
    Material initButtonMaterial();
    List<Component> initButtonLore();
    Component initButtonName();
    void initialize();

}
