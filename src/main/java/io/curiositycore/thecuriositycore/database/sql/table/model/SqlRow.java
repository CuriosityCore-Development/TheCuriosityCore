package io.curiositycore.thecuriositycore.database.sql.table.model;

import io.curiositycore.thecuriositycore.database.caching.Cacheable;
import io.curiositycore.thecuriositycore.database.sql.data.FormattedData;
import io.curiositycore.thecuriositycore.database.sql.data.PersistentDataContainer;

public interface SqlRow extends Cacheable {
    int getRowIndex();
    PersistentDataContainer getRowContents();
    void alterRowContents(FormattedData updatedRowValue, int columnNumber);

}
