package ljs.task;

public class ThreadUtil {
    public static void sleep(long millis) {
        sleep(millis, null);
    }

    /**
     * 休眠当前线程
     *
     * @param millis 休眠时间(毫秒)
     */
    public static void sleep(long millis, SleepHandler sleepHandler) {
        if (millis < 0) return;
        try {
            if (sleepHandler != null) new Thread(() -> {
                for (long i = millis; i > 0; i--) {
                    sleepHandler.onSleep(i);
                    ThreadUtil.sleep(1, null);
                }
            }).start();
            Thread.sleep(millis);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }


    public static void wait(Object lock) {
        synchronized (lock) {
            try {
                lock.wait();
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    }

    public static void notify(Object lock) {
        synchronized (lock) {
            lock.notify();
        }
    }

    public static void notifyAll(Object lock) {
        synchronized (lock) {
            lock.notifyAll();
        }
    }
}
