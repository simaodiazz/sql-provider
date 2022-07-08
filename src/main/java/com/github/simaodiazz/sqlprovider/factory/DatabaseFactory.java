package com.github.simaodiazz.sqlprovider.factory;

import com.github.simaodiazz.sqlprovider.Database;
import com.github.simaodiazz.sqlprovider.DatabaseType;
import com.github.simaodiazz.sqlprovider.exceptions.DatabaseConnectException;
import com.github.simaodiazz.sqlprovider.exceptions.DatabaseDisconnectException;
import com.github.simaodiazz.sqlprovider.executor.SimpleStatement;
import com.github.simaodiazz.sqlprovider.factory.provider.MariaDB;
import com.github.simaodiazz.sqlprovider.factory.provider.MySQL;
import com.github.simaodiazz.sqlprovider.factory.provider.PostgresSQL;
import com.github.simaodiazz.sqlprovider.factory.provider.SQLite;
import lombok.Data;
import lombok.SneakyThrows;
import org.jetbrains.annotations.Contract;
import org.jetbrains.annotations.NotNull;

import java.io.File;
import java.io.IOException;
import java.sql.SQLException;

@Data
public class DatabaseFactory {

    private Database databaseProvider;
    private DatabaseType databaseType;
    private String user;
    private String password;
    private String host;
    private String database;
    private File file;
    private String errorMessage;

    public DatabaseFactory() { }
    
    public DatabaseFactory(@NotNull DatabaseType databaseType) {
        this.databaseType = databaseType;
    }
    
    @Contract(value = " -> new", pure = true)
    public static @NotNull DatabaseFactory of() {
        return new DatabaseFactory();
    }

    @Contract(value = "_ -> new", pure = true)
    public static @NotNull DatabaseFactory of(@NotNull DatabaseType databaseType) {
        return new DatabaseFactory(databaseType);
    }

    public void connect() throws DatabaseConnectException {
        try {
            switch (databaseType) {
                case MARIADB:
                    databaseProvider = new MariaDB(user, password, host, database);
                case MYSQL:
                    databaseProvider = new MySQL(user, password, host, database);
                case POSTGRESQL:
                    databaseProvider = new PostgresSQL(user, password, host, database);
                case SQLITE:
                    databaseProvider = new SQLite(file);
            }
        } catch (SQLException | ClassNotFoundException | IOException ignored) {
            throw new DatabaseConnectException(
              errorMessage == null
                ? databaseType.getName()
                : errorMessage
            );
        }
    }

    @SneakyThrows
    public SimpleStatement prepareStatement(@NotNull String query) {
        return SimpleStatement.of(databaseProvider.getConnection().prepareStatement(query));
    }

    public void disconnect() throws DatabaseDisconnectException {
        try {
            databaseProvider.getConnection().close();
        } catch (SQLException exception) {
            throw new DatabaseDisconnectException(exception.getMessage());
        }
    }
}
