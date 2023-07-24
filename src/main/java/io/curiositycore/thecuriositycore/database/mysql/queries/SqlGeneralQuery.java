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
    UPDATE_ROW("UPDATE %s SET %s WHERE %s"),
    DELETE_ROW("DELETE FROM %s WHERE id = %s"),
    RESET_ID_INCREMENTS("ALTER TABLE %s AUTO_INCREMENT  = 1"),
    GET_TABLE_NAME("SELECT * from %s"),
    GET_TABLE_COLUMNS("SELECT column_name, data_type,character_maximum_length FROM information_schema.columns" +
            " WHERE table_schema = %s AND table_name = %s"),
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

