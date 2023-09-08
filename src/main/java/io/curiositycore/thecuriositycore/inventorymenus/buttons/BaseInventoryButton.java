package io.curiositycore.thecuriositycore.inventorymenus.buttons;

import lombok.Getter;

import net.kyori.adventure.text.Component;
import org.bukkit.Material;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;

import java.util.List;

/**
 * Abstract that represents the generalisation of an item within a custom menu that acts as a button for the menu. <p>
 *
 * The button can not be taken, only pressed (with the pickup attempt being canceled on click).
 */
public abstract class BaseInventoryButton implements InventoryButton {
    /**
     * The icon of the button (just the itemstack within the slot of the inventory)
     */
    @Getter
    private ItemStack buttonIcon;

    /**
     * Constructor that initialises the button.
     */
    protected BaseInventoryButton(){
        initialize();
    }

    /**
     * Initialising function of the button.
     */
    @Override
    public void initialize() {
        try{
        this.buttonIcon = new ItemStack(initButtonMaterial());} catch(NullPointerException ignored){}
        ItemMeta iconMeta = this.buttonIcon.getItemMeta();
        try{
        iconMeta.lore(initButtonLore());} catch(NullPointerException ignored){}
        try{
            iconMeta.displayName(initButtonName());} catch(NullPointerException ignored){}
        this.buttonIcon.setItemMeta(iconMeta);
    }

    /**
     * Initialises the button's material.
     * @return The button's material.
     */
    protected abstract Material initButtonMaterial();

    /**
     * Initialises the buttons lore (e.g: Its description)
     * @return The button's lore.
     */
    protected abstract List<Component> initButtonLore();

    /**
     * Initialises the button's name.
     * @return The buttons name.
     */
    protected abstract Component initButtonName();
}
