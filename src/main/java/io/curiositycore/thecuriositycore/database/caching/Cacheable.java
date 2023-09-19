package io.curiositycore.thecuriositycore.database.caching;

import io.curiositycore.thecuriositycore.database.Credentials;
import io.curiositycore.thecuriositycore.database.sql.data.FormattedData;

public interface Cacheable {
    void setCacheReason(CacheReason cacheReason);
    void queryDatabaseWithCache(Credentials databaseCredentials, String tableName);
}
