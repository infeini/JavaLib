package ljs.ddos;

import org.junit.Test;

public class DDosTest {
    @Test
    public void tcpAttackTest() throws InterruptedException {
        new DDos("www.taobao.com", 80, DDosType.TCP, 2)
                .attack().get(0).join();
    }
}
