package ru.netology;

import java.util.Optional;
import java.util.OptionalInt;

public class PersonBuilder implements IPersonBuilder {
    private String name;
    private String surname;
    private int age = -1;
    private String city;

    @Override
    public IPersonBuilder setName(String name) {
        this.name = name;
        return this;
    }

    @Override
    public IPersonBuilder setSurname(String surname) {
        this.surname = surname;
        return this;
    }

    @Override
    public IPersonBuilder setAge(int age) {
        if (age < 0) {
            throw new IllegalArgumentException("Возраст не может быть меньше нуля: " + age);
        }
        this.age = age;
        return this;
    }

    @Override
    public IPersonBuilder setAddress(String address) {
        this.city = address;
        return this;
    }

    @Override
    public Person build() {
//        if (name == null || name.isEmpty()) {
//            throw new IllegalStateException("Вы не ввели своё имя");
//        }
//        if (surname == null || surname.isEmpty()) {
//            throw new IllegalStateException("Вы не ввели свою фамилию");
//        }
        //Optional.ofNullable(name).orElseThrow(() -> new IllegalStateException("Вы не ввели своё имя"));
        //Optional.ofNullable(surname).orElseThrow(() -> new IllegalStateException("Вы не ввели свою фамилию"));
        return new Person(name, surname, age < 0 ? OptionalInt.empty() : OptionalInt.of(age), city);
    }
}
