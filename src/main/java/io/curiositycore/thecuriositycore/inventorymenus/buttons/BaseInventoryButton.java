package io.curiositycore.thecuriositycore.inventorymenus.buttons;

import lombok.Getter;

import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;



public abstract class BaseInventoryButton implements InventoryButton{
    @Getter
    ItemStack buttonIcon;
    protected BaseInventoryButton(){
        initialize();
    }

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
}
