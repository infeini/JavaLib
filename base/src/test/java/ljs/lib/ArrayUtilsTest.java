package ljs.lib;

import org.junit.Test;

public class ArrayUtilsTest {
    @Test
    public void getIndexTest() {
        Integer[] array = new Integer[]{123, 456, 789};
        int index = ArrayUtils.getIndex(array, 456);
        System.out.println(index);
    }
}
