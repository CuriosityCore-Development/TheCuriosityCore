package io.curiositycore.thecuriositycore.database.model;

import com.mysql.cj.jdbc.MysqlDataSource;
import lombok.Getter;

public abstract class Credentials {
    @Getter
    MysqlDataSource dataSource;
    public Credentials(){
        this.dataSource = getDataSource();
    }

    protected abstract MysqlDataSource getDataSource();
}
