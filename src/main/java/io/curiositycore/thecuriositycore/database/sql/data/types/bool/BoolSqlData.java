package io.curiositycore.thecuriositycore.database.sql.data.types.bool;

import io.curiositycore.thecuriositycore.database.sql.data.FormattedData;

public class BoolSqlData implements FormattedData<Boolean> {
    private boolean dataBooleanValue;
    private String dataColumnName;
    public BoolSqlData(boolean dataBooleanValue, String dataColumnName){
        this.dataBooleanValue = dataBooleanValue;
        this.dataColumnName = dataColumnName;
    }
    @Override
    public String getFormattedQuerySegment() {
        return "BOOL";
    }

    @Override
    public boolean correctDataKey(FormattedData<?> formattedData) {
        return formattedData.getDataValue().getClass() == Boolean.class;
    }

    @Override
    public void updateData(FormattedData<?> dataToUpdateTo) {
        this.dataBooleanValue = (boolean) dataToUpdateTo.getDataValue();
    }

    @Override
    public String getDataKey() {
        return dataColumnName;
    }

    @Override
    public Boolean getDataValue() {
        return this.dataBooleanValue;
    }

    @Override
    public void setDataKey(String dataColumnNameToSet) {
        this.dataColumnName = dataColumnNameToSet;
    }
}
