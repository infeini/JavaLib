package ljs.reflect;

import ljs.exception.KnowException;

import java.lang.reflect.ParameterizedType;
import java.lang.reflect.Type;
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

    /**
     * 获取父类范型参数
     *
     * @param target 目标对象
     */
    public static Type[] getSuperclassTypeParameter(Object target) {
        return getSuperclassTypeParameter(target.getClass());
    }

    /**
     * 获取父类范型参数
     *
     * @param type 类型
     */
    public static Type[] getSuperclassTypeParameter(Class type) {
        Type superclass = type.getGenericSuperclass();
        if (superclass instanceof Class) return new Type[]{};
        else {
            ParameterizedType parameterized = (ParameterizedType) superclass;
            return parameterized.getActualTypeArguments();
        }
    }

    public static <T> T newInstance(Class<T> type) throws KnowException {
        try {
            return type.newInstance();
        } catch (Exception e) {
            throw new KnowException("创建对象失败:" + type);
        }
    }
}
