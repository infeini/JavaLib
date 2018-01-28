package ljs.hardware;

import java.net.Inet4Address;
import java.net.InetAddress;
import java.net.NetworkInterface;
import java.net.SocketException;
import java.util.Enumeration;

public class InterfaceUtils {
    /**
     * 获取网络接口mac地址
     *
     * @param interfaceName 接口名称
     */
    public static String getMac(String interfaceName) {
        String mac = "";
        try {
            if (interfaceName != null) {
                Enumeration<NetworkInterface> interfaces = NetworkInterface.getNetworkInterfaces();
                while (interfaces.hasMoreElements()) {
                    NetworkInterface networkInterface = interfaces.nextElement();
                    if (interfaceName.equals(networkInterface.getName())) {
                        mac = toMacString(networkInterface.getHardwareAddress());
                        break;
                    }
                }
            }
        } catch (SocketException e) {
            e.printStackTrace();
        } finally {
            return mac;

        }
    }

    /**
     * 获取网络接口ip地址
     *
     * @param interfaceName 接口Ip
     */
    public static String getIp(String interfaceName) {
        String ip = "";
        try {
            if (interfaceName != null) {
                Enumeration<NetworkInterface> interfaces = NetworkInterface.getNetworkInterfaces();
                while (interfaces.hasMoreElements()) {
                    NetworkInterface networkInterface = interfaces.nextElement();
                    if (interfaceName.equals(networkInterface.getName())) {
                        Enumeration<InetAddress> inetAddress = networkInterface.getInetAddresses();
                        while (inetAddress.hasMoreElements()) {
                            InetAddress address = inetAddress.nextElement();
                            if (address instanceof Inet4Address) {
                                ip = address.getHostAddress();
                            }
                        }
                    }
                }
            }
        } catch (SocketException e) {
            e.printStackTrace();
        } finally {
            return ip;

        }
    }

    /**
     * 将byte数组mac地址转换为字符串
     *
     * @param macByte byte数组mac
     */
    public static String toMacString(byte[] macByte) {
        String macString = "";
        for (int i = 0; i < macByte.length; i++) {
            macString += Integer.toHexString(macByte[i] & 0xff);
            if (i < macByte.length - 1)
                macString += ":";
        }
        return macString;
    }
}
