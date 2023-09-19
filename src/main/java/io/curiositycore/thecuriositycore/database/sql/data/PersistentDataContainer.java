package io.curiositycore.thecuriositycore.database.sql.data;

import io.curiositycore.thecuriositycore.database.sql.data.FormattedData;

public interface PersistentDataContainer {
    FormattedData[] getFormattedData();
    String getDataTypesAsString();
}
