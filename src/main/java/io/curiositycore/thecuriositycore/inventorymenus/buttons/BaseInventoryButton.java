package io.curiositycore.thecuriositycore.inventorymenus.buttons;

import net.kyori.adventure.text.Component;
import org.bukkit.Material;
import org.bukkit.event.inventory.InventoryCloseEvent;
import org.bukkit.inventory.ItemStack;

import java.util.List;

public abstract class BaseInventoryButton implements InventoryButton{
    ItemStack buttonIcon;
    protected BaseInventoryButton(ItemStack buttonIcon){
        this.buttonIcon = buttonIcon;

    }

    @Override
    public void initialize() {
        this.buttonIcon.setType(initButtonMaterial());
        this.buttonIcon.getItemMeta().lore(initButtonLore());
        this.buttonIcon.displayName().append(initButtonName());
    }
}
