package io.curiositycore.thecuriositycore.database.sql;

import io.curiositycore.thecuriositycore.database.Credentials;
import io.curiositycore.thecuriositycore.database.Database;
import io.curiositycore.thecuriositycore.database.DatabaseManager;

import java.util.HashMap;
import java.util.Map;
//TODO debate use of this manager in lieu of wrapping everything into SqlTableManager.
public class BaseSqlDatabaseManager implements DatabaseManager<SqlDatabase> {
    private Credentials credentials;
    private Map<String,SqlDatabase> databaseMap = new HashMap<>();

    @Override
    public void setCredentials(Credentials credentialsToSet) {
        this.credentials = credentialsToSet;
    }

    @Override
    public void register(SqlDatabase databaseToRegister) {
        databaseToRegister.setCredentials(this.credentials);

        this.databaseMap.put(databaseToRegister.getDatabaseName(),databaseToRegister);
    }

    @Override
    public void unregister(String databaseName) {
        this.databaseMap.remove(databaseName);
    }

    @Override
    public void updateDatabase(String nameOfDatabaseToUpdate) {
        this.databaseMap.get(nameOfDatabaseToUpdate).updateDatabase();
    }
}
