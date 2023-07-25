package com.github.simaodiazz.sqlprovider.provider;

import com.github.simaodiazz.sqlprovider.Database;
import com.github.simaodiazz.sqlprovider.DatabaseCredentials;
import com.github.simaodiazz.sqlprovider.DatabaseType;

import java.io.File;
import java.io.IOException;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class SQLite extends Database {

    // Constructor for the MySQL class, calling the constructor of the parent Database class
    public SQLite() {
        super();
    }

    // Override the config method to set up the database connection using the provided credentials
    @Override
    public void config(DatabaseCredentials credentials) {

        // Verify if the file for storage exists in base folder
        File file = new File(credentials.getFile());
        if (!file.exists()) {
            try {
                file.createNewFile();
            } catch (IOException e) {
                throw new RuntimeException(e);
            }
        }

        // Set the HikariCP data source class name to the one provided in credentials
        this.getHikari().setJdbcUrl("jdbc:sqlite:" + credentials.getFile());

        // Set auto-commit to true for the connection
        this.getHikari().setAutoCommit(true);

        // Set the maximum pool size to 20
        this.getHikari().setMaximumPoolSize(20);
    }

    // Override the open method to establish the database connection
    @Override
    public void open() {
        // Call the getConnection method of the parent Database class to establish the connection
        this.getConnection();
    }

    @Override
    public DatabaseType getType() {
        return DatabaseType.SQLITE;
    }
}