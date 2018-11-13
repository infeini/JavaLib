package ljs.reflect;

import org.junit.Assert;
import org.junit.Test;

import java.text.SimpleDateFormat;
import java.util.Date;

public class ClassUtilsTest {
    @Test
    public void checkPackageTest() {
        System.out.println(ClassUtils.checkPackage("zlx.sasaA777"));
    }

    @Test
    public void getHumpNameTest() {
        System.out.println(ClassUtils.getHumpName(SimpleDateFormat.class));
    }

    abstract class User<T> {
    }

    @Test
    public void getSuperclassTypeParameterTest() {
        User<Date> user = new User<Date>() {
        };
        Assert.assertSame(ClassUtils.getSuperclassTypeParameter(user.getClass())[0], Date.class);
        Assert.assertSame(ClassUtils.getSuperclassTypeParameter(user)[0], Date.class);
    }
}
