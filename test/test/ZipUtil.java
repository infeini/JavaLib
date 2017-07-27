package test;

import org.junit.jupiter.api.Test;

import java.io.File;
import java.io.IOException;
import java.lang.reflect.Method;
import java.nio.file.FileSystem;

public class ZipUtil
{
    @Test
    public void toZip() throws IOException
    {
        ljs.ZipUtil.toZip(new File("D:\\Users\\LiuJiangshan\\Desktop\\ZIP文件"), new File("D:\\Users\\LiuJiangshan\\Desktop\\ZIP文件.zip"));
    }

    @Test
    public void unZip() throws IOException
    {
        ljs.ZipUtil.unZip(new File("D:\\Users\\LiuJiangshan\\Desktop\\ZIP文件.zip"), new File("D:\\Users\\LiuJiangshan\\Desktop\\"));
    }
}
