package com.github.simaodiazz.sqlprovider;

import java.io.IOException;

public class DatabaseSession {

    public DatabaseSession() {
    }

    public void begin(Database database) {
        database.getConnection();
    }
}
