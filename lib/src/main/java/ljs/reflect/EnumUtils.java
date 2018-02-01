package ljs.reflect;

import ljs.exception.KnowException;
import ljs.lib.StringUtils;

public class EnumUtils {
    /**
     * 默认枚举值存储字段名称
     */
    public static String DefaultValueFieldName = "value";

    /**
     * 将枚举转换为int
     *
     * @param enumObj 枚举对象
     * @return int 值
     */
    public static Object toValue(Object enumObj) throws KnowException {
        return toValue(enumObj, DefaultValueFieldName);
    }

    /**
     * 将枚举转换为int
     *
     * @param enumObj        枚举对象
     * @param valueFieldName 值存储字段
     * @return int 值
     */
    public static Object toValue(Object enumObj, String valueFieldName) throws KnowException {
        haveValue(enumObj, valueFieldName);
        Object obj = FieldUtils.getValueByName(enumObj, valueFieldName);
        if (obj == null)
            throw new KnowException("从枚举类型" + enumObj.getClass().getName() + ",无法获取字段" + valueFieldName);
        else
            return obj;
    }

    /**
     * 判断该枚举类型是否符合转换的要求
     *
     * @param enumObj        枚举对象
     * @param valueFieldName 值存储字段名称
     */
    public static boolean haveValue(Object enumObj, String valueFieldName) throws KnowException {
        if (enumObj == null)
            throw new KnowException("枚举对象不能为空!");
        return haveValue(enumObj.getClass(), valueFieldName);
    }

    /**
     * 判断该枚举类型是否符合转换的要求
     *
     * @param enumType       枚举类型
     * @param valueFieldName 值存储字段名称
     */
    public static boolean haveValue(Class enumType, String valueFieldName) throws KnowException {
        boolean re;
        if (enumType == null)
            throw new KnowException("枚举类型不能为空!");
        if (StringUtils.isEmpty(valueFieldName))
            throw new KnowException("枚举值存储字段名称不能为空!");
        try {
            enumType.getField(valueFieldName);
            re = true;
        } catch (NoSuchFieldException e) {
            throw new KnowException("获取字段:" + valueFieldName + "失败");
        }
        return re;
    }

    /**
     * 将数值转换为枚举
     *
     * @param value    待转换目标
     * @param enumType 转换类型
     */
    public static <T> T parse(Object value, Class<T> enumType) throws KnowException {
        return parse(value, enumType, DefaultValueFieldName);
    }

    /**
     * 将数值转换为枚举
     *
     * @param value          待转换目标
     * @param enumType       转换类型
     * @param valueFieldName 值存储字段名称
     */
    public static <T> T parse(Object value, Class<T> enumType, String valueFieldName) throws KnowException {
        haveValue(enumType, valueFieldName);
        for (Object obj : enumType.getEnumConstants()) {
            Object enumValue = FieldUtils.getValueByName(obj, valueFieldName);
            if (value.equals(enumValue))
                return (T) obj;
        }
        throw new KnowException(value + "不能转换为枚举:" + enumType.getName());
    }


    /**
     * 判断value是否能转换为枚举类型
     *
     * @param value    待转换目标
     * @param enumType 转换类型
     */
    public static boolean canParse(Object value, Class enumType) {
        return canParse(value, enumType, DefaultValueFieldName);
    }

    /**
     * 判断value是否能转换为枚举类型
     *
     * @param value          待转换目标
     * @param enumType       转换类型
     * @param valueFieldName 值存储字段名称
     */
    public static boolean canParse(Object value, Class enumType, String valueFieldName) {
        boolean re = false;
        try {
            parse(value, enumType, valueFieldName);
            re = true;
        } finally {
            return re;
        }
    }
}
