package com.github.simaodiazz.sqlprovider;

import com.github.simaodiazz.sqlprovider.provider.MySQL;
import com.github.simaodiazz.sqlprovider.provider.SQLite;

public class DatabaseFactory {

    public DatabaseFactory() {
    }

    public Database build(DatabaseCredentials credentials) {

        // Instance of default database
        Database database;

        // Verify is MySQL connection
        if (credentials.getType() == DatabaseType.MYSQL) {
            database = new MySQL();
            database.config(credentials);
            return database;
        }

        database = new SQLite();
        database.config(credentials);

        // Return of default database instance
        return database;
    }
}
