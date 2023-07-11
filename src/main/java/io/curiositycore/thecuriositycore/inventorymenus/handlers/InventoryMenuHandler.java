package io.curiositycore.thecuriositycore.inventorymenus.handlers;

import io.curiositycore.thecuriositycore.inventorymenus.buttons.BaseInventoryButton;

//TODO: DeltaNote, JavaDocs?

public interface InventoryMenuHandler extends InventoryHandler{
    void decorate();
    void addButton(BaseInventoryButton buttonToAdd,Integer slot);

}
