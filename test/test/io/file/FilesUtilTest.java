package test.io.file;


import ljs.io.file.FilesUtil;
import org.junit.Test;

import java.io.File;
import java.io.IOException;
import java.util.List;

public class FilesUtilTest
{
    @Test
    public void list() throws IOException
    {
        List<File> files = ljs.io.file.FilesUtil.list(new File("./src"));
        for (File file : files)
            System.out.println(file.getAbsolutePath());
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
        File testZip = new File("D:\\Users\\LiuJiangshan\\Desktop\\File\\ZLX\\training_web_api\\Training\\out\\artifacts\\Training_war_exploded\\html\\34");
        System.out.println(testZip.exists());
        FilesUtil.cleanDir(testZip);
    }

    @Test
    public void delete() throws Exception
    {
        ljs.io.file.FilesUtil.delete(new File("D:\\Users\\LiuJiangshan\\Desktop\\a.zip"));
    }

    @Test
    public void cleanDir() throws Exception
    {
        FilesUtil.cleanDir(new File("D:\\Users\\LiuJiangshan\\Desktop\\File\\ZLX\\training_web_api\\Training\\out\\artifacts\\Training_war_exploded\\html\\34"));
    }

    @Test
    public void copyFileOrDir() throws IOException
    {
        File copyForm = new File("D:\\Users\\LiuJiangshan\\Desktop\\html模板\\MB1\\from\\Model.html");
        File copyTo = new File("D:\\Users\\LiuJiangshan\\Desktop\\html模板\\MB1\\to\\testd\\Model.html");
        FilesUtil.copyFileOrDir(copyForm, copyTo);
    }

    @Test
    public void getFile() throws Exception
    {
        String packageName = getClass().getPackage().getName();
        System.out.println(packageName);
        String[] fileNames = packageName.split("\\.");
        File file = FilesUtil.getFile(new File("."), fileNames);
        System.out.println(file.getAbsolutePath());
    }
}
