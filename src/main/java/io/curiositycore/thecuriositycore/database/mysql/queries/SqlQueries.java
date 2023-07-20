package io.curiositycore.thecuriositycore.database.mysql.queries;

import io.curiositycore.thecuriositycore.database.mysql.SQLGeneralQuery;
import org.bukkit.Bukkit;

import javax.sql.DataSource;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.Collections;

public class SqlQueries {
    private SqlQueries(){
        throw new IllegalStateException("SqlQueries is a Utility Class");
    }
    public static void createNewTable(String tableName,DataSource dataSource){
        String statement = String.format(SQLGeneralQuery.CREATE_TABLE.getSql(),tableName);
        executeWithoutParams(statement,dataSource);
    }
    public static void addColumnToTable(String tableName, String columnName,DataSource dataSource, String dataTypeString){
        executeWithoutParams(String.format(SQLGeneralQuery.TABLE_APPEND.getSql(),columnName,dataTypeString),dataSource);
    }
    public static void insertValuesIntoTable(String tableName,DataSource dataSource, String[] valueIds, Object[] values){
        if (equalIdAndValueLengths(valueIds,values)) {
            return;
        }
        String columns = String.join(", ", valueIds);
        String placeholders = String.join(", ", Collections.nCopies(values.length, "?"));
        String statement = String.format(SQLGeneralQuery.INSERT_TABLE_VALUE.getSql(), tableName, columns, placeholders);
        executeWithParams(statement,dataSource, values);
    }
    //TODO add try-with-resources with the execute methods to auto close.
    private static void executeWithoutParams(String statement,DataSource dataSource){
        try{Connection connection = dataSource.getConnection();
            PreparedStatement preparedStatement = connection.prepareStatement(statement);
            preparedStatement.executeUpdate();
            connection.close();
        }
        catch (SQLException e) {
            throw new RuntimeException("Database operation failed", e);
        }
    }
    //TODO add try-with-resources with the execute methods to auto close.
    private static void executeWithParams(String statement,DataSource dataSource, Object[] values) {
        try{Connection connection = dataSource.getConnection();
            PreparedStatement preparedStatement = connection.prepareStatement(statement);
            for (int i = 0; i < values.length; i++) {
                preparedStatement.setObject(i + 1, values[i]);
            }
            preparedStatement.executeUpdate();
            connection.close();
        }
        catch (SQLException e) {
            throw new RuntimeException("Database operation failed", e);
        }
    }

    private static boolean equalIdAndValueLengths(String[] valueIds,Object[] values){
        if(valueIds.length != values.length){
            String logMsg = "Mismatched ids and values: (ValueIds): " + valueIds.length + " (Values): "+values.length;
            Bukkit.getLogger().warning(logMsg);
            return false;
        }
        return true;
    }

}
