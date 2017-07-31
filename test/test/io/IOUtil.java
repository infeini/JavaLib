package test.io;

import org.junit.jupiter.api.Test;

import java.io.*;
import java.net.URL;

/**
 * IO工具类单元测试
 *
 * @author https://github.com/LiuJiangshan
 */
public class IOUtil
{
    @Test
    public void toObj()
    {
        Object objectOfRam = "JAVA";
        Object objectOfFile = null;

        File ofFile = new File("obj");
        if (ljs.io.IOUtil.toFile(objectOfRam, ofFile))
            System.out.println(ljs.io.IOUtil.toObj(ofFile) == null ? "反序列化成功!" : "反序列化成功!");
        ofFile.delete();
    }

    @Test
    public void toFile()
    {
        Object object = "JAVA";
        File toFile = new File("obj");
        System.out.println(ljs.io.IOUtil.toFile(object, toFile) ? "序列化成功!" : "序列化失败!");
        toFile.delete();
    }


    @Test
    public void toString1()
    {
        File ofFile = new File("test/test.io.IOUtil.java");
        StringBuffer stringBuffer = ljs.io.IOUtil.toString(ofFile, "UTF-8");
        System.out.println(stringBuffer.toString());
    }

    @Test
    public void toString2() throws FileNotFoundException
    {
        File ofFile = new File("test/test.io.IOUtil.java");
        StringBuffer stringBuffer = ljs.io.IOUtil.toString(new FileInputStream(ofFile), "UTF-8", true);
        System.out.println(stringBuffer.toString());
    }

    @Test
    public void toString3() throws Exception
    {
        URL url = new URL("http://www.scbz.hrss.gov.cn/index.php/letter-view-id-2178");
        StringBuffer stringBuffer = ljs.io.IOUtil.toString(url, "gb2312", 5000);
        System.out.println(stringBuffer.toString());
    }

    @Test
    public static void close()
    {
    }
}
