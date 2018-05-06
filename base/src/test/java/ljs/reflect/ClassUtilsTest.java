package ljs.reflect;

import org.junit.Test;

import java.text.SimpleDateFormat;

public class ClassUtilsTest {
    @Test
    public void checkPackageTest() {
        System.out.println(ClassUtils.checkPackage("zlx.sasaA777"));
    }

    @Test
    public void getHumpNameTest() {
        System.out.println(ClassUtils.getHumpName(SimpleDateFormat.class));
    }
}
