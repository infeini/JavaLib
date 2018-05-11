package ljs.ddos;

import org.junit.Test;

public class DDosTest {
    @Test
    public void tcpAttackTest() throws InterruptedException {
        new DDos("192.168.1.91", 8080, DDosType.TCP, 2)
                .attack().get(0).join();
    }
}
