package io.curiositycore.thecuriositycore.inventorymenus.palette;

import io.curiositycore.thecuriositycore.inventorymenus.buttons.BaseInventoryButton;

import lombok.Getter;

import java.util.HashMap;
import java.util.Map;

/**
 * Abstract representing the generalisation of a palette of {@link
 * io.curiositycore.thecuriositycore.inventorymenus.buttons.BaseInventoryButton BaseInventoryButtons} that allow for
 * easy creation of a custom inventory. <p>
 *
 * Utilises an array of arrays to make for easy visualisation of the layout of the inventory the palette is related to.
 */
public abstract class BaseInventoryPalette implements InventoryPalette {
    /**
     * The map of inventory buttons being utilised within the linked custom inventory. The keys represent the slot
     * number of the respective button.
     */
    @Getter
    protected Map<Integer,BaseInventoryButton> inventoryButtonMap = initButtonMap();

    /**
     * Initialises the button map by utilising the button palette.
     * @return The initialised map of button values with slot number keys.
     */
    protected Map<Integer, BaseInventoryButton> initButtonMap(){
        Map<Integer,BaseInventoryButton> buttonMap = new HashMap<>();
        BaseInventoryButton[] buttonPalette  = getButtonPalette();
        int currentRow = 0;
        int rowIndex;

        for(int[] inventoryRow : getInventoryArray()){
            rowIndex = 0;
            for(Integer buttonNumber : inventoryRow){
                buttonMap.put((currentRow*9) + rowIndex,buttonPalette[buttonNumber]);
                rowIndex +=1;
            }
            currentRow += 1;
        }
        return buttonMap;
    }

    /**
     * Gets the inventory array for this palette. Used to represent what buttons should be placed in which slot.
     * @return The inventory array for the palette.
     */
    protected abstract int[][] getInventoryArray();

    /**
     * Get the array of buttons being utilised within this palette. The indexes for the array are used for the
     * numerical representation within the inventory array.
     * @return The palette of buttons.
     */
    protected abstract BaseInventoryButton[] getButtonPalette();


}
