package io.curiositycore.thecuriositycore.database.credentials;

import com.mysql.cj.jdbc.MysqlDataSource;
import org.bukkit.configuration.file.FileConfiguration;

import java.sql.Connection;
import java.sql.SQLException;
//TODO javadocs
public abstract class SQLSourceInit {
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
    protected abstract String setHost(FileConfiguration configurationFile);
    protected abstract int setPort(FileConfiguration configurationFile);
    protected abstract String setDatabase(FileConfiguration configurationFile);
    protected abstract String setUsername(FileConfiguration configurationFile);
    protected abstract String setPassword(FileConfiguration configurationFile);
}
