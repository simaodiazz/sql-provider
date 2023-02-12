package com.github.simaodiazz.sqlprovider;

import lombok.AllArgsConstructor;
import lombok.Data;

import java.io.File;

@AllArgsConstructor
@Data
public class DatabaseCredentials {

    private DatabaseType databaseType;
    private String user;
    private String password;
    private String host;
    private String database;
    private File file;

}