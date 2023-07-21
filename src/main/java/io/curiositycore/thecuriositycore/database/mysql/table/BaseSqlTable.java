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

    //TODO This needs to be done when we add the full merge (due to needing other library packages to complete.
    @Override
    public void initTableFromExisting() {

    }

    @Override
    public void createTableInDataBase(String tableName) {
        SqlQueries.createNewTable(tableName,dataSourceForTable);
    }

    @Override
    public void insertRow(Object[] rowToAdd) {
        String[] columnNames = getColumnNames();
        SqlDataTypes[] dataTypes = getDataTypes();
        if(areCorrectDataTypes(rowToAdd,dataTypes)){
            throw new RuntimeException("The row was not added as it's data types do not match that of the table's" +
                    "columns!");
        }
        SqlQueries.insertValuesIntoTable(this.tableName,this.dataSourceForTable,columnNames,rowToAdd);
        this.rowList.add(new SqlRow(rowToAdd,this.rowList.size()+1));

    }

    //TODO This needs to be done when we add the full merge (due to needing other library packages to complete.
    @Override
    public void updateRow(int rowIndex, Object[] updatedRow) {

    }

    //TODO This needs to be done when we add the full merge (due to needing other library packages to complete.
    @Override
    public void deleteRow(int rowIndex) {

    }

    //TODO This needs to be done when we add the full merge (due to needing other library packages to complete.
    @Override
    public void updateTableInDataBase() {

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

    /**
     * Gets the names of each column in this table.
     * @return The names of each column in thsi table.
     */
    private String[] getColumnNames(){
        return Arrays.stream(this.columnsInTable).map(SqlColumn::getColumnName).toArray(String[]::new);
    }

    /**
     * Gets the data types of each column in this table.
     * @return The data types of each column in this table.
     */
    private SqlDataTypes[] getDataTypes(){
        return Arrays.stream(this.columnsInTable).map(SqlColumn::getDataType).toArray(SqlDataTypes[]::new);
    }

    /**
     * Checks to see if each object within the row is of the correct data type, throwing an exception if not. This helps
     * ensure type safety and reduces the risk of SQL injection.
     * @param valuesToCheck The object array of values to add to the table.
     * @param columnTypes The data types of each column within the table.
     * @return True if the value's data types match that of the column'ss, false if they do not.
     */
    private boolean areCorrectDataTypes(Object[] valuesToCheck, SqlDataTypes[] columnTypes ){

        for(int i = 0; i <=valuesToCheck.length; i++){
            Class<?> classOfDataType = columnTypes[i].getTypeClass();
            if (!classOfDataType.isInstance(valuesToCheck[i])) {
                return false;
            }
        }
        return true;
    }
}
