package ljs.io.net;

import java.io.File;
import java.net.InetAddress;
import java.net.NetworkInterface;
import java.util.ArrayList;
import java.util.Enumeration;
import java.util.List;

public class SocketUtils
{
    /**
     * 通过tcp下载文件
     *
     * @param host             host地址
     * @param port             端口
     * @param saveAs           存储路径
     * @param downloadListener 下载监听器
     */
    public static void downloadTcp(String host, int port, File saveAs, DownloadListener downloadListener)
    {
    }

    /**
     * 获取本机所有ip
     *
     * @return 所有网络接口ip
     */
    public static List<InetAddress> getMyIPs()
    {
        List<InetAddress> inetAddresses = new ArrayList<>();
        try
        {
            Enumeration<NetworkInterface> interfaces = NetworkInterface.getNetworkInterfaces();
            while (interfaces.hasMoreElements())
            {
                NetworkInterface netface = interfaces.nextElement();
                Enumeration<InetAddress> ips = netface.getInetAddresses();
                while (ips.hasMoreElements())
                {
                    InetAddress address = ips.nextElement();
                    inetAddresses.add(address);
                }
            }
        } catch (Exception e)
        {
            e.printStackTrace();
        }
        return inetAddresses;
    }
}
