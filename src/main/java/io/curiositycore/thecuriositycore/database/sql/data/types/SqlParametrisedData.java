package io.curiositycore.thecuriositycore.database.sql.data.types;

import io.curiositycore.thecuriositycore.database.sql.data.FormattedData;
import io.curiositycore.thecuriositycore.database.util.exceptions.IncorrectSqlDataParamsException;
import io.curiositycore.thecuriositycore.database.util.exceptions.OutOfBoundsSqlDataParamsException;

import java.util.Arrays;

public abstract class SqlParametrisedData<T> implements FormattedData<T> {
    protected Class<T> dataClazz;
    protected T dataTypeValue;
    protected String sqlQuery;
    protected String dataTypeQuery;
    protected String columnName;
    protected int[] dataParameters;

    protected SqlParametrisedData(String columnName, T dataTypeValue, int... dataParameters){
        this.dataClazz = initDataClazz();
        this.dataParameters = initParameters(dataParameters);
        this.columnName = columnName;
        this.dataTypeValue = dataTypeValue;
        this.dataTypeQuery = initDataTypeQuery();

    }
    //TODO generalise to allow for various types of exception from the various checks in each child of SqlDataType
    protected int[] initParameters(int... intParams) throws IncorrectSqlDataParamsException{
        if(!correctParams(intParams)){
            throw new OutOfBoundsSqlDataParamsException(
                    "Incorrect values passed to the data"
                    , this.getClass().getName()
                    ,intParams);
        }
        return Arrays.stream(intParams).toArray();
    }
    protected abstract boolean correctParams(int... intParams);
    protected abstract Class<T> initDataClazz();
    protected abstract String initDataTypeQuery();

    @Override
    public String getFormattedQuerySegment() {
        return this.sqlQuery;
    }

    @Override
    public boolean correctDataKey(FormattedData formattedData) {
        return formattedData.getDataKey().equals(this.getDataKey());
    }

    @Override
    public void updateData(FormattedData<?> dataToUpdateTo) {
        if(this.correctDataKey(dataToUpdateTo) && dataToUpdateTo.getDataValue().getClass().isInstance(this.dataTypeValue)){
            this.dataTypeValue = (T) dataToUpdateTo.getDataValue();
        }
    }

    @Override
    public String getDataKey() {
        return this.columnName;
    }

    @Override
    public T getDataValue() {
        return this.getDataValue();
    }

    @Override
    public void setDataKey(String keyToSet) {
        this.columnName = keyToSet;
    }

    protected abstract String initSqlQuery();
}
