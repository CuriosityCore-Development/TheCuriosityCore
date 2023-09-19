package io.curiositycore.thecuriositycore.database.sql;

import io.curiositycore.thecuriositycore.database.Credentials;
import io.curiositycore.thecuriositycore.database.Database;
import io.curiositycore.thecuriositycore.database.sql.table.Table;

import java.util.Set;

public interface SqlDatabase extends Database {
    void setCredentials(Credentials credentialsToSet);
}
