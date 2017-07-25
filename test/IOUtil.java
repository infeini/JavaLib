import org.junit.jupiter.api.Test;

import java.io.*;
import java.lang.reflect.Field;
import java.net.MalformedURLException;
import java.net.URL;

public class IOUtil
{
    @Test
    public void toObj()
    {
        Object objectOfRam = "JAVA";
        Object objectOfFile = null;

        File ofFile = new File("obj");
        if (ljs.IOUtil.toFile(objectOfRam, ofFile))
            System.out.println(ljs.IOUtil.toObj(ofFile) == null ? "反序列化成功!" : "反序列化成功!");
        ofFile.delete();
    }

    /**
     * 将对象写入文件
     */
    @Test
    public void toFile()
    {
        Object object = "JAVA";
        File toFile = new File("obj");
        System.out.println(ljs.IOUtil.toFile(object, toFile) ? "序列化成功!" : "序列化失败!");
        toFile.delete();
    }


    /**
     * 将文件转换为string
     */
    @Test
    public void toString1()
    {
        File ofFile = new File("test/IOUtil.java");
        StringBuffer stringBuffer = ljs.IOUtil.toString(ofFile);
        System.out.println(stringBuffer.toString());
    }

    /**
     * 将IO流转换为string
     */
    @Test
    public void toString2() throws FileNotFoundException
    {
        File ofFile = new File("test/IOUtil.java");
        StringBuffer stringBuffer = ljs.IOUtil.toString(new FileInputStream(ofFile));
        System.out.println(stringBuffer.toString());
    }

    /**
     * 读取http文本资源
     */
    @Test
    public void toString3() throws Exception
    {
        URL url = new URL("http://www.baidu.com");
        StringBuffer stringBuffer = ljs.IOUtil.toString(url);
        System.out.println(stringBuffer.toString());
    }

    /**
     * 关闭资源
     *
     * @param closeable 待关闭的资源
     */
    public static void close(Closeable closeable)
    {
        if (closeable != null)
            try
            {
                closeable.close();
            } catch (IOException e)
            {
                e.printStackTrace();
            }
    }
}
