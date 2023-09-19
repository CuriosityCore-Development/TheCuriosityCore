package io.curiositycore.thecuriositycore.database.sql.data.types.integers;

import io.curiositycore.thecuriositycore.database.sql.data.types.SqlParametrisedData;

import java.util.Arrays;

public abstract class IntegerSqlData extends SqlParametrisedData<Integer> {
    protected static final int size = 255;

    protected IntegerSqlData(String columnName, Integer dataTypeValue, int... dataParameters) {
        super(columnName, dataTypeValue, dataParameters);
    }

    @Override
    protected boolean correctParams(int... intParams) {
        return intParams.length == 1 && Arrays.stream(intParams).toArray()[0] <= size;
    }


    @Override
    protected Class<Integer> initDataClazz() {
        return Integer.class;
    }

    @Override
    protected String initSqlQuery() {
        return this.dataTypeQuery + "("+ size + ")";
    }

}
