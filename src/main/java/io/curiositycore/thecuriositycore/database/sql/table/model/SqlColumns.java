package io.curiositycore.thecuriositycore.database.sql.table.model;

import io.curiositycore.thecuriositycore.database.sql.data.PersistentDataContainer;

public interface SqlColumns {
    boolean correctRowFormat(PersistentDataContainer potentialRowData);
    String getColumnDataTypesAsString();
    SqlColumn[] getColumns();

}
