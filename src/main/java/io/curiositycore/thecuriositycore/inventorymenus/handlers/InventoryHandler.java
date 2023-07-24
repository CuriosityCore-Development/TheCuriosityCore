package io.curiositycore.thecuriositycore.inventorymenus.handlers;

import org.bukkit.entity.HumanEntity;
import org.jetbrains.annotations.NotNull;

/**
 * Handles an inventory within a CuriosityCore plugin.
 */
public interface InventoryHandler {

    /**
     * Handles inventory onClick events. Stating who the clicker was and what slot was clicked.
     * @param slot The slot number clicked by the player.
     * @param whoClicked Who clicked the slot.
     */
    void onClick(int slot, @NotNull HumanEntity whoClicked);

    /**
     * Handles inventory open events.
     */
    void onOpen();

    /**
     * Handles inventory close events.
     */
    void onClose();
}
