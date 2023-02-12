package com.github.simaodiazz.sqlprovider;

import com.zaxxer.hikari.HikariDataSource;

import java.sql.Connection;

public interface Database {

    /**
     * @return get database's type
     */
    DatabaseType getDatabaseType();

    /**
     * @return get database connection
     */
    Connection getConnection();

    /**
     * @return get datasource of hikaricp
     */
    HikariDataSource getDataSource();

}