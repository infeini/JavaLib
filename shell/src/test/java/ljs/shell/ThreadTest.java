package ljs.shell;

import ljs.task.ThreadUtil;
import org.junit.Test;

public class ThreadTest {
    @Test
    public void test() {
        for (int i = 0; i < 100; i++) {
            int finalI = i;
            new Thread(() -> doTh(finalI)).start();
        }
        ThreadUtil.sleep(10000000);
    }

    synchronized void doTh(int i) {
        System.out.println("start:" + i);
        ThreadUtil.sleep(1000);
        System.out.println("end:" + i);
    }
}
