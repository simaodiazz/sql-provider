package com.github.simaodiazz.sqlprovider;

import com.github.simaodiazz.sqlprovider.executors.SimpleExecutor;
import com.github.simaodiazz.sqlprovider.executors.SimpleStatement;
import com.zaxxer.hikari.HikariDataSource;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;

public abstract class Database {

    private final HikariDataSource hikari;

    private final DatabaseCredentials credentials;

    public Database() {
        // Initialize an instance of HikariDataSource, which is the connection pool
        this.hikari = new HikariDataSource();
        // Initialize an instance of DatabaseCredentials, which is the configuration of connection
        this.credentials = DatabaseCredentials.builder().build();
    }

    // Abstract method to configure the database connection properties
    public abstract void config(DatabaseCredentials credentials);

    // Abstract method to open the database connection
    public abstract void open();

    public abstract DatabaseType getType();

    // Method to get a connection from the connection pool (try-with-resources is used to ensure proper connection closing)
    public Connection getConnection() {
        try (Connection connection = this.hikari.getConnection()) {
            return connection;
        } catch (SQLException e) {
            // In case of an error while getting the connection, throw an exception
            throw new RuntimeException(e);
        }
    }

    public SimpleExecutor execute(String command) {
        try (PreparedStatement preparedStatement = this.getConnection().prepareStatement(command)) {
            try (SimpleStatement statement = new SimpleStatement(preparedStatement)) {
                return SimpleExecutor.of(statement);
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    // Method to get a instance of credentials
    public DatabaseCredentials getCredentials() {
        return credentials;
    }

    // Method to get a instance of hikari
    public HikariDataSource getHikari() {
        return hikari;
    }
}
