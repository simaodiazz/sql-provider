package com.github.simaodiazz.sqlprovider.executors;

import com.github.simaodiazz.sqlprovider.Database;
import lombok.Getter;
import lombok.RequiredArgsConstructor;

import java.util.LinkedHashSet;
import java.util.Set;
import java.util.concurrent.atomic.AtomicReference;
import java.util.function.Consumer;
import java.util.function.Function;

@Getter
@RequiredArgsConstructor
public class SimpleExecutor {

    private final Database database;

    public void executeUpdate(String query, Consumer<SimpleStatement> statementConsumer) {
        try (SimpleStatement statement = database.execute(query)) {
            statementConsumer.accept(statement);
            statement.executeUpdate();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public <T> T executeQuery(String query, Consumer<SimpleStatement> statementConsumer, Function<SimpleQuery, T> resultFunction) {

        AtomicReference<T> value = new AtomicReference<>();

        try (SimpleStatement statement = database.execute(query)) {
            statementConsumer.accept(statement);

            try (SimpleQuery resultSet = statement.executeQuery()) {
                value.set(resultFunction.apply(resultSet));
            } catch (Exception e) {
                e.printStackTrace();
            }

        } catch (Exception e) {
            e.printStackTrace();
        }

        return value.get();
    }

    public <T> T executeSingleResultQuery(String query, Consumer<SimpleStatement> statementConsumer, SimpleAdapter<T> adapter) {
        return executeQuery(query, statementConsumer, resultSet -> {
            if (resultSet.next()) {
                return adapter.adapt(resultSet);
            }
            return null;
        });
    }

    public <T> Set<T> executeMultipleResultQuery(String query, Consumer<SimpleStatement> statementConsumer, SimpleAdapter<T> adapter) {
        return this.executeQuery(query, statementConsumer, resultSet -> {

            Set<T> elements = new LinkedHashSet<>();
            while (resultSet.next()) {
                elements.add(adapter.adapt(resultSet));
            }

            return elements;
        });
    }
}
