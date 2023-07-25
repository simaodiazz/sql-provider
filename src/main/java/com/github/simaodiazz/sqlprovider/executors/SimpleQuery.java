package com.github.simaodiazz.sqlprovider.executors;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class SimpleQuery implements AutoCloseable {

    private final ResultSet resultSet;

    public SimpleQuery(ResultSet resultSet) {
        this.resultSet = resultSet;
    }

    public Object getObject(String column) {
        try {
            if (resultSet.isBeforeFirst()) {
                throw new SQLException("ResultSet hasn't any result, use next() to search first result!");
            }
            return resultSet.getObject(column);
        } catch (SQLException exception) {
            exception.printStackTrace();
        }
        return null;
    }

    public String getString(String column) {
        return (String) getObject(column);
    }

    public Double getDouble(String column) {
        return (Double) getObject(column);
    }

    public Float getFloat(String column) {
        return (Float) getObject(column);
    }

    public Integer getInteger(String column) {
        return (Integer) getObject(column);
    }

    public Short getShort(String column) {
        return (Short) getObject(column);
    }

    public Byte getByte(String column) {
        return (Byte) getObject(column);
    }

    public Long getLong(String column) {
        return (Long) getObject(column);
    }

    public Boolean getBoolean(String column) {
        return (Boolean) getObject(column);
    }

    public boolean next() {
        try {
            return this.resultSet.next();
        } catch (SQLException exception) {
            exception.printStackTrace();
        }
        return false;
    }

    public void close() {
        try {
            resultSet.close();
        } catch (SQLException exception) {
            exception.printStackTrace();
        }
    }
}