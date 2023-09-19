package io.curiositycore.thecuriositycore.database.sql.data.types.integers;

public class BigIntSqlData extends IntegerSqlData{
    protected BigIntSqlData(String columnName, Integer dataTypeValue, int... dataParameters) {
        super(columnName, dataTypeValue, dataParameters);
    }

    @Override
    protected String initDataTypeQuery() {
        return "BIGINT";
    }
}
