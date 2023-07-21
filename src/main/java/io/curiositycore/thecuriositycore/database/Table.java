package io.curiositycore.thecuriositycore.database;
/**
 * Interface that defines the functionality of a table within a database.
 */
public interface Table {
    /**
     * Initialises a table from an existing database table.
     */
    void initTableFromExisting();

    /**
     * Creates a new table, both in cache and within a database.
     * @param tableName The name of the table to create.
     */
    void createTableInDataBase(String tableName);

    /**
     * Insert a row of values into the table.
     * @param rowToAdd The row to add to the table.
     */
    void insertRow(Object[] rowToAdd);

    /**
     * Update an existing row within the table.
     * @param rowIndex The index of the row to update.
     * @param updatedRow The updated row to implement.
     */
    void updateRow(int rowIndex,Object[] updatedRow);

    /**
     * Delete a row from the table.
     * @param rowIndex The index of the row to delete.
     */
    void deleteRow(int rowIndex);

    /**
     * Update the database table with the values of the cached table.
     */
    void updateTableInDataBase();

    /**
     * Get the amount of columns within the database.
     * @return The amount of columns within the database.
     */
    int getColumnAmount();
}
