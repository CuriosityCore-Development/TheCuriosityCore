package io.curiositycore.thecuriositycore.inventorymenus.buttons.actions;

import io.curiositycore.thecuriositycore.inventorymenus.buttons.BaseInventoryButton;
import io.curiositycore.thecuriositycore.inventorymenus.handlers.InventoryMenuHandler;

public abstract class MenuManipulatorButton extends BaseInventoryButton implements FunctionalButton, MenuManipulator{
    private InventoryMenuHandler holdingMenu;
    protected MenuManipulatorButton(InventoryMenuHandler holdingMenu){
        this.holdingMenu = holdingMenu;

    }
}
