package io.curiositycore.thecuriositycore.inventorymenus.handlers;

import org.bukkit.entity.HumanEntity;
import org.jetbrains.annotations.NotNull;

public interface InventoryHandler {

    void onClick(int slot, @NotNull HumanEntity whoClicked);
    void onOpen();
    void onClose();
}
