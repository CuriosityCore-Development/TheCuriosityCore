package io.curiositycore.thecuriositycore.configurations.test.configs.enums;

import io.curiositycore.thecuriositycore.configurations.interfaces.ConfigCache;

public enum ConfigCache1 implements ConfigCache {
    CACHE_1("Cache_1");


    final String cacheName;

    ConfigCache1(String cacheName){
        this.cacheName = cacheName;
    }
    @Override
    public String getName() {
        return this.cacheName;
    }
}
