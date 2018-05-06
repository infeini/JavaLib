package ljs.io.net;

import org.junit.Test;

import java.net.InetAddress;
import java.util.List;

public class SocketUtilsTest {
    @Test
    public void getMyIPsTest() {
        List<InetAddress> addresses = SocketUtils.getMyIPs();
        for (InetAddress address : addresses) {
            System.out.println(address.getHostAddress());
        }
    }
}
