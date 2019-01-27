package ljs.task;

import org.junit.Test;

public class ThreadUtilTest {
    @Test
    public void sleepTest() {
        ThreadUtil.sleep(1000, System.out::println);
    }
}
