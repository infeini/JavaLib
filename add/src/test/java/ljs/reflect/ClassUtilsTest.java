package ljs.reflect;

import ljs.exception.KnowException;
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

    interface Book<T> {
    }

    class MyBook implements Book<String> {
    }

    @Test
    public void getSuperInterFaceTTest() throws KnowException {
        Type[] types = ClassUtils.getSuperInterFaceT(MyBook.class, Book.class);
        System.out.println(types);
    }

    @Test
    public void getGenericClassByObjTest() throws KnowException {
        Type[] types = ClassUtils.getSuperClassT(new Class1(), ArrayList.class);
        System.out.println(types);

        types = ClassUtils.getSuperClassT(new Class2(), ArrayList.class);
        System.out.println(types);
    }

    @Test
    public void getGenericClassByTypeTest() throws KnowException {
        Type[] types = ClassUtils.getSuperClassT(new Class1().getClass(), ArrayList.class);
        System.out.println(types);

        types = ClassUtils.getSuperClassT(new Class2().getClass(), ArrayList.class);
        System.out.println(types);
    }
}
