package ljs.ddos;

import java.net.MalformedURLException;
import java.net.URL;
import java.util.HashMap;
import java.util.Map;

public class HttpRequestBuild implements SocketRequestBuild {
    private HttpMethod method;

    public HttpRequestBuild method(HttpMethod method) {
        this.method = method;
        return this;
    }

    private static final String GET_TEMP = "GET ${path} HTTP/1.1\\r\\nHOST: ${host}\\r\\n\\r\\n";
    private Map<String, String> head = new HashMap<>();

    public Map<String, String> getHead() {
        return head;
    }

    private URL url;

    public HttpRequestBuild url(String url) throws MalformedURLException {
        this.url = new URL(url);
        return this;
    }

    @Override
    public String getHost() {
        return url.getHost();
    }

    @Override
    public int getPort() {
        return url.getPort();
    }

    private String getPath() {
        return url.getPath();
    }

    /**
     * 删除http请求头
     */
    public void removeHead(String key) {
        head.remove(key);
    }

    /**
     * 添加http请求头
     */
    public void addHead(String key, String value) {
        head.put(key, value);
    }

    private StringBuffer dataBuffer;

    /**
     * 获取get请求报文
     */
    private byte[] get() {
        if (dataBuffer == null) {
            dataBuffer = new StringBuffer();
            dataBuffer.append(GET_TEMP
                    .replace("${path}", getPath())
                    .replace("${host}", getHost()));
        }
        return dataBuffer.toString().getBytes();
    }

    @Override
    public byte[] getData() {
        switch (method) {
            case GET:
                return get();
            default:
                throw new RuntimeException("未实现");
        }
    }

}
