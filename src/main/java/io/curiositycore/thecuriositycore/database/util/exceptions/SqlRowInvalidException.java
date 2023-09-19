package io.curiositycore.thecuriositycore.database.util.exceptions;

import io.curiositycore.thecuriositycore.database.sql.data.PersistentDataContainer;
import io.curiositycore.thecuriositycore.database.sql.table.model.SqlColumns;

public class SqlRowInvalidException extends RuntimeException{
    String errorMessage;
    public SqlRowInvalidException(PersistentDataContainer persistentDataContainer, SqlColumns sqlColumns){
        this.errorMessage = "Row data: "+ persistentDataContainer.getDataTypesAsString() + ", Expected: "
                + sqlColumns.getColumnDataTypesAsString();

    }
    @Override
    public String getMessage() {
        return this.errorMessage;
    }
}
