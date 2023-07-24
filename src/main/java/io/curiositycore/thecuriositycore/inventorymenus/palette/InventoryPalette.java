package io.curiositycore.thecuriositycore.inventorymenus.palette;

import io.curiositycore.thecuriositycore.inventorymenus.buttons.BaseInventoryButton;

/**
 * Interface representing the functionality of any palette of buttons for a custom inventory.
 */
public interface InventoryPalette {
    /**
     * Initializes the palette on construction.
     * @return The pallet of buttons for this custom inventory.
     */
    BaseInventoryButton[] initializePalette();


}
