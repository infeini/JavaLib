package ljs.io;

import org.junit.Test;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.net.URL;

/**
 * IO工具类单元测试
 *
 * @author https://github.com/LiuJiangshan
 */
public class IOUtilTest {
    @Test
    public void toObj() {
        Object objectOfRam = "JAVA";
        File ofFile = new File(new File("target"), "obj");
        IOUtil.toFile(objectOfRam, ofFile);
    }

    @Test
    public void toFileFormObj() {
        Object object = "JAVA";
        File toFile = new File(new File("target"), "obj");
        System.out.println(ljs.io.IOUtil.toFile(object, toFile) ? "序列化成功!" : "序列化失败!");
        toFile.delete();
    }

    @Test
    public void toStringFromFile() throws FileNotFoundException {
        File ofFile = new File("src/test/java/ljs/io/IOUtilTest.java");
        StringBuffer stringBuffer = ljs.io.IOUtil.toString(new FileInputStream(ofFile), "UTF-8", true);
        System.out.println(stringBuffer.toString());
    }

    @Test
    public void toStringFromNetwork() throws Exception {
        URL url = new URL("http://www.baidu.com");
        StringBuffer stringBuffer = ljs.io.IOUtil.toString(url, "gb2312", 5000);
        System.out.println(stringBuffer.toString());
    }
}
