package ljs.lib;

public class ArrayUtils {
    /**
     * 获取对象在数组中的位置
     *
     * @param array  数组
     * @param target 查找的对象
     * @return 不存在返回-1
     */
    public static int getIndex(Object[] array, Object target) {
        for (int i = 0; i < array.length; i++) {
            Object obj = array[i];
            if (obj.equals(target))
                return i;
        }
        return -1;
    }
}
