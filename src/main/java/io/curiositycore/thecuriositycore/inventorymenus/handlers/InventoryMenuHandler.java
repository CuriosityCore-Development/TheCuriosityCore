package io.curiositycore.thecuriositycore.inventorymenus.handlers;

import io.curiositycore.thecuriositycore.inventorymenus.buttons.BaseInventoryButton;

/**
* Interface that represents the functionality of a inventory menu handler. 
*/

public interface InventoryMenuHandler extends InventoryHandler{
    /**
    * Decorates the inventory menu with the required buttons. 
    */
    void decorate();

    /**
    * Add a button to the inventory menu's button map. 
    */
    void addButton(BaseInventoryButton buttonToAdd,Integer slot);

}
