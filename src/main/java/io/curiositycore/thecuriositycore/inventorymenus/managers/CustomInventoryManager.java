package io.curiositycore.thecuriositycore.inventorymenus.managers;

import io.curiositycore.thecuriositycore.inventorymenus.handlers.InventoryHandler;
import org.bukkit.event.inventory.InventoryClickEvent;
import org.bukkit.event.inventory.InventoryCloseEvent;
import org.bukkit.event.inventory.InventoryOpenEvent;
import org.bukkit.inventory.Inventory;

public interface CustomInventoryManager {
    void registerHandledInventory(Inventory inventory, InventoryHandler inventoryHandler);
    void unregisterInventory(Inventory inventory);
    void handleClick(InventoryClickEvent inventoryClickEvent);
    void handleOpen(InventoryOpenEvent inventoryOpenEvent);
    void handleClose(InventoryCloseEvent inventoryCloseEvent);
}
