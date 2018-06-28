package ljs.task;

public class ThreadUtil {
    /**
     * 休眠当前线程
     *
     * @param millis 休眠时间(毫秒)
     */
    public static void sleep(long millis) {
        try {
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
