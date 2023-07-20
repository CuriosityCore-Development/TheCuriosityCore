package io.curiositycore.thecuriositycore.database.mysql.table;

import lombok.Getter;
import lombok.Setter;

/**
 * Abstract that represents the generalisation of a single row within a {@linkplain
 * io.curiositycore.thecuriositycore.database.mysql.table.BaseTable SQL Table}.<p> This allows the data of the table to be
 * more easily readable to the end user. This allows appending, addition and removal of rows from a table without the
 * need to expose the user to the SQL query format.
 */
@Getter @Setter
public abstract class BaseRow {
    /**
     * The data of a single row. Done as an object array to allow for multiple data types within said row.
     */
    Object[] rowData;
    /**
     * The index of the row within the table.
     */
    int rowIndex;

}
