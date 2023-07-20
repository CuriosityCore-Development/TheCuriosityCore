package io.curiositycore.thecuriositycore.database;

import java.util.List;

/**
 * Interface that defines the functionality of a table within a database.
 */
public interface Table {
    void initTableFromExisting();
    void createTableInDataBase(String tableName);
    void insertRow(Object[] rowToAdd);
    void updateRow(int rowIndex,Object[] updatedRow);
    void deleteRow(int rowIndex);
    void updateTableInDataBase();
    int getColumnAmount();
}
