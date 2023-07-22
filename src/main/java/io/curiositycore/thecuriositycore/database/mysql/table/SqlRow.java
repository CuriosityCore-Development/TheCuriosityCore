package io.curiositycore.thecuriositycore.database.mysql.table;

import lombok.Getter;
import lombok.Setter;

/**
 * A single row within a {@linkplain BaseSqlTable SQL Table}.<p> This allows the data of the table to be
 * more easily readable to the end user. This allows appending, addition and removal of rows from a table without the
 * need to expose the user to the SQL query format.
 */
@Getter @Setter
public class SqlRow {
    /**
     * The data of a single row. Done as an object array to allow for multiple data types within said row.
     */
    Object[] rowData;
    /**
     * The index of the row within the table.
     */
    int rowIndex;

    /**
     * Constructor which initialises the row's data and index in the table.
     * @param rowData The data of a single row.
     * @param rowIndex The index of a row within the table.
     */
    public SqlRow(Object[] rowData, int rowIndex){
        this.rowData = rowData;
        this.rowIndex = rowIndex;
    }

}
