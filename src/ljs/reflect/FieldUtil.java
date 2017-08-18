package ljs.reflect;

import java.lang.reflect.Field;
import java.util.HashMap;
import java.util.Map;

public class FieldUtil
{
    /**
     * 通过反射获取对象的字段及字段值，以键值对方式返回
     *
     * @param prefix 键前缀
     * @param suffix 键后缀
     * @param obj    目标对象
     * @return 字段键值对map集合
     */
    public static Map<String, Object> getFieldAndValue(Object obj, String prefix, String suffix)
    {
        HashMap<String, Object> map = new HashMap<>();
        if (obj != null)
        {
            Class type = obj.getClass();
            Field[] fields = type.getDeclaredFields();
            for (Field field : fields)
            {
                field.setAccessible(true);
                try
                {
                    String key = prefix + field.getName() + suffix;
                    Object value = field.get(obj);
                    map.put(key, value);
                } catch (Exception e)
                {
                    e.printStackTrace();
                }
            }
        }
        return map;
    }

    /**
     * 通过反射获取对象的字段及字段值，以键值对方式返回
     *
     * @param obj 目标对象
     * @return 字段键值对map集合
     */
    public static Map<String, Object> getFieldAndValue(Object obj)
    {
        return getFieldAndValue(obj, "", "");
    }

}