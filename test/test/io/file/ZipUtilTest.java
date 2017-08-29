package test.io.file;

import ljs.io.file.UnPackZipListener;
import ljs.io.file.ZipUtil;
import ljs.task.ThreadUtil;
import org.junit.Test;

import java.io.File;
import java.io.IOException;
import java.util.zip.ZipEntry;
import java.util.zip.ZipFile;

public class ZipUtilTest
{
    @Test
    public void toZip() throws IOException
    {
        ljs.io.file.ZipUtil.toZip(new File("D:\\Users\\LiuJiangshan\\Desktop\\html模板\\MB1"), new File("D:\\Users\\LiuJiangshan\\Desktop\\html模板\\MB1\\MB1.zip"));
    }

    public static void main(String[] args) throws IOException
    {
        UnPackZipListener unPackZipListener = new UnPackZipListener()
        {
            @Override
            public void unPackStart()
            {
                System.out.println("准备解压");
            }

            @Override
            public void unPackUpdate(ZipEntry zipEntry, int did, int total)
            {
                System.out.println("正在解压:" + zipEntry.getName());
            }

            @Override
            public void unPackFail(ZipEntry zipEntry, Exception e)
            {
                System.out.println("解压文件:" + zipEntry.getName() + "失败>" + e.getMessage());
            }

            @Override
            public void unPackSuccess()
            {
                System.out.println("解压成功");
            }

            @Override
            public void unPackEnd()
            {
                System.out.println("任务结束");
            }
        };
        ljs.io.file.ZipUtil.unZip(new File("update.zip"), new File("D:\\Users\\LiuJiangshan\\Desktop\\update"), false, unPackZipListener);
        System.out.println("任务添加完成");
    }

    @Test
    public void getPackFileNumber() throws Exception
    {
        ZipFile zipFile = new ZipFile(new File("update.zip"));
        int size = zipFile.size();
        System.out.println("size:" + size);
    }

    @Test
    public void unZip() throws Exception
    {
        File zipFile = new File("D:\\Users\\LiuJiangshan\\Desktop\\html模板\\MB1\\MB1.zip");
        ZipUtil.unZip(zipFile, zipFile.getParentFile(), true, new UnPackZipListener()
        {
            @Override
            public void unPackStart()
            {
                System.out.println("开始解压");
            }

            @Override
            public void unPackUpdate(ZipEntry zipEntry, int did, int total)
            {
                System.out.println("正在解压文件:" + zipEntry.getName() + ",完成:" + did + ",总共:" + total);
            }

            @Override
            public void unPackFail(ZipEntry zipEntry, Exception e)
            {
                System.out.println("解压文件:" + zipEntry.getName() + "失败:" + e.toString());
            }

            @Override
            public void unPackSuccess()
            {
                System.out.println("解压成功");
            }

            @Override
            public void unPackEnd()
            {
                System.out.println("解压结束");
            }
        });
        while (true)
            ThreadUtil.sleep(1000);
    }
}
