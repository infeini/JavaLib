package ljs.lib;

public class ObjectUtils {
    /**
     * 查找对象不能为空!判断对象数组是否存在某个对象(对象类型使用内存地址比较,数字、字符串自动使用值类型比较)
     *
     * @param objects    查找的数组
     * @param findObject 查找的对象
     * @return 存在:返回数组角标,不存在:返回-1
     */
    public static int getObjectIndex(Object[] objects, Object findObject) {
        int index = -1;
        if (objects == null || findObject == null) ;
        else {
            for (int i = 0; i < objects.length; i++) {
                Object nowObject = objects[i];
                if (findObject.equals(nowObject)) {
                    index = i;
                    break;
                }
            }
        }
        return index;
    }
}
