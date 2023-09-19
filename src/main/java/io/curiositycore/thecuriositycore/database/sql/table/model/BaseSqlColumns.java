package io.curiositycore.thecuriositycore.database.sql.table.model;

import io.curiositycore.thecuriositycore.database.sql.data.FormattedData;
import io.curiositycore.thecuriositycore.database.sql.data.PersistentDataContainer;

import java.util.Arrays;

public abstract class BaseSqlColumns implements SqlColumns{
    //TODO replace with the new SqlColumn interface.
    private Class<?>[] formattedDataArray;
    protected BaseSqlColumns(){
        this.formattedDataArray = initFormattedDataArray();
    }
    //TODO add custom exceptions here for informing server what error occurred
    @Override
    public boolean correctRowFormat(PersistentDataContainer potentialRowData) {
        if(potentialRowData.getFormattedData().length != formattedDataArray.length){
            return false;
        }
        FormattedData[] potentialRowDataArray = potentialRowData.getFormattedData();
        for(int arrayIndex = 0 ; arrayIndex<= formattedDataArray.length; arrayIndex++){
            if(!this.formattedDataArray[arrayIndex].isInstance(potentialRowDataArray[arrayIndex])){
                //TODO exception here
                return false;
            }
        }
        return false;
    }

    @Override
    public String getColumnDataTypesAsString() {
        String[] classTypeStringArray =  Arrays.stream(this.formattedDataArray)
                .map(Class::getSimpleName)
                .toArray(String[]::new);
        return String.join(", ", classTypeStringArray);
    }

    protected abstract Class<?>[] initFormattedDataArray();
}
