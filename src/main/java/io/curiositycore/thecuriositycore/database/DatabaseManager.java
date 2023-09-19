package io.curiositycore.thecuriositycore.database;

import io.curiositycore.thecuriositycore.util.managers.Manager;

public interface DatabaseManager<T extends Database> extends Manager<T,String> {
    void setCredentials(Credentials credentialsToSet);
    void updateDatabase(String nameOfDatabaseToUpdate);
}
