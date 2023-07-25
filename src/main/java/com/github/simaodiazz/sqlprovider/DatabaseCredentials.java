package com.github.simaodiazz.sqlprovider;

import lombok.Builder;
import lombok.Data;

@Builder
@Data
public class DatabaseCredentials {

    private final DatabaseType type;
    private final String driver;
    private final String user;
    private final String password;
    private final String host;
    private final String port;
    private final String database;
    private final String file;

}