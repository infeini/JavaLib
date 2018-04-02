package ljs.os;

public class OsUtils {
    public static OsType osType = getOsType();

    private static OsType getOsType() {
        String osName = System.getProperty("os.name").toLowerCase();
        if (osName.contains("windows"))
            return OsType.WINDOWS;
        else if (osName.contains("linux"))
            return OsType.LINUX;
        else if (osName.contains("mac"))
            return OsType.MAC;
        else
            return OsType.UNKNOW;
    }
}
