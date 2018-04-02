package ljs.zip;

import ljs.io.IOUtil;
import ljs.io.file.FileUtils;
import org.apache.tools.zip.ZipFile;

import java.io.*;
import java.util.Enumeration;
import java.util.List;
import java.util.zip.ZipEntry;
import java.util.zip.ZipInputStream;
import java.util.zip.ZipOutputStream;

/**
 * zip文件工具类
 *
 * @author https://github.com/LiuJiangshan
 */
public class ZipUtil {
    /**
     * 解压zip文件
     *
     * @param in    需要解压的IO流
     * @param toDir 解压到目标目录
     * @param close 是否关闭流
     * @param join  是否堵塞当前线程
     * @throws IOException 发生IO异常
     */
    public static void unZip(InputStream in, File toDir, boolean close, boolean join, UnPackZipListener unPackZipListener) throws IOException {
        unZip(in, null, toDir, close, join, unPackZipListener);
    }

    /**
     * 解压zip文件
     *
     * @param in    需要解压的IO流
     * @param toDir 解压到目标目录
     * @param close 是否关闭流
     * @param total 总文件数,因不能从流中获取zip包含的文件数量,故需要传入
     * @param join  是否堵塞当前线程
     * @throws IOException 发生IO异常
     * @deprecated 不建议使用, 从IO流中读取zip不支持中文文件名
     */
    public static void unZip(InputStream in, Integer total, File toDir, boolean close, boolean join, UnPackZipListener unPackZipListener) throws IOException {
        if (toDir.isFile())
            toDir = toDir.getParentFile();

        File finalToDir = toDir;
        Runnable unZipRunnable = () ->
        {
            ZipInputStream zipIn = null;
            ZipEntry zipEntry = null;
            try {
                if (unPackZipListener != null)
                    unPackZipListener.unPackStart();

                zipIn = new ZipInputStream(in);

                int did = 0;
                while ((zipEntry = zipIn.getNextEntry()) != null) {
                    if (unPackZipListener != null) {
                        if (total != null)
                            unPackZipListener.unPackUpdate(zipEntry, did++, total);
                    }
                    OutputStream out = null;
                    try {
                        File unZipFile = new File(finalToDir, zipEntry.getName());
                        File parentDir = unZipFile.getParentFile();
                        if (parentDir.exists()) ;
                        else
                            parentDir.mkdirs();

                        if (zipEntry.isDirectory())
                            unZipFile.mkdirs();
                        else {
                            out = new FileOutputStream(unZipFile);
                            IOUtil.write(zipIn, out);
                        }

                    } finally {
                        IOUtil.close(out);
                    }
                }

                if (unPackZipListener != null)
                    unPackZipListener.unPackSuccess();
            } catch (IOException e) {
                if (unPackZipListener != null)
                    unPackZipListener.unPackFail(zipEntry, e);
                e.printStackTrace();
            } finally {
                IOUtil.close(zipIn);
                if (close)
                    IOUtil.close(in);

                if (unPackZipListener != null)
                    unPackZipListener.unPackEnd();
            }
        };
        if (join)
            unZipRunnable.run();
        else
            new Thread(unZipRunnable).start();
    }

    /**
     * 解压zip文件
     *
     * @param zipFile 需要解压的文件
     * @param toDir   解压到目标目录
     * @throws IOException 发生IO异常
     */
    public static void unZip(File zipFile, File toDir, boolean join, UnPackZipListener unPackZipListener) throws IOException {
        if (!toDir.exists())
            toDir.mkdirs();
        int size = 0;
        ZipFile zFile = null;
        try {
            zFile = new ZipFile(zipFile, "GBK");
            Enumeration<org.apache.tools.zip.ZipEntry> entryEnumeration = zFile.getEntries();
            while (entryEnumeration.hasMoreElements()) {
                org.apache.tools.zip.ZipEntry zipEntry = entryEnumeration.nextElement();
                String entryName = zipEntry.getName();
                File toFile = new File(toDir, entryName);
                toFile.getParentFile().mkdirs();
                if (zipEntry.isDirectory())
                    toFile.mkdirs();
                else {
                    InputStream in = zFile.getInputStream(zipEntry);
                    OutputStream out = new FileOutputStream(toFile);
                    try {
                        IOUtil.write(in, out);
                    } finally {
                        IOUtil.close(in);
                        IOUtil.close(out);
                    }
                }
            }
        } finally {
            //关闭，不然流不会关闭，将不能删除该文件
            zFile.close();
        }
    }

    /**
     * 压缩zip文件
     *
     * @param fileOrDir 待压缩的文件或文件夹
     * @param toZip     压缩数据存储文件
     * @throws IOException 发生IO异常
     */
    public static void toZip(File fileOrDir, File toZip) throws IOException {
        toZip(new File[]{fileOrDir}, toZip);
    }
    //    {
//        fileOrDir = fileOrDir.getCanonicalFile();
//        toZip = toZip.getCanonicalFile();
//
//        ZipOutputStream out = null;
//        try
//        {
//            List<File> files = FileUtils.list(fileOrDir);
//            out = new ZipOutputStream(new FileOutputStream(toZip, false));
//            for (File file : files)
//            {
//                ZipEntry zipEntry = null;
//                FileInputStream in = null;
//                try
//                {
//                    zipEntry = new ZipEntry(FileUtils.getRelativePath(new File(fileOrDir.getAbsolutePath()).getParentFile(), file));
//                    out.putNextEntry(zipEntry);
//                    if (file.isFile())
//                    {
//                        in = new FileInputStream(file);
//                        IOUtil.write(in, out);
//                    }
//                } finally
//                {
//                    if (out != null)
//                        out.closeEntry();
//                    IOUtil.close(in);
//                }
//            }
//            out.flush();
//        } finally
//        {
//            IOUtil.close(out);
//        }
//    }

    public static void toZip(File[] fileOrDirs, File toZip) throws IOException {
        toZip = toZip.getCanonicalFile();

        ZipOutputStream out = null;
        try {
            out = new ZipOutputStream(new FileOutputStream(toZip, false));
            for (File fileOrDir : fileOrDirs) {
                fileOrDir = fileOrDir.getCanonicalFile();

                List<File> files = FileUtils.list(fileOrDir);
                for (File file : files) {
                    ZipEntry zipEntry;
                    FileInputStream in = null;
                    try {
                        zipEntry = new ZipEntry(FileUtils.getRelativePath(new File(fileOrDir.getAbsolutePath()).getParentFile(), file).replaceAll("\\\\", "/"));
                        out.putNextEntry(zipEntry);
                        if (file.isFile()) {
                            in = new FileInputStream(file);
                            IOUtil.write(in, out);
                        }
                    } finally {
                        if (out != null)
                            out.closeEntry();
                        IOUtil.close(in);
                    }
                }
                out.flush();
            }
        } finally {
            IOUtil.close(out);
        }
    }
}
