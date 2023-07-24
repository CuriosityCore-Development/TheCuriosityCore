package io.curiositycore.thecuriositycore.database;
/**
 * Interface that defines the functionality of a table within a database.
 */
public interface Table {

    /**
     * Insert a row of values into the table.
     *
     * @param rowToAdd The row to add to the table.
     */
    void insertRow(Object[] rowToAdd);

    /**
     * Update an existing row within the table.
     *
     * @param rowIndex   The index of the row to update.
     * @param updatedRow The updated row to implement.
     */
    void updateRow(int rowIndex, Object[] updatedRow);

    /**
     * Delete a row from the table.
     *
     * @param rowIndex The index of the row to delete.
     */
    void deleteRow(int rowIndex);

    /**
     * Update the database table with the values of the cached table.
     */
    void updateTableInDataBase();

}
