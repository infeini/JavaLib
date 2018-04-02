package ljs.reflect;

import java.util.regex.Pattern;

public class ClassUtils {
    //包名校验正则表达式
    static Pattern packageCheck = Pattern.compile("^[A-Za-z][A-Za-z0-9]*(\\.[A-Za-z][A-Za-z0-9]*)*$");

    /**
     * 校验包名格式是否正确
     *
     * @param packageName
     */
    public static boolean checkPackage(String packageName) {
        return packageCheck.matcher(packageName).find();
    }
}
