package io.curiositycore.thecuriositycore.inventorymenus.managers;

import io.curiositycore.thecuriositycore.inventorymenus.handlers.InventoryHandler;
import org.bukkit.inventory.Inventory;

import java.util.HashMap;
import java.util.Map;

public abstract class BaseCustomInventoryManager implements CustomInventoryManager {
    Map<Inventory, InventoryHandler> activeInventoryMap = new HashMap<>();

}
