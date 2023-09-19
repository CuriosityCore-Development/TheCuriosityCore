package io.curiositycore.thecuriositycore.database.sql.data.types.floats;

import io.curiositycore.thecuriositycore.database.sql.data.FormattedData;

public abstract class DigitSqlData<T extends Number> implements FormattedData<T> {
    protected T numberValue;
    protected String columnName;
    protected int maximumDigits;

    @Override
    public String getFormattedQuerySegment() {
        return null;
    }

    @Override
    public boolean correctDataKey(FormattedData<?> formattedData) {
        return this.numberValue.getClass().isInstance(formattedData.getDataValue());
    }

    @Override
    public void updateData(FormattedData<?> dataToUpdateTo) {
        if(correctDataKey(dataToUpdateTo)){
            try{
                this.numberValue = (T) dataToUpdateTo.getDataValue();
            }
            catch(ClassCastException e){
                //TODO put custom exception here that accounts for the wrong data type (Unlikely case due to the check).
            }
        }
    }

    @Override
    public String getDataKey() {
        return this.columnName;
    }

    @Override
    public T getDataValue() {
        return this.numberValue;
    }

    @Override
    public void setDataKey(String columnNameToSet) {
        this.columnName = columnNameToSet;
    }
    //TODO check for the number of digits.
}
