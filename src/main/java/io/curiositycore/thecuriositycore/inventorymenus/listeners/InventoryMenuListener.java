package io.curiositycore.thecuriositycore.inventorymenus.listeners;

import io.curiositycore.thecuriositycore.inventorymenus.managers.InventoryMenuManager;
import org.bukkit.event.Event;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.inventory.InventoryClickEvent;
import org.bukkit.event.inventory.InventoryCloseEvent;
import org.bukkit.event.inventory.InventoryOpenEvent;

/**
 * Listener for all events that are triggered via actions within a {@link
 * io.curiositycore.thecuriositycore.inventorymenus.handlers.InventoryMenu InventoryMenu}.
 */
public class InventoryMenuListener implements Listener {
    //TODO Once config and logging package is done, this needs setting up somehow.
    boolean loggerIsHooked = false;
    /**
     * The inventory menu manager attached to this listener. Acts as a mediator to execute functionality of triggered
     * events.
     */
    protected InventoryMenuManager inventoryMenuManager;

    /**
     * Constructor that initialises the linked inventory menu manager.
     * @param inventoryMenuManager The singleton instance of the inventory menu manager linked to this listener.
     */
    public InventoryMenuListener(InventoryMenuManager inventoryMenuManager){
        this.inventoryMenuManager = inventoryMenuManager;
    }

    /**
     * Listens for events triggered by clicks within a custom menu.
     * @param inventoryClickEvent Potential inventory menu click event.
     */
    @EventHandler
    public void onClick(InventoryClickEvent inventoryClickEvent){
        this.inventoryMenuManager.handleClick(inventoryClickEvent);
        logMenuEvent(inventoryClickEvent);
    }
    /**
     * Listens for events triggered by the opening of a custom menu.
     * @param inventoryOpenEvent Potential inventory menu open event.
     */
    @EventHandler
    public void onOpen(InventoryOpenEvent inventoryOpenEvent){
        this.inventoryMenuManager.handleOpen(inventoryOpenEvent);
        logMenuEvent(inventoryOpenEvent);
    }

    /**
     * Listens for events triggered by the closing of a custom menu.
     * @param inventoryCloseEvent Potential inventory menu close event.
     */
    @EventHandler
    public void onClose(InventoryCloseEvent inventoryCloseEvent){
        this.inventoryMenuManager.handleClose(inventoryCloseEvent);
        logMenuEvent(inventoryCloseEvent);
    }
    //TODO each of the handles need turning into booleans to be used in this method.

    /**
     * Called to log inventory events within the library's logger. Only executes if set to true for the plugin.
     * @param eventToLog  Event to be logged.
     */
    private void logMenuEvent(Event eventToLog){
        if(loggerIsHooked){
            this.inventoryMenuManager.logEvent(eventToLog);
        }
    }
}
