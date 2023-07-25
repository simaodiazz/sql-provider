package com.github.simaodiazz.sqlprovider.executors;

public interface SimpleAdapter<T> {

    T adaptResult(SimpleQuery simpleQuery);

}
