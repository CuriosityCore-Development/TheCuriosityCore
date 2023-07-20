package io.curiositycore.thecuriositycore.database.mysql.table;

import io.curiositycore.thecuriositycore.database.Table;
import io.curiositycore.thecuriositycore.database.mysql.queries.SqlDataType;
import io.curiositycore.thecuriositycore.database.mysql.queries.SqlQueries;

import javax.sql.DataSource;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public abstract class BaseTable implements Table {
    protected String tableName;
    protected DataSource dataSourceForTable;
    protected List<BaseRow> rowList = new ArrayList<>();
    protected String[] columnNames;

    protected BaseTable(DataSource dataSourceForTable){
        this.dataSourceForTable = dataSourceForTable;
    }

    @Override
    public void initTableFromExisting() {
        //TODO figure out the best way to approach this.
    }

    @Override
    public void createTableInDataBase(String tableName) {
        SqlQueries.createNewTable(tableName,dataSourceForTable);
    }

    @Override
    public void updateTableInDataBase() {
         //TODO utilise other classes to update table
    }

    @Override
    public int getColumnAmount() {
        return this.columnNames.length;
    }

    protected void appendTableWithColumn(String columnName, String dataTypeString){
        SqlQueries.addColumnToTable(this.tableName,columnName,this.dataSourceForTable,dataTypeString);

        if(this.columnNames == null){
            this.columnNames = new String[]{columnName};
        }

        String[] newArrayProxy = new String[columnNames.length];
        this.columnNames = Arrays.copyOf(this.columnNames,newArrayProxy.length);
        this.columnNames[columnNames.length-1] = columnName;
    }
}
