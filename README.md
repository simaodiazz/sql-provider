# SQL Provider

SQLProvider é uma solução completa e fácil de usar para conexões e operações com diferentes tipos de banco de dados. 
Com ele, você pode se concentrar na construção de suas funcionalidades, sem se preocupar com a complexidade da conexão e gerenciamento de banco de dados.

A biblioteca usa o popular framework de conexão HikariCP, garantindo alta performance e escalabilidade em suas aplicações. 
Além disso, o uso é simples e intuitivo, permitindo que você comece a trabalhar rapidamente com seus bancos de dados.

Em resumo, o provedor de SQL é a escolha ideal para quem procura uma solução rápida, eficiente e fácil de usar para trabalhar com bancos de dados em Java. 
Comece a usá-lo agora e experimente a diferença!
<br><br>
```java
package com.github.simaodiazz.sqlprovider;

import com.github.simaodiazz.sqlprovider.executors.SimpleQuery;
import com.github.simaodiazz.sqlprovider.executors.SimpleStatement;

import java.io.File;
import java.sql.PreparedStatement;

public class Example {

    private static final DatabaseCredentials databaseCredentials = new DatabaseCredentials(
            DatabaseType.SQLITE,
            "root",
            "",
            "localhost:3306",
            "default",
            new File("database.sql"));

    public static void main(String[] args) {
        DatabaseFactory databaseFactory = new DatabaseFactory(databaseCredentials);
        databaseFactory.connect();
        
        try (SimpleStatement simpleStatement = databaseFactory.execute("CREATE TABLE IF NOT EXISTS test (playerName VARCHAR(16) NOT NULL)")) {
            simpleStatement.executeUpdate();
        }

        try (SimpleStatement simpleStatement = databaseFactory.execute("SELECT * FROM test")) {
            try (SimpleQuery simpleQuery = simpleStatement.executeQuery()) {
                while (simpleQuery.next()) System.out.println(simpleQuery.getString("playerName"));
            }
        }
    }
}
```
