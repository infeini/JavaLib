package test.io.file;

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
        List<File> files = ljs.io.file.FilesUtil.list(new File("./src"));
    }

    @Test
    public void getRelativePath() throws IOException
    {
        File rootDir = new File(".");
        File targetPath = new File("src/test.io.IOUtil.java");
        String str = ljs.io.file.FilesUtil.getRelativePath(rootDir, targetPath);
        System.out.println(str);
    }

    @Test
    public void test() throws Exception
    {
        List<File> files = ljs.io.file.FilesUtil.list(new File("out", "test"));
    }
}
