package com.github.simaodiazz.sqlprovider.provider;

import com.github.simaodiazz.sqlprovider.Database;
import com.github.simaodiazz.sqlprovider.DatabaseCredentials;
import com.github.simaodiazz.sqlprovider.DatabaseType;

public class MySQL extends Database {

    // Constructor for the MySQL class, calling the constructor of the parent Database class
    public MySQL() {
        super();
    }

    // Override the config method to set up the database connection using the provided credentials
    @Override
    public void config(DatabaseCredentials credentials) {
        // Set the HikariCP data source class name to the one provided in credentials
        this.getHikari().setDataSourceClassName(credentials.getDriver());

        // Set data source properties for the connection using the credentials
        this.getHikari().addDataSourceProperty("serverName", credentials.getHost());
        this.getHikari().addDataSourceProperty("port", credentials.getPort());
        this.getHikari().addDataSourceProperty("databaseName", credentials.getDatabase());
        this.getHikari().addDataSourceProperty("user", credentials.getUser());
        this.getHikari().addDataSourceProperty("password", credentials.getPassword());

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
        return DatabaseType.MYSQL;
    }
}
