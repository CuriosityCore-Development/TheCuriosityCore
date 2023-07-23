package io.curiositycore.thecuriositycore.database.mysql.queries;

import io.curiositycore.thecuriositycore.database.mysql.table.SqlColumn;
import io.curiositycore.thecuriositycore.database.mysql.table.SqlRow;
import org.bukkit.Bukkit;

import javax.sql.DataSource;
import java.sql.*;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

/**
 * Utility class responsible for querying a SQL datasource for the purposes of creating, manipulating or retrieving table
 * data.
 */
public class SqlQueries {
    /**
     * Private constructor to avoid attempted construction of this utility class.
     */
    private SqlQueries(){
        throw new IllegalStateException("SqlQueries is a Utility Class");
    }

    /**
     * Creates a new table within the Sql database. The table has no columns other than id.
     * @param tableName What to name the newly created table.
     * @param dataSource The datasource where the table is to be created.
     */
    public static void createNewTable(String tableName,DataSource dataSource){
        String statement = String.format(SqlGeneralQuery.CREATE_TABLE.getSql(),tableName);
        executeWithoutParams(statement,dataSource);
    }

    /**
     * Adds a column to the table. The column has a user defined datatype.
     * @param tableName The name of the table to add the column to.
     * @param columnName The name of the column to create.
     * @param dataSource The datasource where the table is located.
     * @param dataTypeString The string that represents the datasource.
     */
    public static void addColumnToTable(String tableName, String columnName,DataSource dataSource, String dataTypeString){
        executeWithoutParams(String.format(SqlGeneralQuery.APPEND_COLUMN_TO_TABLE.getSql(),columnName,dataTypeString),dataSource);
    }

    /**
     * Inserts a row of values into an existing table.
     * @param tableName The name of the table to add the row of values to.
     * @param dataSource The data source where the table is located.
     * @param valueIds The names of each of columns.
     * @param values The values of the row to add.
     */
    public static void insertValuesIntoTable(String tableName,DataSource dataSource, String[] valueIds, Object[] values){
        if (equalIdAndValueLengths(valueIds,values)) {
            return;
        }
        String columns = String.join(", ", valueIds);
        String placeholders = String.join(", ", Collections.nCopies(values.length, "?"));
        String statement = String.format(SqlGeneralQuery.INSERT_TABLE_VALUE.getSql(), tableName, columns, placeholders);
        executeWithParams(statement,dataSource, values);
    }
    public static String getTableName(String tableName){
        //TODO Complete query method.
        return null;
    }
    public static int getTableSize(String tableName,DataSource dataSource){
        String statement = String.format(SqlGeneralQuery.GET_TABLE_SIZE.getSql(),tableName);
        try(ResultSet resultSet = retrieveSqlDataWithoutParams(statement,dataSource)){
            return resultSet.getInt("tableSize");
        }
        catch(SQLException exception){
            throw new RuntimeException("Retrieval of table size was not successful!");
        }
    }

    /**
     * Gets all the rows within the specified table.
     * @param tableName The name of the table.
     * @param dataSource The data source where the table is located.
     * @return The rows of the table.
     */
    public static List<SqlRow> getRowData(String tableName, DataSource dataSource) throws SQLException {
        List<SqlRow> sqlRows = new ArrayList<>();
        String statement = String.format(SqlGeneralQuery.GET_TABLE_ROWS.getSql(),tableName);

        ResultSet resultSet = retrieveSqlDataWithoutParams(statement,dataSource);
        int columnCount = resultSet.getMetaData().getColumnCount();

        while(resultSet.next()){

            Object[] rowData = new Object[columnCount];
            for (int i = 1; i <= columnCount; i++) {
                rowData[i - 1] = resultSet.getObject(i);
            }
            sqlRows.add( new SqlRow(rowData,sqlRows.size()+1));
        }

        return sqlRows;
    }

    public static ResultSet retrieveSqlDataWithoutParams(String statement,DataSource dataSource){
        try(Connection connection = dataSource.getConnection();
            PreparedStatement preparedStatement = connection.prepareStatement(statement)){
            return preparedStatement.executeQuery();
        }
        catch (SQLException e) {
            throw new RuntimeException("Database operation failed", e);
        }
    }


    /**
     * Execution of an SQL query with no parameters.
     * @param statement The statement to query, as a string.
     * @param dataSource The datasource where the query is to be executed.
     */
    private static void executeWithoutParams(String statement,DataSource dataSource){
        try(Connection connection = dataSource.getConnection();
            PreparedStatement preparedStatement = connection.prepareStatement(statement)){
            preparedStatement.executeUpdate();
        }
        catch (SQLException e) {
            throw new RuntimeException("Database value retrieval failed", e);
        }

    }

    /**
     * Execution of an SQL query with parameters.
     * @param statement The statement to query, as a string.
     * @param dataSource The datasource where the query is to be executed.
     * @param values The values of the query's parameters.
     */
    private static void executeWithParams(String statement,DataSource dataSource, Object[] values) {
        try(Connection connection = dataSource.getConnection();
            PreparedStatement preparedStatement = connection.prepareStatement(statement)){
            for (int i = 0; i < values.length; i++) {
                preparedStatement.setObject(i + 1, values[i]);
            }
            preparedStatement.executeUpdate();
        }
        catch (SQLException e) {
            throw new RuntimeException("Database operation failed", e);
        }
    }

    /**
     * Checks to see if the value ids (i.e. column names) are of the same length as the objects to be added to a
     * table.
     * @param valueIds The ids of the column's in the table.
     * @param values The values of the row being added to a table.
     * @return True if the 2 arrays are of equal length, false if they are not.
     */
    private static boolean equalIdAndValueLengths(String[] valueIds,Object[] values){
        if(valueIds.length != values.length){
            String logMsg = "Mismatched ids and values: (ValueIds): " + valueIds.length + " (Values): "+values.length;
            Bukkit.getLogger().warning(logMsg);
            return false;
        }
        return true;
    }

}
