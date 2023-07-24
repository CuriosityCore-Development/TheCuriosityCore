package io.curiositycore.thecuriositycore.database.mysql;

import io.curiositycore.thecuriositycore.database.mysql.table.BaseSqlTable;

import java.util.HashMap;
import java.util.Map;

/**
 * Class responsible for retrieving and registering {@linkplain
 * io.curiositycore.thecuriositycore.database.mysql.table.BaseSqlTable SQL Tables}.
 */
public abstract class SqlTableManager{
    /**
     * Map of SQL tables that are retrieved using their classes as keys.
     */
    protected Map<Class<?>, BaseSqlTable> sqlTableMap = new HashMap<>();

    /**
     * Registers a table to the manager.
     * @param baseSqlTable The table to register.
     */
    public void registerTable(BaseSqlTable baseSqlTable){
        this.sqlTableMap.put(baseSqlTable.getClass(),baseSqlTable);
    }

    /**
     * Unregister a table to the manager.
     * @param tableClass The class of the table to unregister.
     */
    public void unregisterTable(Class<?> tableClass){
        this.sqlTableMap.remove(tableClass);
    }

    /**
     * Get a table from the manager. Uses generics to grab the cast version of the BaseSqlTable that is retrieved.
     * @param tableClass The class of the table to retrieve.
     */
    public<T extends BaseSqlTable> T getTable(Class<T> tableClass){
       return tableClass.cast(this.sqlTableMap.get(tableClass));
    }


}
