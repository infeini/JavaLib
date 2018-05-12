package ljs.ddos.tcp.http;

import ljs.ddos.SocketRequestBuild;
import ljs.lib.StringUtils;

import java.net.MalformedURLException;
import java.net.URL;
import java.util.HashMap;
import java.util.Map;
import java.util.TreeSet;

public class HttpRequestBuild implements SocketRequestBuild {
    private HttpMethod method;

    public HttpRequestBuild method(HttpMethod method) {
        this.method = method;
        return this;
    }

    private static final String GET_TEMP = "GET ${path} HTTP/1.1\r\nHOST: ${host}";
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
        int port = url.getPort();
        return port == -1 ? 80 : port;
    }

    private String getPath() {
        String path = url.getPath();
        if (StringUtils.isEmpty(path))
            path = "/";
        return path;
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
    public HttpRequestBuild addHead(String key, String value) {
        head.put(key, value);
        return this;
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
            for (String key : head.keySet()) {
                String item = head.get(key);
                dataBuffer.append("\r\n" + key + ": " + item);
            }
            dataBuffer.append("\r\n\r\n");
        }
        String hexString = toHexString(dataBuffer.toString().getBytes());
        return dataBuffer.toString().getBytes();
    }

    public String toHexString(byte[] data) {
        StringBuffer hexBuffer = new StringBuffer();
        if (data != null)
            for (Byte b : data)
                hexBuffer.append(toHexString(b.intValue()));
        return hexBuffer.toString();
    }

    public String toHexString(int value) {
        String hexString = Integer.toHexString(value);
        if (hexString.length() == 1)
            hexString = 0 + hexString;
        return hexString;

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
