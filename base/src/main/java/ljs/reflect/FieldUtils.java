package ljs.reflect;

import ljs.lib.StringUtils;

import java.lang.reflect.Field;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class FieldUtils {
    /**
     * 通过反射获取对象的字段及字段值，以键值对方式返回
     *
     * @param prefix    键前缀
     * @param suffix    键后缀
     * @param obj       目标对象
     * @param fieldType 是否过滤类型
     * @return 字段键值对map集合
     */
    public static <T> Map<String, T> getFieldNameAndValues(Object obj, Class<T> fieldType, String prefix, String suffix) {
        HashMap<String, Object> map = new HashMap<>();
        if (obj != null) {
            for (Field field : getFields(obj.getClass())) {
                //过滤类型
                if (fieldType != null && fieldType != field.getType())
                    continue;
                String key = prefix + field.getName() + suffix;
                map.put(key, getValue(obj, field));
            }
        }
        return (Map<String, T>) map;
    }

    /**
     * 通过反射获取对象的字段及字段值，以键值对方式返回
     *
     * @param obj       目标对象
     * @param fieldType 是否过滤类型
     * @return 字段键值对map集合
     */
    public static <T> Map<String, T> getFieldNameAndValues(Object obj, Class<T> fieldType) {
        return getFieldNameAndValues(obj, fieldType, "", "");
    }

    /**
     * 通过反射获取对象的字段及字段值，以键值对方式返回
     *
     * @param obj 目标对象
     * @return 字段键值对map集合
     */
    public static Map<String, Object> getFieldNameAndValues(Object obj) {
        return getFieldNameAndValues(obj, null, "", "");
    }


    public static Map<Field, Object> getFieldsAndValues(Object obj) {
        Map<Field, Object> map = new HashMap<>();
        if (obj != null)
            for (Field field : getFields(obj.getClass())) {
                map.put(field, getValue(obj, field));
            }
        return map;
    }

    private static Object getValue(Object obj, Field field) {
        Object val = null;
        if (obj != null || field != null)
            try {
                val = field.get(obj);
            } catch (IllegalAccessException e) {
                e.printStackTrace();
            } finally {
                if (val == null && obj instanceof Map)
                    val = ((Map) obj).get(field.getName());
            }
        return val;
    }

    /**
     * 从指定对象中获取指定名称字段值
     *
     * @param obj       目标对象
     * @param fieldName 指定的字段名称
     * @return 字段值
     */
    public static Object getValueByName(Object obj, String fieldName) {
        Object val = null;
        if (obj != null) {
            Field field = getField(obj.getClass(), fieldName);
            if (field != null)
                val = getValue(obj, field);
        }
        return val;
    }

    /**
     * 通过名称获取字段(优先匹配本类字段，父类等级越高匹配优先级越低)
     *
     * @param type      class类型
     * @param fieldName 字段名称
     * @return 若不存在则返回
     */
    public static Field getField(Class type, String fieldName) {
        Field field = null;
        if (type != null && !StringUtils.isEmpty(fieldName)) {
            try {
                field = type.getField(fieldName);
            } catch (NoSuchFieldException e) {
                try {
                    field = type.getDeclaredField(fieldName);
                    field.setAccessible(true);
                } catch (NoSuchFieldException e1) {
                    Class superClass = type.getSuperclass();
                    if (superClass != null)
                        field = getField(superClass, fieldName);
                }
            }
        }
        return field;
    }

    /**
     * 获取所有字段(本类及所有父类公共字段、私有字段)
     *
     * @param type class类型
     * @return 字段list集合
     */
    public static List<Field> getFields(Class type) {
        List<Field> fields = new ArrayList<>();
        if (type != null) {
            //公共字段
            for (Field field : type.getFields())
                fields.add(field);
            //私有字段
            for (Field field : type.getDeclaredFields()) {
                field.setAccessible(true);
                fields.add(field);
            }
            Class superClass = type.getSuperclass();
            //递归获取所有父类字段
            if (superClass != null)
                fields.addAll(getFields(superClass));
        }
        return fields;
    }

    /**
     * 获取所有字段(本类及所有父类公共字段、私有字段)名称
     *
     * @param type class类型
     * @return 字段名称list集合
     */
    public static List<String> getFieldNames(Class type) {
        List<String> fields = new ArrayList<>();
        if (type != null) {
            //公共字段
            for (Field field : type.getFields())
                fields.add(field.getName());
            //私有字段
            for (Field field : type.getDeclaredFields()) {
                field.setAccessible(true);
                fields.add(field.getName());
            }
            Class superClass = type.getSuperclass();
            //递归获取所有父类字段
            if (superClass != null)
                fields.addAll(getFieldNames(superClass));
        }
        return fields;
    }
}