package io.curiositycore.thecuriositycore.database.sql.table;

import com.mysql.cj.jdbc.MysqlDataSource;
import io.curiositycore.thecuriositycore.database.Credentials;
import org.bukkit.configuration.ConfigurationSection;

import java.sql.Connection;
import java.sql.SQLException;

public class SqlCredentials implements Credentials<SQLException> {
    protected MysqlDataSource credentialsSource = new MysqlDataSource();;
    @Override
    public Connection getConnection() throws SQLException {
        return this.credentialsSource.getConnection();
    }

    @Override
    public void setCredentials(ConfigurationSection credentialsSection) {
        this.credentialsSource.setServerName(credentialsSection.getString("host"));
        this.credentialsSource.setPort(credentialsSection.getInt("port"));
        this.credentialsSource.setDatabaseName(credentialsSection.getString("databaseName"));
        this.credentialsSource.setUser(credentialsSection.getString("username"));
        this.credentialsSource.setPassword(credentialsSection.getString("password"));
    }


}
