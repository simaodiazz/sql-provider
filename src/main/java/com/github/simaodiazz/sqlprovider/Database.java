package com.github.simaodiazz.sqlprovider;

import java.sql.Connection;

public interface Database {

    /**
     * @return get database's type
     */
    DatabaseType getDatabaseType();

    /**
     * @return get database connection
     */
    Connection getConnection();

}