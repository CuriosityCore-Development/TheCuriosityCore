package io.curiositycore.thecuriositycore.inventorymenus.palette;

import io.curiositycore.thecuriositycore.inventorymenus.buttons.BaseInventoryButton;
import io.curiositycore.thecuriositycore.inventorymenus.buttons.InventoryButton;
import lombok.Getter;

import java.util.HashMap;
import java.util.Map;

//TODO current task is to use this palette to create an initial inventory based on the buttons of the pallete (assinged
//     to numbers, for ease of visulisation). The inventory would then be cloned per inventory open.
public abstract class BaseInventoryPalette implements InventoryPalette {
    @Getter
    protected Map<Integer,BaseInventoryButton> inventoryButtonMap = initButtonMap();
    Map<Integer, BaseInventoryButton> initButtonMap(){
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
    protected abstract int[][] getInventoryArray();
    protected abstract BaseInventoryButton[] getButtonPalette();


}
