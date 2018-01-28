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
}
