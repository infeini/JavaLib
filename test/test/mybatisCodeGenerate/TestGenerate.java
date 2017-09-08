package test.mybatisCodeGenerate;


import ljs.code.mybatis.mapper.java.MapperJavaCodeGenerate;
import ljs.code.mybatis.mapper.xml.MapperXmlCodeGenerate;
import org.junit.Test;

public class TestGenerate
{
    @Test
    public void javaCodeTest() throws Exception
    {
        new MapperJavaCodeGenerate(User.class).generate();
    }

    @Test
    public void xmlCodeTest() throws Exception
    {
        new MapperXmlCodeGenerate(User.class).generate();
    }
}