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

    class Class2 extends ArrayList {
    }

    @Test
    public void getGenericClassByObjTest() {
        Type[] types = ClassUtils.getGenericClassByObj(new Class1());
        System.out.println(types);

        types = ClassUtils.getGenericClassByObj(new Class2());
        System.out.println(types);
    }

    @Test
    public void getGenericClassByTypeTest() {
        Type[] types = ClassUtils.getGenericClassByType(new Class1().getClass());
        System.out.println(types);

        types = ClassUtils.getGenericClassByType(new Class2().getClass());
        System.out.println(types);
    }
}
