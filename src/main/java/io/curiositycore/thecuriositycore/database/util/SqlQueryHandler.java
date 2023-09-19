package io.curiositycore.thecuriositycore.database.util;

import io.curiositycore.thecuriositycore.database.Credentials;
import io.curiositycore.thecuriositycore.database.sql.data.FormattedData;
import io.curiositycore.thecuriositycore.database.sql.table.Table;
import io.curiositycore.thecuriositycore.database.sql.table.model.SqlColumn;
import io.curiositycore.thecuriositycore.database.sql.table.model.SqlColumns;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;

public class SqlQueryHandler {

    //TODO add the table removal, row removal and table creations into here
    public static void updateTable(Credentials credentials, FormattedData[] formattedData, String baseQuery){
        try(Connection connection = credentials.getConnection()) {
            updateRow(baseQuery,formattedData,connection);
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }
    //TODO need method to get the names of each column
    public static void createTable(Credentials credentials, Table tableToCreate){
        try(Connection connection = credentials.getConnection()){
            PreparedStatement creationStatement = connection.prepareStatement("CREATE TABLE IF NOT EXISTS "
                    + tableToCreate.getTableName()
                    + " (id INT AUTO_INCREMENT, PRIMARY KEY(id));");
            creationStatement.executeQuery();
            addColumnToTable(connection,tableToCreate.getTableName(),tableToCreate.getTableColumns());


        }
        catch(Exception e){
        }
    }

    private static void addColumnToTable(Connection connection, String tableName, SqlColumn[] columnsToAdd)throws SQLException{
        for(SqlColumn columnToAdd : columnsToAdd){
            connection.prepareStatement("ALTER TABLE "
                    + tableName
                    + " ADD "
                    + columnToAdd.getColumnName()
                    + " " + columnToAdd.getDataTypeQuery()+";");
        }
    }

    private static void updateRow(String query,FormattedData[] dataToAdd, Connection connection) {
        if (dataToAdd == null || dataToAdd.length == 0) {
            return;
        }

        StringBuilder columns = new StringBuilder("(");
        StringBuilder placeholders = new StringBuilder("(");

        for (int i = 0; i < dataToAdd.length; i++) {
            columns.append(dataToAdd[i].getDataKey());

            if (i < dataToAdd.length - 1) {
                columns.append(", ");
                placeholders.append(", ");
            }
        }
        columns.append(")");
        placeholders.append(")");

        String finalQuery = query + columns + " VALUES " + placeholders;

        try (PreparedStatement preparedStatement = connection.prepareStatement(finalQuery)) {
            for (int i = 0; i < dataToAdd.length; i++) {
                preparedStatement.setString(i + 1, dataToAdd[i].getFormattedQuerySegment());
            }

            preparedStatement.executeUpdate();

        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
}
