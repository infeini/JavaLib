package test;

import org.junit.jupiter.api.Test;

import java.io.File;
import java.io.IOException;

public class ZipUtil
{
    @Test
    public void toZip() throws IOException
    {
        ljs.io.file.ZipUtil.toZip(new File("D:\\Users\\LiuJiangshan\\Desktop\\ZIP文件"), new File("D:\\Users\\LiuJiangshan\\Desktop\\ZIP文件.zip"));
    }

    @Test
    public void unZip() throws IOException
    {
        ljs.io.file.ZipUtil.unZip(new File("D:\\Users\\LiuJiangshan\\Desktop\\update.zip"), new File("D:\\Users\\LiuJiangshan\\Desktop\\update"));
    }
}
