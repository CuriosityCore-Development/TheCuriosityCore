package io.curiositycore.thecuriositycore.database.mysql.table;

import io.curiositycore.thecuriositycore.database.Table;
import io.curiositycore.thecuriositycore.database.mysql.queries.SqlDataTypes;
import io.curiositycore.thecuriositycore.database.mysql.queries.SqlQueries;

import javax.sql.DataSource;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/**
 * Abstract class representing the generalisation of a table stored within a database. This class acts as a localised
 * cache of the table. <p>
 *     <br>
 *     <i>NOTE: Do not utilise this class for large data sets, as it would cause significant RAM utilisation.</i>
 */
public abstract class BaseSqlTable implements Table {
    /**
     * The name of the table.
     */
    protected String tableName;
    /**
     * The datasource that the table is stored within.
     */
    protected DataSource dataSourceForTable;

    /**
     * The list of rows within the table.
     */
    protected List<SqlRow> rowList = new ArrayList<>();

    /**
     * The columns within the table, containing both their names and variable types.
     */
    protected SqlColumn[] columnsInTable ;

    /**
     * The constructor that initialises the datasource for the table.
     * @param dataSourceForTable The datasource the table is to reside in.
     */
    protected BaseSqlTable(DataSource dataSourceForTable){
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
    public void insertRow(Object[] rowToAdd) {
        //TODO implement functionality
    }

    @Override
    public void updateRow(int rowIndex, Object[] updatedRow) {
        //TODO implement functionality
    }

    @Override
    public void deleteRow(int rowIndex) {
        //TODO implement functionality
    }

    @Override
    public void updateTableInDataBase() {
         //TODO utilise other classes to update table
    }

    @Override
    public int getColumnAmount() {
        return this.columnsInTable.length;
    }

    /**
     * Add a new column to the table, with a {@linkplain
     * io.curiositycore.thecuriositycore.database.mysql.queries.SqlDataTypes datatype} that has a singular parameter.
     * @param columnName The name of the column to add.
     * @param dataType The column's acceptable value data type.
     * @param param The parameter of the data type.
     */
    protected void addTableWithColumn(String columnName, SqlDataTypes dataType, int param){
        String dataTypeString = dataType.getDataType(param);
        SqlQueries.addColumnToTable(this.tableName,columnName,this.dataSourceForTable,dataTypeString);
        addColumnToCache(new SqlColumn(columnName,dataType));
    }

    /**
     Add a new column to the table, with a {@linkplain
     * io.curiositycore.thecuriositycore.database.mysql.queries.SqlDataTypes datatype} that has two parameters.
     * @param columnName The name of the column to add.
     * @param dataType The column's acceptable value data type.
     * @param firstParam The first parameter of the data type.
     * @param secondParam The second parameter of the data type.
     */
    protected void addTableWithColumn(String columnName, SqlDataTypes dataType, int firstParam, int secondParam){
        String dataTypeString = dataType.getDataType(firstParam,secondParam);
        SqlQueries.addColumnToTable(this.tableName,columnName,this.dataSourceForTable,dataTypeString);
        addColumnToCache(new SqlColumn(columnName,dataType));

    }

    /**
     * Adds a column to the table object, i.e. ensuring both the cache and the database table match.
     * @param sqlColumnToAdd The column to add to the cached table.
     */
    protected void addColumnToCache(SqlColumn sqlColumnToAdd){
        if(this.columnsInTable == null){
            this.columnsInTable = new SqlColumn[]{sqlColumnToAdd};
        }

        SqlColumn[] newArrayProxy = new SqlColumn[columnsInTable.length];
        this.columnsInTable = Arrays.copyOf(this.columnsInTable,newArrayProxy.length);
        this.columnsInTable[columnsInTable.length-1] = sqlColumnToAdd;
    }
}
