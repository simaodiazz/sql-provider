package com.github.simaodiazz.sqlprovider.executors;

// Esta interface define um adaptador genérico
public interface SimpleAdapter<T> {

    // Este método é responsável por adaptar o resultado de uma consulta (representado por um objeto SimpleQuery)
    // em um objeto do tipo especificado por T
    T adaptResult(SimpleQuery simpleQuery);
}
