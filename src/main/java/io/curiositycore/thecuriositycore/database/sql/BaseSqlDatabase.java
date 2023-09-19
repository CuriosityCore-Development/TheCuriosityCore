package io.curiositycore.thecuriositycore.database.sql;

import io.curiositycore.thecuriositycore.database.Credentials;
import io.curiositycore.thecuriositycore.database.sql.table.Table;

import java.util.HashSet;
import java.util.Set;

public abstract class BaseSqlDatabase implements SqlDatabase{
    protected String databaseName;
    protected Set<Table> tableSet = new HashSet<>();
    protected Credentials<? extends Exception> databaseCredentials;
    protected BaseSqlDatabase(Credentials<? extends Exception> databaseCredentials){
        this.tableSet.addAll(initTables());
        this.databaseName = initDatabaseName();
        this.databaseCredentials = databaseCredentials;
        this.tableSet.forEach(table -> table.createTableInDatabase(this.databaseCredentials));
    }

    @Override
    public String getDatabaseName() {
        return this.databaseName;
    }

    @Override
    public void updateDatabase() {
        this.tableSet.forEach(table -> table.updateToDatabase(this.databaseCredentials));
    }

    @Override
    public void setCredentials(Credentials credentialsToSet) {
        this.databaseCredentials = credentialsToSet;
    }

    protected abstract String initDatabaseName();
    protected abstract Set<Table> initTables();
}
