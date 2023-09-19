package io.curiositycore.thecuriositycore.database.sql.data.types.integers;

public class MediumIntSqlData extends IntegerSqlData{
    protected MediumIntSqlData(String columnName, Integer dataTypeValue, int... dataParameters) {
        super(columnName, dataTypeValue, dataParameters);
    }

    @Override
    protected String initDataTypeQuery() {
        return "MEDIUMINT";
    }
}
