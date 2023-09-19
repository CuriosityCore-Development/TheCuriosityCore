package io.curiositycore.thecuriositycore.database.sql.table;

import io.curiositycore.thecuriositycore.database.caching.CacheReason;
import io.curiositycore.thecuriositycore.database.Credentials;
import io.curiositycore.thecuriositycore.database.sql.data.FormattedData;
import io.curiositycore.thecuriositycore.database.sql.data.PersistentDataContainer;
import io.curiositycore.thecuriositycore.database.sql.table.model.SqlColumn;
import io.curiositycore.thecuriositycore.database.sql.table.model.SqlColumns;
import io.curiositycore.thecuriositycore.database.sql.table.model.SqlRow;
import io.curiositycore.thecuriositycore.database.sql.table.model.SqlRowCache;
import io.curiositycore.thecuriositycore.database.util.SqlQueryHandler;
import io.curiositycore.thecuriositycore.database.util.exceptions.SqlRowInvalidException;

import java.util.HashSet;
import java.util.Set;

public abstract class MySqlTable implements Table{
    private final String tableName;
    private String tableSchema;
    private Set<SqlRow> tableRowSet = new HashSet<>();
    private SqlRowCache sqlRowCache = new SqlRowCache();
    private SqlColumns tableColumns;
    protected MySqlTable(){
        this.tableName = initTableName();
        this.tableColumns = initSqlColumns();
    }
    @Override
    public String getTableName() {
        return this.tableName;
    }

    @Override
    public String getTableSchema() {
        return this.tableSchema;
    }

    @Override
    public void addNewRow(PersistentDataContainer rowData) {
        if(!this.tableColumns.correctRowFormat(rowData)){
            throw new SqlRowInvalidException(rowData,this.tableColumns);
        }
        SqlRow rowToAdd = constructRowFromData(rowData);
        this.sqlRowCache.add(rowToAdd, CacheReason.ADDITION);
        this.tableRowSet.add(rowToAdd);
    }

    @Override
    public SqlColumn[] getTableColumns() {
        return this.tableColumns.getColumns();
    }

    @Override
    public void addExistingRowSet(Set<SqlRow> setToAdd) {
        this.tableRowSet.addAll(setToAdd);
    }

    @Override
    public void updateRow(int rowIndex, int columnIndex, FormattedData updatedData) {
        SqlRow rowToUpdate = getRowFromSet(rowIndex);
        rowToUpdate.alterRowContents(updatedData,columnIndex);
        this.sqlRowCache.add(rowToUpdate,CacheReason.UPDATE);

    }

    @Override
    public void removeRow(int rowIndex) {
        SqlRow rowToRemove = getRowFromSet(rowIndex);
        this.sqlRowCache.add(rowToRemove,CacheReason.REMOVAL);
        this.tableRowSet.remove(getRowFromSet(rowIndex));
    }

    @Override
    public void createTableInDatabase(Credentials databaseCredentials) {
        SqlQueryHandler.createTable(databaseCredentials,this);
    }

    @Override
    public void updateToDatabase(Credentials databaseCredentials) {
        this.sqlRowCache.getCachedData().forEach(sqlRow -> sqlRow.queryDatabaseWithCache(databaseCredentials, this.tableName));
        this.sqlRowCache.clearCache();
    }
    protected SqlRow getRowFromSet(int rowIndex){
        return this.tableRowSet.stream()
                .filter(sqlRow -> sqlRow
                        .getRowIndex() == rowIndex)
                .findFirst().orElseThrow();
    }
    protected abstract String initTableName();
    protected abstract SqlColumns initSqlColumns();
    protected abstract SqlRow constructRowFromData(PersistentDataContainer rowData);
}
