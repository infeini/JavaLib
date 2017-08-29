package test;

import org.junit.Test;

import java.io.File;

public class TestClass
{
    class Father
    {
        public String obj = "abc";
    }

    class Son extends Father
    {
        public Integer obj = 123;
    }

    @Test
    public void test()
    {
        new File("D:\\Users\\LiuJiangshan\\Desktop\\ljs19951215").delete();
    }
}
