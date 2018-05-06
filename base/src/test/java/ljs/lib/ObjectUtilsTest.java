package ljs.lib;

import org.junit.Test;

public class ObjectUtilsTest {
    @Test
    public void getObjectIndex() {
        int index = ObjectUtils.getObjectIndex(new Integer[]{new Integer(1), new Integer(2)}, new Integer(1));
        System.out.println(index);
    }
}
