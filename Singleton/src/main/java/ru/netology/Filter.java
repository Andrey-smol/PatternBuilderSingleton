package ru.netology;

import java.util.List;
import java.util.stream.Collectors;

public class Filter {
    protected int treshold;

    public Filter(int treshold) {
        this.treshold = treshold;
        ILogger logger = Logger.getInstance();
        logger.log("Создание объекта new Filter(), treshold = " + treshold);
    }

    public List<Integer> filterOut(List<Integer> source) {
        ILogger logger = Logger.getInstance();
        logger.log("Зашли в метод filterOut объекта Filter");
        return source.stream().filter(i -> i < treshold).collect(Collectors.toList());
    }
}
