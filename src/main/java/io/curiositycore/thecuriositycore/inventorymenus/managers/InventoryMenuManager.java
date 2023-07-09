package io.curiositycore.thecuriositycore.inventorymenus.managers;

import io.curiositycore.thecuriositycore.inventorymenus.handlers.InventoryHandler;
import org.bukkit.event.Event;
import org.bukkit.event.inventory.InventoryClickEvent;
import org.bukkit.event.inventory.InventoryCloseEvent;
import org.bukkit.event.inventory.InventoryOpenEvent;
import org.bukkit.inventory.Inventory;

public class InventoryMenuManager extends BaseCustomInventoryManager{
    @Override
    public void registerHandledInventory(Inventory inventory, InventoryHandler inventoryHandler) {
        this.activeInventoryMap.put(inventory,inventoryHandler);
    }

    @Override
    public void unregisterInventory(Inventory inventory) {
        this.activeInventoryMap.remove(inventory);
    }

    @Override
    public void handleClick(InventoryClickEvent inventoryClickEvent) {
        InventoryHandler inventoryHandler = this.activeInventoryMap.get(inventoryClickEvent.getInventory());
        if(inventoryHandler == null){
            return;
        }
        inventoryClickEvent.setCancelled(true);
        inventoryHandler.onClick(inventoryClickEvent.getSlot(),inventoryClickEvent.getWhoClicked());
    }

    @Override
    public void handleOpen(InventoryOpenEvent inventoryOpenEvent) {
        InventoryHandler inventoryHandler = this.activeInventoryMap.get(inventoryOpenEvent.getInventory());
        if(inventoryHandler == null){
            return;
        }
        inventoryHandler.onOpen();
    }

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
