package ljs.zip;

import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.util.zip.ZipEntry;
import java.util.zip.ZipFile;

public class ZipUtilTest {
    File tempFolder = new File("target");
    File tempFile = new File(tempFolder, "a.zip");

    @Before
    public void setUp() throws IOException {
        if (!tempFolder.exists()) Assert.assertTrue(tempFolder.mkdir());
        ZipUtil.toZip(new File("src").listFiles(), tempFile);
    }

    @Test
    public void unzip() throws Exception {
        UnPackZipListener unPackZipListener = new UnPackZipListener() {
            @Override
            public void unPackStart() {
                System.out.println("准备解压");
            }

            @Override
            public void unPackUpdate(ZipEntry zipEntry, int did, int total) {
                System.out.println("正在解压:" + zipEntry.getName());
            }

            @Override
            public void unPackFail(ZipEntry zipEntry, Exception e) {
                System.out.println("解压文件:" + zipEntry.getName() + "失败>" + e.getMessage());
            }

            @Override
            public void unPackSuccess() {
                System.out.println("解压成功");
            }

            @Override
            public void unPackEnd() {
                System.out.println("任务结束");
            }
        };
        ZipUtil.unZip(tempFile, tempFolder, true, unPackZipListener);
    }

    @Test
    public void unzipStream() throws Exception {
        ZipUtil.unZip(new FileInputStream(tempFile), 1, tempFolder, true, true, null);
    }

    @Test
    public void getPackFileNumber() throws Exception {
        ZipFile zipFile = new ZipFile(tempFile);
        int size = zipFile.size();
        System.out.println("size:" + size);
    }

    @Test
    public void unZip() throws Exception {
        ZipUtil.unZip(tempFile, tempFolder, true, new UnPackZipListener() {
            @Override
            public void unPackStart() {
                System.out.println("开始解压");
            }

            @Override
            public void unPackUpdate(ZipEntry zipEntry, int did, int total) {
                System.out.println("正在解压文件:" + zipEntry.getName() + ",完成:" + did + ",总共:" + total);
            }

            @Override
            public void unPackFail(ZipEntry zipEntry, Exception e) {
                System.out.println("解压文件:" + zipEntry.getName() + "失败:" + e.toString());
            }

            @Override
            public void unPackSuccess() {
                System.out.println("解压成功");
            }

            @Override
            public void unPackEnd() {
                System.out.println("解压结束");
            }
        });
    }
}
