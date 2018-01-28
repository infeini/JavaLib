package test.lib;

import org.junit.Test;

public class StringUtilsTest {
    @Test
    public void getRandStr() {
        for (int i = 0; i < 100; i++)
            System.out.println(ljs.lib.StringUtils.getRandString(100));
    }
}
