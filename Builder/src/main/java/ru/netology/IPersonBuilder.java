package ru.netology;

public interface IPersonBuilder {
    public IPersonBuilder setName(String name);
    public IPersonBuilder setSurname(String surname);
    public IPersonBuilder setAge(int age);
    public IPersonBuilder setAddress(String address);
    public Person build();
}
