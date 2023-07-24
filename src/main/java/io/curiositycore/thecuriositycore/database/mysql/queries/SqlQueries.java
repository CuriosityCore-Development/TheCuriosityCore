package io.curiositycore.thecuriositycore.database.mysql.queries;

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
        executeWithoutParams(String.format(SqlGeneralQuery.APPEND_COLUMN_TO_TABLE.getSql(), tableName,columnName,dataTypeString),dataSource);
    }

    /**
     * Updates the row of an SQL table. Creates a statement with enough "?" placeholders to accommodate the number of
     * columns in the table.
     * @param tableName The name of the table to update the row of.
     * @param dataSource The datasource where the table is located.
     * @param rowToUpdate The SqlRow to update.
     * @param columnNames The name of the columns.
     */
    public static void updateRow(String tableName, DataSource dataSource, SqlRow rowToUpdate, String[] columnNames){
        String formattedValuePlaceholders = String.join(" = ? , ", columnNames)+ "= ?";
        String formattedStatement = String.format(SqlGeneralQuery.UPDATE_ROW.getSql(),tableName, formattedValuePlaceholders, "id =" + rowToUpdate.getRowIndex());
        executeWithParams(formattedStatement, dataSource,rowToUpdate.getRowData());
    }

    /**
     * Deletes a row within the SQL database table, based on the index id of the row.
     * @param tableName The name of the table to delete the row from.
     * @param dataSource The datasource where the table is located.
     * @param indexOfRowToDelete The index id of the row to delete.
     */
    public static void deleteRow(String tableName, DataSource dataSource, int indexOfRowToDelete){
        String formattedStatementString = String.format(SqlGeneralQuery.DELETE_ROW.getSql(),tableName,indexOfRowToDelete);
        executeWithoutParams(formattedStatementString,dataSource);
    }

    /**
     * Resets the id indexes of the database to 1. This is doen when all id's within a table have been deleted.
     * @param tableName The name of the table to reset the ids for.
     * @param dataSource The data source where the table is located.
     */
    public static void resetIds(String tableName, DataSource dataSource){
        String formattedStatementString = String.format(SqlGeneralQuery.RESET_ID_INCREMENTS.getSql(),tableName);
        executeWithoutParams(formattedStatementString,dataSource);
    }

    /**
     * Inserts a row of values into an existing table.
     * @param tableName The name of the table to add the row of values to.
     * @param dataSource The data source where the table is located.
     * @param valueIds The names of each of columns.
     * @param values The values of the row to add.
     */
    public static void insertValuesIntoTable(String tableName,DataSource dataSource, String[] valueIds, Object[] values){
        if (!equalIdAndValueLengths(valueIds,values)) {
            return;
        }
        String columns = String.join(", ", valueIds);
        String placeholders = String.join(", ", Collections.nCopies(values.length, "?"));
        String statement = String.format(SqlGeneralQuery.INSERT_TABLE_VALUE.getSql(), tableName, columns, placeholders);
        executeWithParams(statement,dataSource, values);
    }

    /**
     * Checks to see if the table exists within the SQL database.
     * @param tableName The name of the table to check.
     * @param dataSource The data source to check.
     * @return True if the table exists within the database, false if it does not.
     */
    public static boolean tableExistsInDatabase(String tableName, DataSource dataSource) {
        String formattedStatement = String.format(SqlGeneralQuery.GET_ALL_TABLE_DATA.getSql(),tableName);
        try {
            List<Object[]> rows = retrieveSqlDataWithoutParams(formattedStatement, dataSource);
            return true;
        }
        catch(SQLException sqlException){
            return false;
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

        List<Object[]> resultSet = retrieveSqlDataWithoutParams(statement,dataSource);
            for(Object[] row : resultSet){
                sqlRows.add(new SqlRow(row,resultSet.indexOf(row)+1));
            }
            return sqlRows;
    }

    /**
     * Retreives SQL data from a table without any parameters.
     * @param statement The statement to query the database with.
     * @param dataSource The datasource of the table to retrieve data from.
     * @return The data to retrieve as a List of object arrays.
     * @throws SQLException Exception that will occur if the query is not succesful.
     */
    public static List<Object[]> retrieveSqlDataWithoutParams(String statement, DataSource dataSource) throws SQLException {
        List<Object[]> resultList = new ArrayList<>();

        try (Connection connection = dataSource.getConnection();
             PreparedStatement preparedStatement = connection.prepareStatement(statement);
             ResultSet resultSet = preparedStatement.executeQuery()) {

            ResultSetMetaData metaData = resultSet.getMetaData();
            int columnCount = metaData.getColumnCount();

            while (resultSet.next()) {
                Object[] row = new Object[columnCount];
                for (int i = 1; i <= columnCount; i++) {
                    row[i - 1] = resultSet.getObject(i);
                }
                resultList.add(row);
            }

        }

        return resultList;
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
            e.printStackTrace();
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
