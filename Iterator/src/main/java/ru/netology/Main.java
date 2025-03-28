package ru.netology;

public class Main {
    private static final int MIN_RANDOM_NUMBER = 80;
    private static final int MAX_RANDOM_NUMBER = 100;
    public static void main(String[] args) {

        int count = 0;
        for (int r : new Randoms(MIN_RANDOM_NUMBER, MAX_RANDOM_NUMBER)) {
            System.out.println("Случайное число: " + r);
            count++;
            if (r == MAX_RANDOM_NUMBER) {
                System.out.printf("Выпало число %d через %d проходов, давайте на этом закончим\n", MAX_RANDOM_NUMBER, count);
                break;
            }
        }
    }
}