package ru.netology;

import java.time.LocalDateTime;

public class Logger{
    private final IStorage iStorage;

    public Logger(IStorage iStorage) {
        this.iStorage = iStorage;
    }

    public void log(String msg) {
        iStorage.append(LocalDateTime.now() + "#" + msg);
    }
}
