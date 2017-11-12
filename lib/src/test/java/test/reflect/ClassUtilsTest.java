package test.reflect;

import ljs.reflect.ClassUtils;
import org.junit.Test;

import java.io.File;

public class ClassUtilsTest
{
    @Test
    public void getAllClassTest()
    {
        //Object obj = ClassUtils.getAllClass("test.date");
//        String path = getClass().getResource("/").getPath();
//        System.out.println("path = " + path);
        Class clasz = ClassUtils.getClass(new File("test/date/DateUtil.class"));
    }
}
