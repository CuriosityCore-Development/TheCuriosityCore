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

public abstract class InventoryMenu implements InventoryMenuHandler{
    @Getter
    protected final Inventory inventory;
    protected final Map<Integer, BaseInventoryButton> buttonMap = getInventoryPalette().getInventoryButtonMap();

    protected InventoryMenu(){

        this.inventory = createInventory();
        decorate();
    }
    @Override
    public void onClick(int slot, @NotNull HumanEntity whoClicked) {
        BaseInventoryButton buttonClicked = this.buttonMap.get(slot);
        if(!(buttonClicked instanceof InventoryActionButton actionButton)){
            return;
        }
        actionButton.activateButton((Player) whoClicked);
    }

    @Override
    public void onOpen() {

    }


    @Override
    public void decorate() {
        for(Integer slotNumber : this.buttonMap.keySet()){
            this.inventory.setItem(slotNumber,this.buttonMap.get(slotNumber).getButtonIcon());
        }
    }

    @Override
    public void addButton(BaseInventoryButton buttonToAdd,Integer slot) {
        this.buttonMap.put(slot,buttonToAdd);
    }

    protected Inventory createInventory() {
        return Bukkit.createInventory(null, getSize(), getTitle());

    }
    protected abstract Component getTitle();
    protected abstract int getSize();
    protected abstract BaseInventoryPalette getInventoryPalette();

}

