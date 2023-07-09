package io.curiositycore.thecuriositycore.inventorymenus.handlers;

import io.curiositycore.thecuriositycore.inventorymenus.buttons.BaseInventoryButton;
import io.curiositycore.thecuriositycore.inventorymenus.buttons.InventoryActionButton;
import io.curiositycore.thecuriositycore.inventorymenus.palette.BaseInventoryPalette;
import lombok.Getter;
import net.kyori.adventure.text.Component;
import org.bukkit.Bukkit;
import org.bukkit.entity.HumanEntity;
import org.bukkit.entity.Player;
import org.bukkit.inventory.Inventory;
import org.jetbrains.annotations.NotNull;

import java.util.Map;

/**
 * Abstract that represents the generalisation of any custom inventory menu within a plugin. This class handles any
 * events that correspond to an inventory menu, and hence bypasses a lot of the Bukkit classes that lead to performance
 * hits.
 */
public abstract class InventoryMenu implements InventoryMenuHandler{
    /**
     * The inventory that is to be produced for this menu.
     */
    @Getter
    protected final Inventory inventory;
    /**
     * Map of inventory slot keys and inventory button values.
     */
    protected final Map<Integer, BaseInventoryButton> buttonMap = getInventoryPalette().getInventoryButtonMap();

    /**
     * Constructor which creates the initial inventory for the menu instance.
     */
    protected InventoryMenu(){

        this.inventory = createInventory();
        decorate();
    }

    /**
     * Handles click events for the menu's inventory. Functionality is determined by the button that was clicked.
     * @param slot Inventory slot clicked.
     * @param whoClicked Which player clicked the inventory slot.
     */
    @Override
    public void onClick(int slot, @NotNull HumanEntity whoClicked) {
        BaseInventoryButton buttonClicked = this.buttonMap.get(slot);
        if(!(buttonClicked instanceof InventoryActionButton actionButton)){
            return;
        }
        actionButton.activateButton((Player) whoClicked);
    }

    /**
     * Handles open events for the menu's inventory. Typically, stays null unless something specific needs to happen.
     * Allows for flexibility.
     */
    @Override
    public void onOpen() {

    }

    /**
     * Decorate the newly created inventory with all the custom buttons assigned to it by its inventory palette.
     */
    @Override
    public void decorate() {
        for(Integer slotNumber : this.buttonMap.keySet()){
            this.inventory.setItem(slotNumber,this.buttonMap.get(slotNumber).getButtonIcon());
        }
    }

    /**
     * Add a button to the button map.
     * @param buttonToAdd Button to add to the map.
     * @param slot The inventory slot key for the map entry addition.
     */
    @Override
    public void addButton(BaseInventoryButton buttonToAdd,Integer slot) {
        this.buttonMap.put(slot,buttonToAdd);
    }

    /**
     * Create the inventory instance that will be decorated for this menu.
     * @return A newly created inventory of the correct size and title.
     */
    protected Inventory createInventory() {
        return Bukkit.createInventory(null, getSize(), getTitle());

    }

    /**
     * Get the title of the menu's inventory, as a <code>Component</code> from the adventure library.
     * @return The menu's inventory title.
     */
    protected abstract Component getTitle();

    /**
     * Get the size of the menu's inventory.
     * @return The inventory's size.
     */
    protected abstract int getSize();

    /**
     * Get the palette of custom buttons for this inventory menu. Used to construct the menu's inventory.
     * @return The palette of the menu's custom buttons.
     */
    protected abstract BaseInventoryPalette getInventoryPalette();

}

