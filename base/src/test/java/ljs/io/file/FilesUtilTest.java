package ljs.io.file;


import ljs.SingletonHolder;
import ljs.io.IOUtil;
import org.junit.Assert;
import org.junit.Test;

import java.io.File;
import java.io.IOException;
import java.util.List;

public class FilesUtilTest {
    @Test
    public void list() throws IOException {
        List<File> files = FileUtils.list(new File("src"));
        for (File file : files)
            System.out.println(file.getAbsolutePath());
    }

    @Test
    public void deleteTest() throws IOException {
        //删除文件夹测试
        File folder = new File("删除文件夹测试" + SingletonHolder.Random.INSTANCE.nextInt());
        Assert.assertTrue(folder.mkdir());
        Assert.assertTrue(folder.exists());
        FileUtils.delete(folder);
        Assert.assertFalse(folder.exists());

        //删除文件测试
        File file = new File("删除文件测试" + SingletonHolder.Random.INSTANCE.nextInt());
        IOUtil.write("hello", file);
        Assert.assertTrue(file.exists());
        FileUtils.delete(file);
        Assert.assertFalse(file.exists());

        //删除非空文件夹测试
        File noNullFolder = new File("非空文件夹" + SingletonHolder.Random.INSTANCE.nextInt());
        Assert.assertTrue(noNullFolder.mkdir());
        File subFile = new File(noNullFolder, "子文件" + SingletonHolder.Random.INSTANCE.nextInt());
        IOUtil.write("hello", subFile);
        Assert.assertTrue(subFile.exists());
        File subFolder = new File(noNullFolder, "子文件夹" + SingletonHolder.Random.INSTANCE.nextInt());
        Assert.assertTrue(subFolder.mkdir());
        FileUtils.delete(noNullFolder);
        Assert.assertFalse(noNullFolder.exists());
        Assert.assertFalse(subFile.exists());
        Assert.assertFalse(subFolder.exists());
    }

    @Test
    public void cleanDir() throws IOException {
        File folder = new File("待清理文件夹" + SingletonHolder.Random.INSTANCE.nextInt());
        Assert.assertTrue(folder.mkdir());
        for (int i = 0; i < 10; i++) {

            Assert.assertTrue(new File(folder, "子文件夹" + SingletonHolder.Random.INSTANCE.nextInt()).mkdir());

            File file = new File(folder, "子文件" + SingletonHolder.Random.INSTANCE.nextInt());
            IOUtil.write("hello", file);
            Assert.assertTrue(file.exists());
        }
        FileUtils.cleanDir(folder);
        Assert.assertEquals(0, folder.list().length);
        FileUtils.delete(folder);
    }

    @Test
    public void getFile() throws Exception {
        String[] ary = getClass().getPackage().getName().split("\\.");
        File file = FileUtils.getFile(new File("ljs"), ary);
        System.out.println(file);
    }

    @Test
    public void getNameNoSuffix() throws Exception {
        File file = new File("IOUtil.java");
        System.out.println(FileUtils.getNameNoSuffix(file));
    }

    @Test
    public void getSuffix() throws Exception {
        System.out.println(FileUtils.getSuffix(".sdasm.mp4"));
        System.out.println(FileUtils.getSuffix(".sdasm.mp4.doc"));
        System.out.println(FileUtils.getSuffix(".sdasm....ps"));
    }
}
