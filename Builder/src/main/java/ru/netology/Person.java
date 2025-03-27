package ru.netology;

import java.util.Objects;
import java.util.Optional;
import java.util.OptionalInt;

public class Person {
    private final String name;
    private final String surname;
    private int age = -1;
    private String city;


    public Person(String name, String surname) {
        this.name = Optional.ofNullable(name).orElseThrow(() -> new IllegalStateException("Вы не ввели своё имя"));
        this.surname = Optional.ofNullable(surname).orElseThrow(() -> new IllegalStateException("Вы не ввели свою фамилию"));
    }

    public Person(String name, String surname, OptionalInt age) {
        this(name, surname);
        if (age.isPresent() && age.getAsInt() < 0) {
            throw new IllegalArgumentException("Возраст не может быть меньше нуля: " + age.getAsInt());
        }
        this.age = age.orElse(-1);
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
        return age < 0 ? OptionalInt.empty() : OptionalInt.of(age);
    }

    public String getCity() {
        return city;
    }

    public void setAddress(String address) {
        this.city = address;
    }

    public boolean hasAge() {
        return age >= 0;
    }

    public boolean hasAddress() {
        return getCity() != null;
    }

    public void happyBirthday() {
        if (age >= 0) {
            age++;
        }
    }

    public IPersonBuilder newChildBuilder() {
        return new PersonBuilder().setAddress(city).setSurname(surname);
    }

    @Override
    public int hashCode() {
        return Objects.hash(name, surname);
        //return name.hashCode() + surname.hashCode();
    }

    @Override
    public String toString() {
        return new StringBuilder().append("Person [name = ").append(name)
                .append(", surname = ").append(surname)
                .append(", age = ").append(hasAge() ? age : "age is not set")
                .append(", city = ").append(hasAddress() ? city : "city is not set")
                .append("]").toString();
    }
}
