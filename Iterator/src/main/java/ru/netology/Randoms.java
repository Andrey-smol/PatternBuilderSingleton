package ru.netology;

import java.util.Iterator;
import java.util.Random;

public class Randoms implements Iterable<Integer>{
    private final Random random;
    int min;
    int max;
    //Iterator<Integer> iterator;

    public Randoms(int min, int max) {
        random = new Random();
        this.min = min;
        this.max = max + 1;
        //iterator = new Random().ints(min, max + 1).iterator();
    }

    @Override
    public Iterator<Integer> iterator() {
        return new Iterator<>() {
            @Override
            public boolean hasNext() {
                return true;
            }

            @Override
            public Integer next() {
                //return iterator.next();
                return min + random.nextInt(max - min);
            }
        };
    }
}
