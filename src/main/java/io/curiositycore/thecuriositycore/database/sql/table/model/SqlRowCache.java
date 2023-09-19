package io.curiositycore.thecuriositycore.database.sql.table.model;

import io.curiositycore.thecuriositycore.database.caching.CacheReason;
import io.curiositycore.thecuriositycore.database.caching.DataCache;

import java.util.HashSet;
import java.util.Set;


public class SqlRowCache implements DataCache<SqlRow> {
    private Set<SqlRow> cachedRows = new HashSet<>();

    @Override
    public void clearCache() {
        this.cachedRows.clear();
    }

    @Override
    public void add(SqlRow sqlRowToAdd, CacheReason cacheReason) {
        sqlRowToAdd.setCacheReason(cacheReason);
        this.cachedRows.add(sqlRowToAdd);
    }

    @Override
    public Set<SqlRow> getCachedData(){
        return this.cachedRows;
    }


}
