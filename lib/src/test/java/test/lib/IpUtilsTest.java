package test.lib;

import ljs.lib.IpUtils;
import org.junit.Test;

public class IpUtilsTest {
    @Test
    public void isIpTest() {
        System.out.println(IpUtils.isIp("0.0.0.0"));
    }

    @Test
    public void toByteTest() {
        byte[] bytes = IpUtils.toByte("192.168.1.1");
        for (byte b : bytes)
            System.out.println(b);
    }

    @Test
    public void paseIpTest() {
        String ip = IpUtils.paseIp(IpUtils.toByte("192.168.1.1"));
        System.out.println(ip);
    }
}
