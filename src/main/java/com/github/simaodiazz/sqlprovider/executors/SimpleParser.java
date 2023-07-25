package com.github.simaodiazz.sqlprovider.executors;

public interface SimpleParser<Model, Data> {

    Data parse(Model model);

    Model unparse(Data data);

}
