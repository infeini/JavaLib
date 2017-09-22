package test.io.file;


import ljs.io.file.FileUtils;
import org.junit.Test;

import java.io.File;
import java.io.IOException;
import java.util.List;

public class FilesUtilTest
{
    @Test
    public void list() throws IOException
    {
        List<File> files = FileUtils.list(new File("./src"));
        for (File file : files)
            System.out.println(file.getAbsolutePath());
    }

    @Test
    public void getRelativePath() throws IOException
    {
        File rootDir = new File(".");
        File targetPath = new File("src/test.ljs.io.IOUtil.java");
        String str = FileUtils.getRelativePath(rootDir, targetPath);
        System.out.println(str);
    }

    @Test
    public void test() throws Exception
    {
        File testZip = new File("D:\\Users\\LiuJiangshan\\Desktop\\File\\ZLX\\training_web_api\\Training\\out\\artifacts\\Training_war_exploded\\html\\34");
        System.out.println(testZip.exists());
        FileUtils.cleanDir(testZip);
    }

    @Test
    public void delete() throws Exception
    {
        FileUtils.delete(new File("D:\\Users\\LiuJiangshan\\Desktop\\a.zip"));
    }

    @Test
    public void cleanDir() throws Exception
    {
        FileUtils.cleanDir(new File("D:\\Users\\LiuJiangshan\\Desktop\\File\\ZLX\\training_web_api\\Training\\out\\artifacts\\Training_war_exploded\\html\\34"));
    }

    @Test
    public void copyFileOrDir() throws IOException
    {
        File copyForm = new File("D:\\Users\\LiuJiangshan\\Desktop\\html模板\\MB1\\from\\Model.html");
        File copyTo = new File("D:\\Users\\LiuJiangshan\\Desktop\\html模板\\MB1\\to\\testd\\Model.html");
        FileUtils.copyFileOrDir(copyForm, copyTo);
    }

    @Test
    public void getFile() throws Exception
    {
        String[] ary = getClass().getPackage().getName().split("\\.");
        File file = FileUtils.getFile(new File("./test"), ary);
        System.out.println(file);
    }

    @Test
    public void getNameNoSuffix() throws Exception
    {
        File file = new File("IOUtil.java");
        System.out.println(FileUtils.getNameNoSuffix(file));
    }

    @Test
    public void getSuffix() throws Exception
    {
        System.out.println(FileUtils.getSuffix(".sdasm.mp4"));
        System.out.println(FileUtils.getSuffix(".sdasm.mp4.doc"));
        System.out.println(FileUtils.getSuffix(".sdasm....ps"));
    }
}
