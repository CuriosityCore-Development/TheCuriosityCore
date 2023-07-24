package io.curiositycore.thecuriositycore.database.mysql.queries;

import lombok.Getter;

/**
 * Enum containing the string values of common sql queries. These are called to retrieve the common convention of the
 * query, to then be formatted within other classes. This ensures that no raw queries have to be implemented directly by
 * the end users.
 */
public enum SqlGeneralQuery {
    /**
     * Query for creating a new table within a database, with an id value that automatically increments. Requires a
     * "Table Name" parameter for the query to successfully execute.
     */
    CREATE_TABLE("CREATE TABLE IF NOT EXISTS `%s` (id INT AUTO_INCREMENT, PRIMARY KEY(id));"),

    /**
     * Query for appending a table with a new column. Requires a "Table Name" , "Var type" and "Var params" parameters
     * for the query to successfully execute.
     */
    APPEND_COLUMN_TO_TABLE("ALTER TABLE %s ADD %s %s;"),
    /**
     * Inserts a new value into a table within a database. Requires a "Table name" , "List of valueIds" and "list of
     * values" for the query to successfully execute.
     */
    INSERT_TABLE_VALUE("INSERT INTO %s (%s) VALUES (%s)"),
    /**
     * Update the row of a table. Usually done for plugins where the data of a row is subject to change (such as the
     * name of a land claim for example). Requires the table name, placeholders for the parameters to change, and the
     * id of the row.
     */
    UPDATE_ROW("UPDATE %s SET %s WHERE %s"),
    /**
     * Delete the row of a table. Requires the table name and the index id of the row to delete..
     */
    DELETE_ROW("DELETE FROM %s WHERE id = %s"),
    /**
     * Resets the Index IDs to 1, usually called when a table has 0 rows after a deletion event. requires the table name.
     */
    RESET_ID_INCREMENTS("ALTER TABLE %s AUTO_INCREMENT  = 1"),

    /**
     * Gets all the data within a specific table. Is used typically onEnable as part of the checks for pre-existing
     * tables. Requires the name of the table.
     */
    GET_ALL_TABLE_DATA("SELECT * from %s"),
    /**
     * Get all the rows of a table within a database. Requires the name of the table.
     */
    GET_TABLE_ROWS("SELECT * FROM %s");

    /**
     * The string required to perform the sql query, with parameter placeholders to be formatted by other classes.
     */
    @Getter
    private final String sql;

    /**
     * Constructor which initializes the sql string for each enum.
     * @param sql The sql string for enum.
     */

    SqlGeneralQuery(String sql) {
        this.sql = sql;
    }
}

