package io.curiositycore.thecuriositycore.database.sql.data.types.integers;

import java.util.Arrays;

public class SmallIntSqlData extends IntegerSqlData {
    protected SmallIntSqlData(String columnName, Integer dataTypeValue, int... dataParameters) {
        super(columnName, dataTypeValue, dataParameters);
    }
    //TODO custom exceptions here for when size is too large or too many params
    @Override
    protected boolean correctParams(int... intParams) {
        return intParams.length == 1 && Math.abs(Arrays.stream(intParams).toArray()[0]) <= this.size;
    }

    @Override
    protected String initDataTypeQuery() {
        return "SMALLINT";
    }
}
