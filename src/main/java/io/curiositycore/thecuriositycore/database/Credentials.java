package io.curiositycore.thecuriositycore.database;

import org.bukkit.configuration.ConfigurationSection;

import java.sql.Connection;

public interface Credentials<T extends Exception> {
    Connection getConnection() throws T;
    void setCredentials(ConfigurationSection credentialsSection);

}
