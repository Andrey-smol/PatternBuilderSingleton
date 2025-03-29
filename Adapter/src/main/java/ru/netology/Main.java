package ru.netology;

import java.util.ArrayList;
import java.util.List;

public class Main {
    public static void main(String[] args) {

        List<String> list = new ArrayList<>(200);
        IStorage storage = new ListStorageAdapter(list);
        Logger logger = new Logger(new ErrorCapsDecorator(storage));

        logger.log("Hello");
        logger.log("error find");
        list.stream().forEach(System.out::println);
    }
}