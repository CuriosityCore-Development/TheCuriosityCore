package io.curiositycore.thecuriositycore.inventorymenus.managers;

import io.curiositycore.thecuriositycore.inventorymenus.handlers.InventoryHandler;
import org.bukkit.event.Event;
import org.bukkit.event.inventory.InventoryClickEvent;
import org.bukkit.event.inventory.InventoryCloseEvent;
import org.bukkit.event.inventory.InventoryOpenEvent;
import org.bukkit.inventory.Inventory;

/**
 * Manager responsible for the registering, functionality and handling of all {@link
 * io.curiositycore.thecuriositycore.inventorymenus.handlers.InventoryMenu InventoryMenu} instances within a plugin.
 */
public class InventoryMenuManager extends BaseCustomInventoryManager{
    /**
     * Registers a new inventory to the manager.
     * @param inventory The inventory to register.
     * @param inventoryMenuToRegister The inventory menu instance to directly relate to the inventory key.
     */
    @Override
    public void registerHandledInventory(Inventory inventory, InventoryHandler inventoryMenuToRegister) {
        this.activeInventoryMap.put(inventory,inventoryMenuToRegister);
    }

    /**
     * Un registers an inventory (typically done on menu close).
     * @param inventory The inventory to unregister.
     */
    @Override
    public void unregisterInventory(Inventory inventory) {
        this.activeInventoryMap.remove(inventory);
    }

    /**
     * Handles any click events that are triggered by custom menus that occur on the server.
     * @param inventoryClickEvent The event triggered by a player clicking on a menu.
     */
    @Override
    public void handleClick(InventoryClickEvent inventoryClickEvent) {
        InventoryHandler inventoryMenu = this.activeInventoryMap.get(inventoryClickEvent.getInventory());
        if(inventoryMenu == null){
            return;
        }
        inventoryClickEvent.setCancelled(true);
        inventoryMenu.onClick(inventoryClickEvent.getSlot(),inventoryClickEvent.getWhoClicked());
    }

    /**
     * Handles any events that are triggered by the opening of a custom menus on the server.
     * @param inventoryOpenEvent The event triggered by a player opening a custom menu.
     */
    @Override
    public void handleOpen(InventoryOpenEvent inventoryOpenEvent) {
        InventoryHandler inventoryHandler = this.activeInventoryMap.get(inventoryOpenEvent.getInventory());
        if(inventoryHandler == null){
            return;
        }
        inventoryHandler.onOpen();
    }

    /**
     * Handles any events that are triggered by the closing of a custom menus on the server.
     * @param inventoryCloseEvent The event triggered by a player closing a custom menu.
     */
    @Override
    public void handleClose(InventoryCloseEvent inventoryCloseEvent) {
        InventoryHandler inventoryHandler = this.activeInventoryMap.get(inventoryCloseEvent.getInventory());
        if(inventoryHandler == null){
            return;
        }
        inventoryHandler.onClose();
    }


    @Override
    public void logEvent(Event eventToLog) {
        //TODO in future when logging package is complete this needs implementing
    }
}
