package ljs.reflect;

import ljs.lib.StringUtils;
import org.junit.Test;

import java.lang.reflect.Type;
import java.text.SimpleDateFormat;
import java.util.ArrayList;

public class ClassUtilsTest {
    @Test
    public void checkPackageTest() {
        System.out.println(ClassUtils.checkPackage("zlx.sasaA777"));
    }

    @Test
    public void getHumpNameTest() {
        System.out.println(ClassUtils.getHumpName(SimpleDateFormat.class));
    }

    class Class1 extends ArrayList<StringUtils> {
    }

    @Test
    public void getGenericClassTest() {
        Type[] types = ClassUtils.getGenericClassByObj(new Class1());
        System.out.println(types);
    }
}
