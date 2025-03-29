package ru.netology;

public class ErrorCapsDecorator implements IStorage {
    private final IStorage storage;

    public ErrorCapsDecorator(IStorage storage) {
        this.storage = storage;
    }

    @Override
    public void append(String line) {
        storage.append(line.contains("error") ? line.toUpperCase() : line);
    }
}
