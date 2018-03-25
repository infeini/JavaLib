package ljs.weixin.impl;

import ljs.weixin.Logger;

public class LoggerImpl implements Logger {
    static class SingletonHolder {
        private static LoggerImpl INSTANCE = new LoggerImpl();
    }

    public static LoggerImpl getInstance() {
        return SingletonHolder.INSTANCE;
    }

    @Override
    public void info(Object... logs) {
        for (Object log : logs)
            System.out.println(log);
    }

    @Override
    public void warn(Object... logs) {
        for (Object log : logs)
            System.err.println(log);
    }
}
