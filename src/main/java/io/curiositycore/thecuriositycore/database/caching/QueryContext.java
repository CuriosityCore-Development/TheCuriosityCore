package io.curiositycore.thecuriositycore.database.caching;

import io.curiositycore.thecuriositycore.database.Credentials;
import io.curiositycore.thecuriositycore.database.sql.data.FormattedData;

import java.sql.Connection;

public interface QueryContext {
    void queryDatabase(Credentials credentials, FormattedData[] formattedData, String tableName);
}
