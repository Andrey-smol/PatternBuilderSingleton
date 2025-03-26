package ru.netology;

public class Person {
    private final String name;
    private final String surname;
    protected int age;
    protected String city;



    public Person(String name, String surname) {
        if(name == null || name.isEmpty()){
            throw new IllegalArgumentException("Вы не ввели своё имя");
        }
        if(surname == null || surname.isEmpty()){
            throw new IllegalArgumentException("Вы не ввели свою фамилию");
        }
        this.name = name;
        this.surname = surname;
    }

    public Person(String name, String surname, int age) {
        this(name, surname);
        this.age = age;
    }

    public Person(String name, String surname, int age, String city) {
        this(name, surname, age);
        this.city = city;
    }

    public String getName() {
        return name;
    }

    public String getSurname() {
        return surname;
    }

    public int getAge() {
        return age;
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
        if (age < 0) {
            age = 0;
        }
        age++;
    }
    public IPersonBuilder newChildBuilder(){
        return new PersonBuilder().setAddress(city).setSurname(surname);
    }

    @Override
    public int hashCode() {
        return name.hashCode() + surname.hashCode();
    }

    @Override
    public String toString() {
        StringBuilder builder = new StringBuilder();
        builder.append("Person: [name = ").append(name)
                .append(", surname = ").append(surname)
                .append(", age = ").append(hasAge() ? age : "age is not set")
                .append(", city = ").append(hasAddress() ? city : "city is not set")
                .append("]");
        return builder.toString();
    }
}
