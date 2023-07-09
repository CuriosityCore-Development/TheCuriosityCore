package io.curiositycore.thecuriositycore.inventorymenus.managers;

import io.curiositycore.thecuriositycore.inventorymenus.handlers.InventoryHandler;
import org.bukkit.event.Event;
import org.bukkit.event.inventory.InventoryClickEvent;
import org.bukkit.event.inventory.InventoryCloseEvent;
import org.bukkit.event.inventory.InventoryOpenEvent;
import org.bukkit.inventory.Inventory;

/**
 * Interface representing the functionality of any manager responsible for custom inventories.
 */
public interface CustomInventoryManager {
    /**
     * Registers a new inventory to the manager.
     * @param inventory The inventory to register.
     * @param inventoryHandler The inventory handler instance to directly relate to the inventory key.
     */
    void registerHandledInventory(Inventory inventory, InventoryHandler inventoryHandler);
    /**
     * Un registers an inventory (typically done on inventory close).
     * @param inventory The inventory to unregister.
     */
    void unregisterInventory(Inventory inventory);
    /**
     * Handles any click events that are triggered by custom inventories that occur on the server.
     * @param inventoryClickEvent The event triggered by a player clicking on an inventory.
     */
    void handleClick(InventoryClickEvent inventoryClickEvent);

    /**
     * Handles any events that are triggered by the opening of a custom inventory on the server.
     * @param inventoryOpenEvent The event triggered by a player opening a custom inventory.
     */
    void handleOpen(InventoryOpenEvent inventoryOpenEvent);

    /**
     * Handles any events that are triggered by the closing of a custom inventory on the server.
     * @param inventoryCloseEvent The event triggered by a player closing a custom inventory.
     */
    void handleClose(InventoryCloseEvent inventoryCloseEvent);

    /**
     * Logs the event via the logging package.
     * @param eventToLog The event to log.
     */
    void logEvent(Event eventToLog);
}
