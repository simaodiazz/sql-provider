package com.github.simaodiazz.sqlprovider.executors;

import lombok.Data;

import java.util.LinkedHashSet;
import java.util.Set;
import java.util.concurrent.atomic.AtomicReference;
import java.util.function.Consumer;
import java.util.function.Function;

@Data(staticConstructor = "of")
public class SimpleExecutor {

    private final SimpleStatement statement;

    public void executeUpdate(Consumer<SimpleStatement> statementConsumer) {
        statementConsumer.accept(statement);
        statement.executeUpdate();
    }

    public <T> T executeQuery(Consumer<SimpleStatement> statementConsumer, Function<SimpleQuery, T> resultFunction) {

        AtomicReference<T> value = new AtomicReference<>();

        statementConsumer.accept(statement);

        try (SimpleQuery resultSet = statement.executeQuery()) {
            value.set(resultFunction.apply(resultSet));
        } catch (Exception e) {
            e.printStackTrace();
        }

        return value.get();
    }

    public <T> T executeSingleResultQuery(Consumer<SimpleStatement> statementConsumer, SimpleAdapter<T> adapter) {
        return executeQuery(statementConsumer, resultSet -> {
            if (resultSet.next()) {
                return adapter.adapt(resultSet);
            }
            return null;
        });
    }

    public <T> Set<T> executeMultipleResultQuery(Consumer<SimpleStatement> statementConsumer, SimpleAdapter<T> adapter) {
        return this.executeQuery(statementConsumer, resultSet -> {

            Set<T> elements = new LinkedHashSet<>();
            while (resultSet.next()) {
                elements.add(adapter.adapt(resultSet));
            }

            return elements;
        });
    }
}
