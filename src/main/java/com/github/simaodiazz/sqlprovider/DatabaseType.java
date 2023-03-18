package com.github.simaodiazz.sqlprovider;

import java.util.Arrays;

/**
 * Enum para representar o tipo de banco de dados suportado.
 */
public enum DatabaseType {

    MYSQL("MySQL"),
    SQLITE("SQLite");

    private final String name;

    /**
     * Construtor para criar uma instância de {@code DatabaseType}.
     *
     * @param name o nome do tipo de banco de dados
     */
    DatabaseType(String name) {
        this.name = name;
    }

    /**
     * Retorna o nome do tipo de banco de dados.
     *
     * @return o nome do tipo de banco de dados
     */
    public String getName() {
        return name;
    }

    /**
     * Obtém o {@code DatabaseType} correspondente ao nome fornecido.
     *
     * @param name o nome do tipo de banco de dados
     * @return o {@code DatabaseType} correspondente, ou uma exceção se não houver correspondência
     * @throws IllegalArgumentException se o nome fornecido não corresponder a nenhum tipo de banco de dados
     */
    public static DatabaseType fromName(String name) {
        return Arrays.stream(values())
                .filter(database -> database.getName().equalsIgnoreCase(name))
                .findFirst()
                .orElseThrow(() -> new IllegalArgumentException("Unknown database type: " + name));
    }
}