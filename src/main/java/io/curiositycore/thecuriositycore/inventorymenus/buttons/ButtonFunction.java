package io.curiositycore.thecuriositycore.inventorymenus.buttons;

import org.bukkit.entity.Player;

/**
 * Interface representing the functionality of an inventory menu button that has functionality, when clicked.
 */
public interface ButtonFunction {
    /**
     * Activate the inventory menu button's functionality.
     * @param activatingPlayer The player who activated the button.
     */
    void activateButton(Player activatingPlayer);
}
