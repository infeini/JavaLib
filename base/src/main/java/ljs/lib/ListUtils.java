package ljs.lib;

import ljs.exception.KnowException;

import java.lang.reflect.Array;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.util.List;

public class ListUtils {
    /**
     * 将数组所有元素添加到集合
     */
    public static void add(Object[] objects, List list) {
        for (Object object : objects)
            list.add(object);
    }

    /**
     * 将集合转换为数组
     */
    public static <T> T[] toArray(List list, Class<T> itemType) throws KnowException {
        int arrayLength = list == null ? 0 : list.size();
        T[] array = (T[]) Array.newInstance(itemType, arrayLength);
        for (int i = 0; i < list.size(); i++)
            array[i] = autoConvert(list.get(i), itemType);
        return array;
    }

    public static <T> T autoConvert(Object target, Class<T> toType) throws KnowException {
        if (target == null)
            return null;
        //调用对象to+toType名称的方法转换
        Class targetType = target.getClass();
        if (targetType == toType)
            return (T) target;
        Method convertMethod;
        try {
            convertMethod = targetType.getMethod("to" + toType.getSimpleName());
            convertMethod.setAccessible(true);
            if (convertMethod.getReturnType() != toType)
                throw new KnowException("转换方法" + convertMethod.getName() + ":返回指类型错误,应该是:" + toType.getName());
            return (T) convertMethod.invoke(target);
        } catch (NoSuchMethodException e) {
            throw new KnowException("找不到转换方法");
        } catch (IllegalAccessException e) {
            throw new KnowException("执行转换方法失败:" + e);
        } catch (InvocationTargetException e) {
            throw new KnowException("执行转换呢方法失败:" + e);
        }
    }
}
