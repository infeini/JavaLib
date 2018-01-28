package test.reflect;

import ljs.exception.KnowException;
import ljs.reflect.ClassUtils;
import org.junit.Test;

import java.util.List;

public class ClassUtilsTest {
    @Test
    public void getAllClassTest() throws KnowException {
        List<Class> classes1 = ClassUtils.getAllClass("ljs");
        List<Class> classes2 = ClassUtils.getAllClass("ljs.date.**");
    }

    @Test
    public void checkPackageTest() {
        System.out.println(ClassUtils.checkPackage("zlx.sasaA777"));
    }
}
