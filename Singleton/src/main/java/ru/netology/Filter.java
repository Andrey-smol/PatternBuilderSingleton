package ru.netology;

import java.util.List;
import java.util.stream.Collectors;

public class Filter {
    protected int treshold;

    public Filter(int treshold) {
        this.treshold = treshold;
        Logger.getInstance().log("Создание объекта new Filter(), treshold = " + treshold);
    }

    public List<Integer> filterOut(List<Integer> source) {
        Logger.getInstance().log("Зашли в метод filterOut объекта Filter");
        return source.stream().filter(i -> i < treshold).collect(Collectors.toList());
    }
}
