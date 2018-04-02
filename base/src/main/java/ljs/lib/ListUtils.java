package ljs.lib;

import java.util.List;

public class ListUtils {
    /**
     * 将数组所有元素添加到集合
     */
    public static void add(Object[] objects, List list) {
        for (Object object : objects)
            list.add(object);
    }
}
