package ljs;

import java.io.*;
import java.util.List;
import java.util.zip.ZipEntry;
import java.util.zip.ZipInputStream;
import java.util.zip.ZipOutputStream;

public class ZipUtil
{
    /**
     * 解压zip文件
     */
    public static void unZip(File zipFile, File toDir) throws IOException
    {
        zipFile = zipFile.getCanonicalFile();
        toDir = toDir.getCanonicalFile();
        if (toDir.isFile())
            toDir = toDir.getParentFile();

        ZipInputStream in = null;
        OutputStream out = null;
        try
        {
            in = new ZipInputStream(new FileInputStream(zipFile));
            ZipEntry zipEntry = null;
            while ((zipEntry = in.getNextEntry()) != null)
            {
                try
                {
                    File unZipFile = new File(toDir, zipEntry.getName());
                    File parentDir = unZipFile.getParentFile();
                    if (parentDir.exists()) ;
                    else
                        parentDir.mkdirs();

                    if (in.available() > 0)
                    {
                        out = new FileOutputStream(unZipFile);
                        IOUtil.write(in, out);
                    } else
                        unZipFile.mkdirs();
                } finally
                {
                    IOUtil.close(out);
                    in.closeEntry();
                }
            }
        } finally
        {
            IOUtil.close(in);
        }
    }

    /**
     * 压缩zip文件
     *
     * @param fileOrDir 待压缩的文件或文件夹
     * @param toZip     压缩数据存储文件
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
