package io.curiositycore.thecuriositycore.database.caching;

import java.util.Set;

public interface DataCache<T extends Cacheable> {
    void clearCache();
    void add(T cacheableDataToAdd, CacheReason cacheReason);
    Set<T> getCachedData();

}
