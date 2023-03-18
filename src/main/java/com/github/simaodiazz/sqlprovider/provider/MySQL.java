package com.github.simaodiazz.sqlprovider.provider;

import com.github.simaodiazz.sqlprovider.Database;
import com.github.simaodiazz.sqlprovider.DatabaseCredentials;
import com.github.simaodiazz.sqlprovider.DatabaseType;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class MySQL implements Database {

    private final Connection connection;

    public MySQL(DatabaseCredentials databaseCredentials) throws ClassNotFoundException, SQLException {
        Class.forName("org.mysql.jdbc.Driver");
        connection = DriverManager.getConnection(String.format("jdbc:mysql://%s/%s", databaseCredentials.getHost(), databaseCredentials.getDatabase()), databaseCredentials.getUser(), databaseCredentials.getPassword());
    }

    @Override
    public DatabaseType getDatabaseType() {
        return DatabaseType.MYSQL;
    }

    @Override
    public Connection getConnection() {
        return connection;
    }
}