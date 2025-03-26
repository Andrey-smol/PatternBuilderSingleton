package ru.netology;

import java.time.LocalDateTime;

//public class Logger implements ILogger {
//    private Logger() {
//    }
//
//    private static class LoggerSingleton {
//        private static final Logger logger = new Logger();
//    }
//
//    public static Logger getInstance() {
//        return LoggerSingleton.logger;
//    }
//}

public class Logger implements ILogger{
    private static Logger logger;
    protected int num = 1;

    private Logger() {
    }

    public static Logger getInstance() {
        if (logger == null) {
            logger = new Logger();
        }
        return logger;
    }

    @Override
    public void log(String msg) {
        System.out.println("[" + getData() + " " + num++ + "] " + msg);
    }

    private String getData() {
        LocalDateTime localDate = LocalDateTime.now();
        return new StringBuilder().append(localDate.getDayOfMonth()).append("-")
                .append(localDate.getMonth().getValue()).append("-")
                .append(localDate.getYear()).append(" ")
                .append(localDate.getHour()).append("-")
                .append(localDate.getMinute()).append("-")
                .append(localDate.getSecond()).append(" ")
                .toString();
    }
}
