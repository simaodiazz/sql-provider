package com.github.simaodiazz.sqlprovider.executors;

public interface SimpleAdapter<T> {

    T adapt(SimpleQuery query);
}