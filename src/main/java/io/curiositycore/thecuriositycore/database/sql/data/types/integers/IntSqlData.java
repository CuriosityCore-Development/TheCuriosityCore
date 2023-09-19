package io.curiositycore.thecuriositycore.database.sql.data.types.integers;

public class IntSqlData extends IntegerSqlData{
    protected IntSqlData(String columnName, Integer dataTypeValue, int... dataParameters) {
        super(columnName, dataTypeValue, dataParameters);
    }

    @Override
    protected String initDataTypeQuery() {
        return "INT";
    }
}
