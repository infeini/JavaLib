package test.os;

import ljs.os.OsUtils;
import org.junit.Test;

public class OsUtilsTest {
    @Test
    public void getOsTypeTest() {
        System.out.println(OsUtils.osType.getName());
    }
}
