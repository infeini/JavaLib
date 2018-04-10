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

    /**
     * 获取类的驼峰式命名
     */
    public static String getHumpName(Class type) {
        String result = null;
        if (type != null) {
            char[] names = type.getSimpleName().toCharArray();
            names[0] = Character.toLowerCase(names[0]);
            result = new String(names);
        }
        return result;
    }
}
