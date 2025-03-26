package ru.netology;

import java.util.OptionalInt;

public class Person {
    private final String name;
    private final String surname;
    private OptionalInt age;
    private String city;


    public Person(String name, String surname) {
        if (name == null || name.isEmpty()) {
            throw new IllegalStateException("Вы не ввели своё имя");
        }
        if (surname == null || surname.isEmpty()) {
            throw new IllegalStateException("Вы не ввели свою фамилию");
        }
        this.name = name;
        this.surname = surname;
    }

    public Person(String name, String surname, OptionalInt age) {
        this(name, surname);
        this.age = age;
    }

    public Person(String name, String surname, OptionalInt age, String city) {
        this(name, surname, age);
        this.city = city;
    }

    public String getName() {
        return name;
    }

    public String getSurname() {
        return surname;
    }

    public OptionalInt getAge() {
        return age;
    }

    public String getCity() {
        return city;
    }

    public void setAddress(String address) {
        this.city = address;
    }

    public boolean hasAge() {
        return age.isPresent();
    }

    public boolean hasAddress() {
        return getCity() != null;
    }

    public void happyBirthday() {
        if (age.isPresent()) {
            age = OptionalInt.of(age.getAsInt() + 1);
        }
    }

    public IPersonBuilder newChildBuilder() {
        return new PersonBuilder().setAddress(city).setSurname(surname);
    }

    @Override
    public int hashCode() {
        return name.hashCode() + surname.hashCode();
    }

    @Override
    public String toString() {
        return new StringBuilder().append("Person [name = ").append(name)
                .append(", surname = ").append(surname)
                .append(", age = ").append(hasAge() ? age.getAsInt() : "age is not set")
                .append(", city = ").append(hasAddress() ? city : "city is not set")
                .append("]").toString();
    }
}
