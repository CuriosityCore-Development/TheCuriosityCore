package io.curiositycore.thecuriositycore.database.mysql.table;

import io.curiositycore.thecuriositycore.database.Table;
import io.curiositycore.thecuriositycore.database.mysql.queries.SqlDataTypes;
import io.curiositycore.thecuriositycore.database.mysql.queries.SqlQueries;
import lombok.Getter;
import org.bukkit.Bukkit;

import javax.sql.DataSource;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.NoSuchElementException;

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
    @Getter
    protected List<SqlRow> rowList = new ArrayList<>();
    @Getter
    protected int currentRows = 0;


    /**
     * The columns within the table, containing both their names and variable types.
     */
    @Getter
    protected SqlColumn[] columnsInTable ;

    /**
     * The constructor that initialises the datasource for the table.
     * @param dataSourceForTable The datasource the table is to reside in.
     */
    protected BaseSqlTable(DataSource dataSourceForTable, String tableName){
        this.dataSourceForTable = dataSourceForTable;
        this.tableName = tableName;
        initTable();
    }

    /**
     * Initialises the table. Creation is determined by if the table already exists. If existing, its values are queried
     * and used for construction. If it does not, a new table with the user-defined columns are constructed.
     */
    protected void initTable(){
        boolean isInDatabase  = SqlQueries.tableExistsInDatabase(this.tableName,this.dataSourceForTable);
        if(isInDatabase){
            this.rowList = setRowList();
            this.currentRows = rowList.size();
            initColumns(true);
            return;
        }
        SqlQueries.createNewTable(this.tableName,this.dataSourceForTable);
        initColumns(false);
    }

    /**
     * Initialises the columns of the table,in the event that the table
     */
    protected abstract SqlColumn[] initColumns(boolean tableExistsInDatabase);

    /**
     * Sets the row data of the table via existing values within the SQL database.
     * @return The row data from the existing table.
     */
    protected List<SqlRow> setRowList(){
        try{
            return SqlQueries.getRowData(this.tableName,dataSourceForTable);
        }catch (SQLException exception){
         throw new RuntimeException("Error in retrieving data!");
        }
    }

    @Override
    public void insertRow(Object[] rowToAdd) {
        String[] columnNames = getColumnNames();
        SqlDataTypes[] dataTypes = getDataTypes();
        if(!areCorrectDataTypes(rowToAdd,dataTypes)){
            throw new NoSuchElementException("The row was not added as it's data types do not match that of the table's" +
                    "columns!");
        }
        this.rowList.add(new SqlRow(rowToAdd,this.rowList.size()+1));

    }

    @Override
    public void updateRow(int rowIndex, Object[] updatedRow) {
        SqlRow rowToUpdate = rowList.get(rowIndex-1);
        rowToUpdate.setRowData(updatedRow);
        SqlQueries.updateRow(this.tableName,dataSourceForTable,rowToUpdate,getColumnNames());
    }


    @Override
    public void deleteRow(int rowIndex) {
        SqlQueries.deleteRow(this.tableName,this.dataSourceForTable, rowIndex);
        try {
            this.rowList.remove(this.rowList.stream().
                    filter(sqlRow -> sqlRow.rowIndex == rowIndex).
                    findFirst().
                    orElseThrow(NoSuchElementException::new));

            this.currentRows = this.rowList.size();
            if (currentRows == 0) {
                SqlQueries.resetIds(this.tableName, this.dataSourceForTable);
            }
        }
        catch(NoSuchElementException noSuchElementException){
            throw new NoSuchElementException("The row with index: " + rowIndex + " does not exist within the database table!");
        }
    }


    /**
     * Updates the SQL database with any values in the cache that have currently not been recorded.
     */
    @Override
    public void updateTableInDataBase() {

        for(int i = this.currentRows; i <= this.rowList.size()-1 ; i++){
            SqlQueries.insertValuesIntoTable(this.tableName,
                                         this.dataSourceForTable, getColumnNames(), this.rowList.get(i).getRowData());
        }
        this.currentRows = this.rowList.size();

    }


    /**
     * Add a new column to the table, with a {@linkplain
     * io.curiositycore.thecuriositycore.database.mysql.queries.SqlDataTypes datatype} that has a no parameter.
     * @param columnName The name of the column to add.
     * @param dataType The column's acceptable value data type.
     */
    protected void addColumnToTable(String columnName, SqlDataTypes dataType, boolean tableExistsInDatabase){
        String dataTypeString = dataType.getDataType();
        if(!tableExistsInDatabase){
            SqlQueries.addColumnToTable(this.tableName,columnName,this.dataSourceForTable,dataTypeString);
        }
        addColumnToCache(new SqlColumn(columnName,dataType));
    }
    /**
     * Add a new column to the table, with a {@linkplain
     * io.curiositycore.thecuriositycore.database.mysql.queries.SqlDataTypes datatype} that has a singular parameter.
     * @param columnName The name of the column to add.
     * @param dataType The column's acceptable value data type.
     * @param param The parameter of the data type.
     */
    protected void addColumnToTable(String columnName, SqlDataTypes dataType, int param, boolean tableExistsInDatabase){
        String dataTypeString = dataType.getDataType(param);
        if(!tableExistsInDatabase) {
            SqlQueries.addColumnToTable(this.tableName, columnName, this.dataSourceForTable, dataTypeString);
        }
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
    protected void addColumnToTable(String columnName, SqlDataTypes dataType, int firstParam, int secondParam, boolean tableExistsInDatabase){
        String dataTypeString = dataType.getDataType(firstParam,secondParam);
        if(!tableExistsInDatabase) {
            SqlQueries.addColumnToTable(this.tableName, columnName, this.dataSourceForTable, dataTypeString);
        }
        addColumnToCache(new SqlColumn(columnName,dataType));

    }

    /**
     * Adds a column to the table object, i.e. ensuring both the cache and the database table match.
     * @param sqlColumnToAdd The column to add to the cached table.
     */
    protected void addColumnToCache(SqlColumn sqlColumnToAdd){
        if(this.columnsInTable == null){
            this.columnsInTable = new SqlColumn[]{sqlColumnToAdd};
            return;
        }

        SqlColumn[] newArrayProxy = new SqlColumn[columnsInTable.length+1];
        this.columnsInTable = Arrays.copyOf(this.columnsInTable,newArrayProxy.length);
        this.columnsInTable[columnsInTable.length-1] = sqlColumnToAdd;
    }

    /**
     * Gets the names of each column in this table.
     * @return The names of each column in this table.
     */
    protected String[] getColumnNames(){
        return Arrays.stream(this.columnsInTable).map(SqlColumn::getColumnName).toArray(String[]::new);
    }

    /**
     * Gets the data types of each column in this table.
     * @return The data types of each column in this table.
     */
    protected SqlDataTypes[] getDataTypes(){
        return Arrays.stream(this.columnsInTable).map(SqlColumn::getDataType).toArray(SqlDataTypes[]::new);
    }

    /**
     * Checks to see if each object within the row is of the correct data type. This helps
     * ensure type safety and reduces the risk of SQL injection.
     * @param valuesToCheck The object array of values to add to the table.
     * @param columnTypes The data types of each column within the table.
     * @return True if the value's data types match that of the columns, false if they do not.
     */
    protected boolean areCorrectDataTypes(Object[] valuesToCheck, SqlDataTypes[] columnTypes ){

        for(int i = 0; i <=valuesToCheck.length-1; i++){
            Class<?> classOfDataType = columnTypes[i].getTypeClass();
            if (!classOfDataType.isInstance(valuesToCheck[i])) {
                return false;
            }
        }
        return true;
    }

    /**
     * Gets the cast value of the Sql row. This functionality is cruical to ensure correct retreival of data from
     * database rows.
     * @param clazz The class of the value to retrieve.
     * @param object The object to be cast.
     * @return The cast value of the retrieved row data.
     * @param <T> The generic of the class of the object to retrieve.
     */
    protected<T> T getCastedValue(Class<T> clazz, Object object){
        if(clazz.isInstance(object)){
            return clazz.cast(object);
        }
        return null;
    }

    /**
     * Gets the row within the sqlRow field, based on the row's index.
     * @param rowIndex The index of the row to retrieve.
     * @return The SqlRow to retrieve.
     */
    protected SqlRow getRowBaseOnIndex(int rowIndex){
        return this.getRowList().stream().filter(sqlRow -> sqlRow.getRowIndex() == rowIndex).
                findFirst().
                orElseThrow(NoSuchElementException::new);
    }

}
