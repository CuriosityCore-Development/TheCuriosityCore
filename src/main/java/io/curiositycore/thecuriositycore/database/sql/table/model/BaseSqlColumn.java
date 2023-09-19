package io.curiositycore.thecuriositycore.database.sql.table.model;
public abstract class BaseSqlColumn implements SqlColumn{
    private String columnName;
    //TODO need a datatype object to initiate the datatype query.
    protected BaseSqlColumn(String columnName){
        this.columnName = columnName;
    }
    @Override
    public String getColumnName() {
        return this.columnName;
    }

    @Override
    public String getDataTypeQuery() {
        return null;
    }
}
