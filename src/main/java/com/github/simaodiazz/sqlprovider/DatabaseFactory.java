package com.github.simaodiazz.sqlprovider;

import com.github.simaodiazz.sqlprovider.executors.SimpleStatement;
import com.github.simaodiazz.sqlprovider.provider.MySQL;
import com.github.simaodiazz.sqlprovider.provider.SQLite;

import java.io.File;
import java.sql.SQLException;

public class DatabaseFactory {

    private Database database;
    private final DatabaseCredentials databaseCredentials;

    public DatabaseFactory() {
        this.databaseCredentials = new DatabaseCredentials(DatabaseType.SQLITE, "root", "null", "localhost:3006", "default", new File("database.sql"));
    }

    public DatabaseFactory(DatabaseCredentials databaseCredentials) {
        this.databaseCredentials = databaseCredentials;
    }

    public void connect() {
        switch (databaseCredentials.getDatabaseType()) {
            case MYSQL:
                database = new MySQL(databaseCredentials);
            case SQLITE:
                database = new SQLite(databaseCredentials);
        }
    }

    public void disconnect() {
        try {
            database.getConnection().close();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public SimpleStatement execute(String command) {
        try {
            return new SimpleStatement(database.getConnection().prepareStatement(command));
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return null;
    }
}