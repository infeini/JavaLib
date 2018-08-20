package ljs.reflect;

import ljs.exception.KnowException;
import sun.reflect.generics.reflectiveObjects.ParameterizedTypeImpl;

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
     * 获取指定对象的带有泛型的父类泛型参数
     *
     * @param target 目标对象
     */
    public static Type[] getSuperClassT(Object target, Class tType) throws KnowException {
        return getSuperClassT(target.getClass(), tType);
    }

    /**
     * 获取指定对象的带有泛型的父类泛型参数
     *
     * @param target 目标类型
     */
    public static Type[] getSuperClassT(Class target, Class tType) throws KnowException {
        Type genericSuperclass = target.getGenericSuperclass();
        if (genericSuperclass instanceof ParameterizedType) {
            if (((ParameterizedType) genericSuperclass).getRawType() == tType)
                return ((ParameterizedType) genericSuperclass).getActualTypeArguments();
        }
        throw new KnowException("未发现父类泛型");
    }

    /**
     * 获取指定对象的带有泛型的接口泛型参数
     *
     * @param target 目标对象
     */
    public static Type[] getSuperInterFaceT(Object target, Class tType) throws KnowException {
        return getSuperClassT(target.getClass(), tType);
    }

    /**
     * 获取指定对象的带有泛型的接口泛型参数
     *
     * @param target 目标类型
     */
    public static Type[] getSuperInterFaceT(Class target, Class tType) throws KnowException {
        Type[] genericSuperclasses = target.getGenericInterfaces();
        for (Type genericSuperclass : genericSuperclasses)
            if (genericSuperclass instanceof ParameterizedTypeImpl) {
                if (((ParameterizedTypeImpl) genericSuperclass).getRawType() == tType)
                    return ((ParameterizedTypeImpl) genericSuperclass).getActualTypeArguments();
            }
        throw new KnowException("未发现接口泛型");
    }

    public static <T> T newInstance(Class<T> type) throws KnowException {
        try {
            return type.newInstance();
        } catch (Exception e) {
            throw new KnowException("创建对象失败:" + type);
        }
    }
}
