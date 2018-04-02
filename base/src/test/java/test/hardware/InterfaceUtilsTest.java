package test.hardware;

import ljs.hardware.InterfaceUtils;
import org.junit.Test;

public class InterfaceUtilsTest {
    @Test
    public void getMacTest() {
        String mac = InterfaceUtils.getMac("enp1s0");
        System.out.println(mac);
    }

    @Test
    public void getIpTest() {
        String ip = InterfaceUtils.getIp("enp1s0");
        System.out.println(ip);
    }
}
