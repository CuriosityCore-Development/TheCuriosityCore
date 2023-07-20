package io.curiositycore.thecuriositycore.database.mysql;

import lombok.Getter;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public enum SQLGeneralQuery {
    CREATE_TABLE("CREATE TABLE IF NOT EXISTS `%s` (id INT AUTO_INCREMENT, PRIMARY KEY(id));"),
    TABLE_APPEND("ALTER TABLE %s ADD %s %s;"),
    INSERT_TABLE_VALUE("INSERT INTO %s (%s) VALUES (%s)");
    @Getter
    private final String sql;

    SQLGeneralQuery(String sql) {
        this.sql = sql;
    }
}

