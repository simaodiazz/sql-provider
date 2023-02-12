package com.github.simaodiazz.sqlprovider.provider;

import com.github.simaodiazz.sqlprovider.Database;
import com.github.simaodiazz.sqlprovider.DatabaseCredentials;
import com.github.simaodiazz.sqlprovider.DatabaseType;
import com.zaxxer.hikari.HikariConfig;
import com.zaxxer.hikari.HikariDataSource;

import java.io.IOException;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class SQLite implements Database {

    private final HikariDataSource dataSource;

    public SQLite(DatabaseCredentials databaseCredentials) {

        try {
            Class.forName("org.sqlite.JDBC");
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }

        HikariConfig config = new HikariConfig();

        config.setJdbcUrl("jdbc:sqlite:" + databaseCredentials.getFile().getAbsolutePath());
        config.setUsername(databaseCredentials.getUser());
        config.setPassword(databaseCredentials.getPassword());
        config.addDataSourceProperty("cachePrepStmts", "true");
        config.addDataSourceProperty("prepStmtCacheSize", "250");
        config.addDataSourceProperty("prepStmtCacheSqlLimit", "2048");
        config.setMaximumPoolSize(20);
        config.setMinimumIdle(5);
        config.setIdleTimeout(30000);
        config.setConnectionTimeout(5000);
        config.setLeakDetectionThreshold(15000);
        config.setPoolName("SQLitePool");

        dataSource = new HikariDataSource(config);
    }

    @Override
    public DatabaseType getDatabaseType() {
        return DatabaseType.SQLITE;
    }

    @Override
    public Connection getConnection() {
        try {
            return dataSource.getConnection();
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return null;
    }

    @Override
    public HikariDataSource getDataSource() {
        return dataSource;
    }
}
