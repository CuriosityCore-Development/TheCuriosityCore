package io.curiositycore.thecuriositycore.database.sql.table;

import io.curiositycore.thecuriositycore.database.Credentials;
import io.curiositycore.thecuriositycore.database.sql.data.FormattedData;
import io.curiositycore.thecuriositycore.database.sql.data.PersistentDataContainer;
import io.curiositycore.thecuriositycore.database.util.SqlQueryHandler;

import java.util.HashMap;
import java.util.Map;

public class SqlTableManager implements TableManager{
    private static SqlTableManager instance;
    public static SqlTableManager getInstance(){
        if(instance == null){
            instance = new SqlTableManager();
        }
        return instance;
    }
    private Map<String,Table> tableMap = new HashMap<>();
    @Override
    public void updateAllTablesToDatabase(Credentials databaseCredentials) {
        this.tableMap.values().forEach(table->table.updateToDatabase(databaseCredentials));
    }

    @Override
    public void updateTableRow(int rowIndex, int columnIndex, FormattedData dataToUpdate, String tableName) {
        this.tableMap.get(tableName).updateRow(rowIndex, columnIndex, dataToUpdate);
    }

    @Override
    public void removeTableRow(int rowToRemoveIndex, String tableName) {
        this.tableMap.get(tableName).removeRow(rowToRemoveIndex);
    }

    @Override
    public void addRowDataToTable(PersistentDataContainer dataToAdd, String tableName) {
        this.tableMap.get(tableName).addNewRow(dataToAdd);
    }

    @Override
    public void register(Table tableToRegister) {
        this.tableMap.put(tableToRegister.getTableName(),tableToRegister);
    }

    @Override
    public void unregister(String tableName) {
        this.tableMap.remove(tableName);
    }
}
