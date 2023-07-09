package io.curiositycore.thecuriositycore.inventorymenus.managers;

import io.curiositycore.thecuriositycore.inventorymenus.handlers.InventoryHandler;
import org.bukkit.inventory.Inventory;

import java.util.HashMap;
import java.util.Map;
/**
 * Abstract class that represents the generalisation of a manager responsible for the functionality, manipulation and
 * retrieval of custom inventories within a plugin.
 */
public abstract class BaseCustomInventoryManager implements CustomInventoryManager {
    /**
     * A map of all inventories that are linked to a custom inventory handler.
     */
    protected Map<Inventory, InventoryHandler> activeInventoryMap = new HashMap<>();

}
