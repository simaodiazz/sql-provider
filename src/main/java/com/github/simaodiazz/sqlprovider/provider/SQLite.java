package com.github.simaodiazz.sqlprovider.provider;

import com.github.simaodiazz.sqlprovider.Database;
import com.github.simaodiazz.sqlprovider.DatabaseCredentials;
import com.github.simaodiazz.sqlprovider.DatabaseType;

import java.io.IOException;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class SQLite implements Database {

    private final Connection connection;

    public SQLite(DatabaseCredentials databaseCredentials) throws IOException, SQLException, ClassNotFoundException {
        if (!databaseCredentials.getFile().exists()) databaseCredentials.getFile().createNewFile();
        Class.forName("org.sqlite.JDBC");
        connection = DriverManager.getConnection(String.format("jdbc:sqlite:%s", databaseCredentials.getFile().getAbsolutePath()));
    }

    @Override
    public DatabaseType getDatabaseType() {
        return DatabaseType.SQLITE;
    }

    @Override
    public Connection getConnection() {
        return connection;
    }
}