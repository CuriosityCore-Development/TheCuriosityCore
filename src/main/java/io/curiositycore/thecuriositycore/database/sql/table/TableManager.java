package io.curiositycore.thecuriositycore.database.sql.table;

import io.curiositycore.thecuriositycore.database.Credentials;
import io.curiositycore.thecuriositycore.database.sql.data.FormattedData;
import io.curiositycore.thecuriositycore.database.sql.data.PersistentDataContainer;
import io.curiositycore.thecuriositycore.util.managers.Manager;

public interface TableManager extends Manager<Table,String> {
    void updateAllTablesToDatabase(Credentials databaseCredentials);
    void updateTableRow(int rowIndex, int columnIndex, FormattedData dataToUpdate, String tableName);
    void removeTableRow(int rowToRemoveIndex, String tableName);
    void addRowDataToTable(PersistentDataContainer dataToAdd, String tableName);
}
