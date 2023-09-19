package io.curiositycore.thecuriositycore.database.sql.data.types.integers;

public class TinyIntSqlData extends IntegerSqlData {
    protected boolean isSigned;

    protected TinyIntSqlData(String columnName, Integer dataTypeValue, boolean isSigned, int... dataParameters) {
        super(columnName, dataTypeValue, dataParameters);
        this.isSigned = isSigned;
    }

    @Override
    protected String initDataTypeQuery() {
        return "TINYINT";
    }
}
