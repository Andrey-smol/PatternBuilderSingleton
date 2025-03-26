package ru.netology;

import java.util.List;
import java.util.Random;
import java.util.Scanner;
import java.util.stream.Collectors;

public class Main {
    public static void main(String[] args) {

        ILogger logger = Logger.getInstance();
        logger.log("Запускаем программу");

        Scanner in = new Scanner(System.in);
        logger.log("Просим пользователя ввести входные данные для списка");

        System.out.print("Введите размер списка: ");
        String str = in.next();

        if (isNumeric(str)) {
            int sizeList = Integer.parseInt(str);
            System.out.print("Введите верхнюю границу для значений: ");
            str = in.next();

            if (isNumeric(str)) {
                int upperLimit = Integer.parseInt(str);
                logger.log("Создаём и наполняем список");
                List<Integer> list = new Random().ints(sizeList, 0, upperLimit).boxed().collect(Collectors.toList());
                System.out.print("Вот случайный список: ");
                list.stream().forEach(s -> System.out.print(s + " "));
                System.out.println();
                logger.log("Просим пользователя ввести входные данные для фильтрации");

                System.out.print("Введите порог для фильтра: ");
                str = in.next();

                if (isNumeric(str)) {
                    int f = Integer.parseInt(str);
                    logger.log("Запускаем фильтрацию");
                    Filter filter = new Filter(f);
                    list = filter.filterOut(list);
                    logger.log("Выводим результат на экран");
                    System.out.print("Отфильтрованный список: ");
                    list.stream().forEach(s -> System.out.print(s + " "));
                    System.out.println();
                    logger.log("Завершаем программу");
                    return;
                }
            }
        }
        System.out.printf("Вы ввели неправильное число: %s, программа будет закрыта", str);
        logger.log("Пользователь ввёл не верные данные");
        logger.log("Завершаем программу");
    }

    public static boolean isNumeric(String str) {
        return str.matches("-?\\d+(\\.\\d+)?");
    }
}