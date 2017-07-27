package test;

import org.junit.jupiter.api.Test;

import java.io.File;
import java.io.IOException;
import java.util.List;
import java.util.zip.ZipEntry;

public class FilesUtil
{
    @Test
    public void list() throws IOException
    {
        List<File> files = ljs.FilesUtil.list(new File("./src"));
    }

    @Test
    public void getRelativePath() throws IOException
    {
        File rootDir = new File(".");
        File targetPath = new File("src/test.IOUtil.java");
        String str = ljs.FilesUtil.getRelativePath(rootDir, targetPath);
        System.out.println(str);
    }

    @Test
    public void test()
    {
        ZipEntry zipEntry = new ZipEntry("/test");
    }
}
