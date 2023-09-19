package io.curiositycore.thecuriositycore.database.sql.table.model;

import io.curiositycore.thecuriositycore.database.Credentials;
import io.curiositycore.thecuriositycore.database.caching.CacheReason;
import io.curiositycore.thecuriositycore.database.sql.data.FormattedData;
import io.curiositycore.thecuriositycore.database.sql.data.PersistentDataContainer;

public abstract class BaseSqlRow implements SqlRow{
    private int rowIndex;
    private PersistentDataContainer rowContents;
    private CacheReason cacheReason;
    protected BaseSqlRow(FormattedData[] rowData){
        this.rowContents = initRowContents(rowData);
    }
    @Override
    public int getRowIndex() {
        return this.rowIndex;
    }

    @Override
    public PersistentDataContainer getRowContents() {
        return this.rowContents;
    }

    @Override
    public void alterRowContents(FormattedData updatedRowValue, int columnNumber) {
        FormattedData[] rowData = this.rowContents.getFormattedData();
        if(rowData[columnNumber-1].correctDataKey(updatedRowValue)){
            rowData[columnNumber-1] = updatedRowValue;
        }
    }
    protected abstract PersistentDataContainer initRowContents(FormattedData[] rowData);

    @Override
    public void setCacheReason(CacheReason cacheReason) {
        this.cacheReason = cacheReason;
    }

    @Override
    public void queryDatabaseWithCache(Credentials databaseCredentials, String tableName) {
        this.cacheReason.queryDatabase(databaseCredentials,this.rowContents.getFormattedData(),tableName);
    }
}
