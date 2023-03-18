package com.github.simaodiazz.sqlprovider.executors;

import java.sql.PreparedStatement;
import java.sql.SQLException;

public class SimpleStatement implements AutoCloseable {

    private final PreparedStatement preparedStatement;

    public SimpleStatement(PreparedStatement preparedStatement) {
        this.preparedStatement = preparedStatement;
    }

    public void setObject(int parameterIndex, Object value) {
        try {
            preparedStatement.setObject(parameterIndex, value);
        } catch (SQLException exception) {
            exception.printStackTrace();
        }
    }

    public void setString(int parameterIndex, String value) {
        try {
            preparedStatement.setString(parameterIndex, value);
        } catch (SQLException exception) {
            exception.printStackTrace();
        }
    }

    public void setDouble(int parameterIndex, Double value) {
        try {
            preparedStatement.setDouble(parameterIndex, value);
        } catch (SQLException exception) {
            exception.printStackTrace();
        }
    }

    public void setFloat(int parameterIndex, Float value) {
        try {
            preparedStatement.setFloat(parameterIndex, value);
        } catch (SQLException exception) {
            exception.printStackTrace();
        }
    }

    public void setInteger(int parameterIndex, Integer value) {
        try {
            preparedStatement.setInt(parameterIndex, value);
        } catch (SQLException exception) {
            exception.printStackTrace();
        }
    }

    public void setShort(int parameterIndex, Short value) {
        try {
            preparedStatement.setShort(parameterIndex, value);
        } catch (SQLException exception) {
            exception.printStackTrace();
        }
    }

    public void setByte(int parameterIndex, Byte value) {
        try {
            preparedStatement.setByte(parameterIndex, value);
        } catch (SQLException exception) {
            exception.printStackTrace();
        }
    }

    public void setLong(int parameterIndex, Long value) {
        try {
            preparedStatement.setLong(parameterIndex, value);
        } catch (SQLException exception) {
            exception.printStackTrace();
        }
    }

    public void setBoolean(int parameterIndex, Boolean value) {
        try {
            preparedStatement.setBoolean(parameterIndex, value);
        } catch (SQLException exception) {
            exception.printStackTrace();
        }
    }

    public void executeUpdate() {
        try {
            preparedStatement.executeUpdate();
        } catch (SQLException exception) {
            exception.printStackTrace();
        }
    }

    public SimpleQuery executeQuery() {
        try {
            return new SimpleQuery(preparedStatement.executeQuery());
        } catch (SQLException exception) {
            exception.printStackTrace();
        }
        return null;
    }

    public void close() {
        try {
            preparedStatement.close();
        } catch (SQLException exception) {
            exception.printStackTrace();
        }
    }
}