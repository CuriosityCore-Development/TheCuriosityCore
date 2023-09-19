package io.curiositycore.thecuriositycore.database.caching;

import io.curiositycore.thecuriositycore.database.Credentials;
import io.curiositycore.thecuriositycore.database.sql.data.FormattedData;
import io.curiositycore.thecuriositycore.database.util.SqlQueryHandler;

public enum CacheReason implements QueryContext{
    ADDITION("") {
        @Override
        protected void executeQuery(Credentials credentials, FormattedData[] formattedData, String baseQuery) {
            SqlQueryHandler.updateTable(credentials,formattedData, baseQuery);
        }
    },
    REMOVAL("") {
        @Override
        protected void executeQuery(Credentials credentials, FormattedData[] formattedData, String baseQuery) {
            SqlQueryHandler.updateTable(credentials,formattedData, baseQuery);
        }
    },
    UPDATE("") {
        @Override
        protected void executeQuery(Credentials credentials, FormattedData[] formattedData, String baseQuery) {

        }
    };

    private String baseQuery;

    CacheReason(String baseQuery){
        this.baseQuery = baseQuery;
    }
    @Override
    public void queryDatabase(Credentials credentials, FormattedData[] formattedData, String tableName) {
        this.executeQuery(credentials,formattedData,String.format(baseQuery,tableName));
    }

    protected abstract void executeQuery(Credentials credentials, FormattedData[] formattedData, String tableName);
}
