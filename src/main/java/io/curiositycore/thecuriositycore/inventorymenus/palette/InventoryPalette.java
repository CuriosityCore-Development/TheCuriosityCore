package io.curiositycore.thecuriositycore.inventorymenus.palette;

import io.curiositycore.thecuriositycore.inventorymenus.buttons.BaseInventoryButton;

import java.util.Map;

/**
 * Interface representing the functionality of any palette of buttons for a custom inventory.
 */
public interface InventoryPalette {
    /**
     * Gets the Map representation of the Inventory Palette. A Map of slot number Keys and Button Values.
     * @return The pallet of buttons for this custom inventory.
     */
    Map<Integer, BaseInventoryButton> getMenuLayoutMap();


}
