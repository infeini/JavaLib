package ljs.task;

import org.junit.Test;

public class ThreadUtilTest {
    @Test
    public void sleepTest() {
        ThreadUtil.sleep(10000, System.out::println);
    }
}
