package ljs.lib;

public class IpUtils {
    /**
     * 检查IP格式是否正确
     *
     * @param ip ip地址
     * @return true, false
     */
    public static boolean isIp(String ip) {
        if (ip == null || ip.isEmpty())
            return false;
        else
            return ip.matches("\\d{1,3}\\.\\d{1,3}\\.\\d{1,3}\\.\\d{1,3}");
        //&& !ip.matches("0{1,3}\\..+");
    }

    /**
     * 字符串ip转byte格式ip
     *
     * @param ipStr 字符串格式ip
     * @return
     */
    public static byte[] toByte(String ipStr) {
        byte[] ipByte = null;
        if (isIp(ipStr)) {
            ipByte = new byte[4];
            String[] splits = ipStr.split("\\.");
            for (int i = 0; i < ipByte.length; i++) {
                try {
                    ipByte[i] = (byte) Integer.parseInt(splits[i]);
                } catch (NumberFormatException e) {
                }
            }
        }
        return ipByte;
    }

    /**
     * @param ipBytes byte数组形式ip
     * @return string形式ip
     */
    public static String paseIp(byte[] ipBytes) {
        String ip = "";
        if (ipBytes != null && ipBytes.length == 4)
            for (int i = 0; i < ipBytes.length; i++)
                //&0xff:将byte转换为int(保持位不变)
                ip += (ipBytes[i] & 0xff) + (i == 3 ? "" : ".");
        return ip;
    }
}
