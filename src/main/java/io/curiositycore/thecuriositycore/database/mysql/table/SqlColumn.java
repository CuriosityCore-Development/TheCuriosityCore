package io.curiositycore.thecuriositycore.database.mysql.table;

import io.curiositycore.thecuriositycore.database.mysql.queries.SqlDataTypes;
import lombok.Getter;

/**
 * A single column within a {@linkplain BaseSqlTable SQL Table}.<p> This allows the input of new data into a table to be
 * more easily executed end user. This allows each value added to a table to be checked by the column's specific data
 * type to ensure both type safety and an extra security measure to mitigate sql injection.
 */
@Getter
public class SqlColumn {
    /**
     * The name of the column.
     */
    String columnName;

    /**
     * The data type of values within this column.
     */
    SqlDataTypes dataType;

    /**
     * Constructor which initialises the name and data type of the column.
     * @param columnName The name of the column.
     * @param dataType The data type of values within this column.
     */
    public SqlColumn(String columnName, SqlDataTypes dataType){
        this.columnName = columnName;
        this.dataType = dataType;
    }
}
