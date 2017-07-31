package ljs.io.file;

import ljs.io.IOUtil;

import java.io.*;
import java.util.List;
import java.util.zip.ZipEntry;
import java.util.zip.ZipInputStream;
import java.util.zip.ZipOutputStream;

/**
 * zip文件工具类
 *
 * @author https://github.com/LiuJiangshan
 */
public class ZipUtil
{
    /**
     * 解压zip文件
     *
     * @param in    需要解压的IO流
     * @param toDir 解压到目标目录
     * @param close 是否关闭流
     * @throws IOException 发生IO异常
     */
    public static void unZip(InputStream in, File toDir, boolean close) throws IOException
    {
        toDir = toDir.getCanonicalFile();
        if (toDir.isFile())
            toDir = toDir.getParentFile();

        ZipInputStream zipIn = null;
        OutputStream out = null;
        try
        {
            zipIn = new ZipInputStream(in);
            ZipEntry zipEntry = null;
            while ((zipEntry = zipIn.getNextEntry()) != null)
            {
                try
                {
                    File unZipFile = new File(toDir, zipEntry.getName());
                    File parentDir = unZipFile.getParentFile();
                    if (parentDir.exists()) ;
                    else
                        parentDir.mkdirs();

                    if (zipEntry.isDirectory())
                        unZipFile.mkdirs();
                    else
                    {
                        out = new FileOutputStream(unZipFile);
                        IOUtil.write(in, out);
                    }

                } finally
                {
                    IOUtil.close(out);
                    zipIn.closeEntry();
                }
            }
        } finally
        {
            if (close)
                IOUtil.close(in);
            IOUtil.close(zipIn);
        }
    }

    /**
     * 解压zip文件
     *
     * @param zipFile 需要解压的文件
     * @param toDir   解压到目标目录
     * @throws IOException 发生IO异常
     */
    public static void unZip(File zipFile, File toDir) throws IOException
    {
        unZip(new FileInputStream(zipFile), toDir, true);
    }

    /**
     * 压缩zip文件
     *
     * @param fileOrDir 待压缩的文件或文件夹
     * @param toZip     压缩数据存储文件
     * @throws IOException 发生IO异常
     */
    public static void toZip(File fileOrDir, File toZip) throws IOException
    {
        fileOrDir = fileOrDir.getCanonicalFile();
        toZip = toZip.getCanonicalFile();

        ZipOutputStream out = null;
        try
        {
            out = new ZipOutputStream(new FileOutputStream(toZip, false));
            List<File> files = FilesUtil.list(fileOrDir);
            for (File file : files)
            {
                ZipEntry zipEntry = null;
                FileInputStream in = null;
                try
                {
                    zipEntry = new ZipEntry(FilesUtil.getRelativePath(new File(fileOrDir.getAbsolutePath()).getParentFile(), file));
                    out.putNextEntry(zipEntry);
                    if (file.isFile())
                    {
                        in = new FileInputStream(file);
                        IOUtil.write(in, out);
                    }
                } finally
                {
                    if (out != null)
                        out.closeEntry();
                    IOUtil.close(in);
                }
            }
            out.flush();
        } finally
        {
            IOUtil.close(out);
        }
    }
}
