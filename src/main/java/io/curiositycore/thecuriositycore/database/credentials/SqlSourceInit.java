package io.curiositycore.thecuriositycore.database.credentials;

import com.mysql.cj.jdbc.MysqlDataSource;
import org.bukkit.configuration.file.FileConfiguration;

import java.sql.Connection;
import java.sql.SQLException;

/**
 * Utility class used to initialise the datasource for a SQL database. Doing so by grabbing connection credentials from
 * a designated configuration file for the plugin.
 */
public abstract class SqlSourceInit {
    /**
     * Get a new data source from a specified configuration file.
     * @param configurationFile The configuration file containing the credential data.
     * @return The data source of the requested database.
     * @throws SQLException Exception that throws if a connection is not successfully made.
     */
    public MysqlDataSource getNewDataSource(FileConfiguration configurationFile) throws SQLException {
        MysqlDataSource dataSource = new MysqlDataSource();
        dataSource.setServerName(setHost(configurationFile));
        dataSource.setPort(setPort(configurationFile));
        dataSource.setDatabaseName(setDatabase(configurationFile));
        dataSource.setUser(setUsername(configurationFile));
        dataSource.setPassword(setPassword(configurationFile));
        if(!dataSourceCanConnect(dataSource)){
            throw new SQLException("Could not establish database connection.");
        }
        return dataSource;
    }

    /**
     * Checks to see if a data source can connect to its relevant database.
     * @param dataSourceToTest The datasource to check.
     * @return True if the datasource can be utilized to connect successfully, false if it cannot.
     */
    protected boolean dataSourceCanConnect(MysqlDataSource dataSourceToTest){
        try (Connection conn = dataSourceToTest.getConnection()) {
            if (!conn.isValid(1)) {
                return false;
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        return true;
    }

    /**
     * Set the host parameter of the datasource.
     * @param configurationFile The configuration file containing the credential data.
     * @return The host parameter of the datasource.
     */
    protected abstract String setHost(FileConfiguration configurationFile);

    /**
     * Set the port parameter of the datasource.
     * @param configurationFile The configuration file containing the credential data.
     * @return The host parameter of the datasource.
     */
    protected abstract int setPort(FileConfiguration configurationFile);

    /**
     * Set the database (name) parameter of the datasource.
     * @param configurationFile The configuration file containing the credential data.
     * @return The database (name) parameter of the datasource.
     */
    protected abstract String setDatabase(FileConfiguration configurationFile);

    /**
     * Set the username parameter of the datasource.
     * @param configurationFile The configuration file containing the credential data.
     * @return The username parameter of the datasource.
     */
    protected abstract String setUsername(FileConfiguration configurationFile);

    /**
     * Set the password parameter of the datasource.
     * @param configurationFile The configuration file containing the credential data.
     * @return The password parameter of the datasource.
     */
    protected abstract String setPassword(FileConfiguration configurationFile);
}
