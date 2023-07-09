package io.curiositycore.thecuriositycore.inventorymenus.listeners;

import io.curiositycore.thecuriositycore.inventorymenus.managers.InventoryMenuManager;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.inventory.InventoryClickEvent;
import org.bukkit.event.inventory.InventoryCloseEvent;
import org.bukkit.event.inventory.InventoryOpenEvent;

public class InventoryMenuListener implements Listener {
    protected InventoryMenuManager inventoryMenuManager;
    @EventHandler
    public void onClick(InventoryClickEvent inventoryClickEvent){
        this.inventoryMenuManager.handleClick(inventoryClickEvent);
    }
    @EventHandler
    public void onOpen(InventoryOpenEvent inventoryOpenEvent){
        this.inventoryMenuManager.handleOpen(inventoryOpenEvent);
    }
    @EventHandler
    public void onClose(InventoryCloseEvent inventoryCloseEvent){
        this.inventoryMenuManager.handleClose(inventoryCloseEvent);
    }
}
