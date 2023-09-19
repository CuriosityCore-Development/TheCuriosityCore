package io.curiositycore.thecuriositycore.database.sql.table;

import io.curiositycore.thecuriositycore.database.Credentials;
import io.curiositycore.thecuriositycore.database.sql.data.FormattedData;
import io.curiositycore.thecuriositycore.database.sql.data.PersistentDataContainer;
import io.curiositycore.thecuriositycore.database.sql.table.model.SqlColumn;
import io.curiositycore.thecuriositycore.database.sql.table.model.SqlRow;

import java.util.Set;

public interface Table {
    String getTableName();
    String getTableSchema();

    void addNewRow(PersistentDataContainer rowToAdd);
    SqlColumn[] getTableColumns();
    void addExistingRowSet(Set<SqlRow> setToAdd);
    void updateRow(int rowIndex,int columnIndex, FormattedData updatedData);
    void removeRow(int rowIndex);
    void createTableInDatabase(Credentials databaseCredentials);
    void updateToDatabase(Credentials databaseCredentials);

}
