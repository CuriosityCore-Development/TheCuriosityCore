package io.curiositycore.thecuriositycore.inventorymenus.listeners;

import io.curiositycore.thecuriositycore.inventorymenus.managers.InventoryMenuManager;
import org.bukkit.event.Event;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.inventory.InventoryClickEvent;
import org.bukkit.event.inventory.InventoryCloseEvent;
import org.bukkit.event.inventory.InventoryOpenEvent;

public class InventoryMenuListener implements Listener {
    //TODO Once config and logging package is done, this needs setting up somehow.
    boolean loggerIsHooked = false;
    protected InventoryMenuManager inventoryMenuManager;
    public InventoryMenuListener(InventoryMenuManager inventoryMenuManager){
        this.inventoryMenuManager = inventoryMenuManager;
    }
    @EventHandler
    public void onClick(InventoryClickEvent inventoryClickEvent){
        this.inventoryMenuManager.handleClick(inventoryClickEvent);
        logMenuEvent(inventoryClickEvent);
    }
    @EventHandler
    public void onOpen(InventoryOpenEvent inventoryOpenEvent){
        this.inventoryMenuManager.handleOpen(inventoryOpenEvent);
        logMenuEvent(inventoryOpenEvent);
    }
    @EventHandler
    public void onClose(InventoryCloseEvent inventoryCloseEvent){
        this.inventoryMenuManager.handleClose(inventoryCloseEvent);
        logMenuEvent(inventoryCloseEvent);
    }
    //TODO each of the handles need turning into booleans to be used in this method.
    private void logMenuEvent(Event eventToLog){
        if(loggerIsHooked){
            this.inventoryMenuManager.logEvent(eventToLog);
        }
    }
}
