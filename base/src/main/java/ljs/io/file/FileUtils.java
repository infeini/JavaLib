package ljs.io.file;

import ljs.exception.KnowException;
import ljs.io.IOUtil;

import java.io.*;
import java.nio.file.*;
import java.nio.file.attribute.BasicFileAttributes;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.util.ArrayList;
import java.util.List;

/**
 * file工具类
 *
 * @author https://github.com/LiuJiangshan
 */
public class FileUtils {
    /**
     * 获取一个路径相对于另一个路径的相对路径
     *
     * @param rootDir    根路径
     * @param targetPath 需要计算的路径
     * @return 返回相对路径
     * @throws IOException 发生IO异常
     */
    public static String getRelativePath(File rootDir, File targetPath) throws IOException {
        rootDir = rootDir.getCanonicalFile();
        targetPath = targetPath.getCanonicalFile();
        if (rootDir.isFile())
            rootDir = rootDir.getParentFile();

        String relativePath;
        if (rootDir.getAbsolutePath().equals(targetPath.getAbsolutePath()))
            relativePath = "";
        else {
            relativePath = targetPath.getAbsolutePath();
            relativePath = relativePath.substring(rootDir.getAbsolutePath().length() + 1, relativePath.length());
        }
        return relativePath;
    }

    /**
     * 遍历路径下所有的文件和空文件夹
     *
     * @param dir 待遍历的路径
     * @return 该路径下所有的文件和空文件夹
     * @throws IOException 发生IO异常
     */
    public static List<File> list(File dir) throws IOException {
        List<File> files = new ArrayList<>();
        if (dir.isFile()) {
            files.add(dir);
            return files;
        }
        dir = dir.getCanonicalFile();

        FileVisitor finder = new FileVisitor<Path>() {
            @Override
            public FileVisitResult preVisitDirectory(Path dirPath, BasicFileAttributes attrs) throws IOException {
                File dirFile = dirPath.toFile();
                File[] dirFiles = dirFile.listFiles();
                if (files == null) ;
                else if (dirFile.getAbsolutePath().equals(dirPath.toFile().getAbsolutePath())) ;
                else if (dirFiles.length == 0)
                    files.add(dirFile);
                return FileVisitResult.CONTINUE;
            }

            @Override
            public FileVisitResult visitFile(Path file, BasicFileAttributes attrs) throws IOException {
                files.add(file.toFile());
                return FileVisitResult.CONTINUE;
            }

            @Override
            public FileVisitResult visitFileFailed(Path file, IOException e) throws IOException {
                if (e != null)
                    e.printStackTrace();
                return FileVisitResult.CONTINUE;
            }

            @Override
            public FileVisitResult postVisitDirectory(Path dir, IOException e) throws IOException {
                if (e != null)
                    e.printStackTrace();
                return FileVisitResult.CONTINUE;
            }
        };
        Files.walkFileTree(Paths.get(dir.getAbsolutePath()), finder);
        return files;
    }

    /**
     * 递归删除文件或文件夹
     *
     * @param fileOrDir 要删除的文件或文件夹
     */
    public static void delete(File fileOrDir) {
        if (fileOrDir == null || !fileOrDir.exists())
            return;
        if (fileOrDir.isDirectory()) {
            File[] files = fileOrDir.listFiles();
            for (File f : files)
                delete(f);
        }
        fileOrDir.delete();
    }

    /**
     * 清空文件夹
     *
     * @param dir 需要清空的文件夹
     */
    public static void cleanDir(File dir) {
        if (dir == null || !dir.exists())
            return;
        for (File file : dir.listFiles())
            delete(file);
    }

    /**
     * 递归复制文件或文件夹
     *
     * @param from 原文件
     * @param to   复制目标路径
     * @throws IOException
     */
    public static void copyFileOrDir(File from, File to) throws IOException {
        if (from == null || !from.exists())
            return;
        if (from.isDirectory()) {
            to.mkdirs();
            File[] files = from.listFiles();
            for (File f : files)
                copyFileOrDir(f, new File(to, f.getName()));
        } else {
            File toDir = to.getParentFile();
            if (!toDir.exists())
                toDir.mkdirs();
            Files.copy(from.toPath(), to.toPath(), StandardCopyOption.REPLACE_EXISTING);
        }
    }

    /**
     * 拼接文件路径
     *
     * @param preFixFile 前缀文件路径
     * @param fileNames  拼接路径数组
     */
    public static File getFile(File preFixFile, String... fileNames) {
        File file = preFixFile == null ? new File(".") : preFixFile;
        if (fileNames.length == 0) ;
        else {
            for (String fileName : fileNames)
                file = new File(file, fileName);
        }
        return file;
    }

    /**
     * 获取文件不含后缀的名称
     *
     * @param file 文件
     * @return 不含后缀的文件名
     */
    public static String getNameNoSuffix(File file) {
        String name = file.getName();
        int index = name.lastIndexOf(".");
        if (index != -1)
            name = name.substring(0, index);
        return name;
    }

    /**
     * 获取文件后缀名,不包括.
     *
     * @param fileName 文件名称
     * @return 后缀名, 不存在返回null
     */
    public static String getSuffix(String fileName) {
        String suffix = null;
        int index = fileName.lastIndexOf('.');
        if (index == -1) ;
        else
            suffix = fileName.substring(index + 1, fileName.length());
        return suffix;
    }

    public static String getHash(File file) throws KnowException {
        try {
            return getHash(new FileInputStream(file), true);
        } catch (FileNotFoundException e) {
            throw new KnowException("");
        }
    }

    public static String getHash(InputStream in, boolean close) throws KnowException {
        try {
            String filehash = "";
            MessageDigest md = null;
            try {
                md = MessageDigest.getInstance("SHA-1");
            } catch (NoSuchAlgorithmException e) {
                e.printStackTrace();
            }
            byte[] buffer = new byte[1024];
            int numRead;
            do {
                numRead = in.read(buffer);
                if (numRead > 0) {
                    md.update(buffer, 0, numRead);
                }
            } while (numRead != -1);
            in.close();
            byte[] digest = md.digest();
            for (int i = 0; i < digest.length; i++)
                filehash += Integer.toString((digest[i] & 0xff) + 0x100, 16).substring(1);
            return filehash != null ? filehash.toLowerCase() : filehash;
        } catch (IOException e) {
            throw new KnowException("读取文件流失败:" + e.getMessage());
        } finally {
            if (close)
                IOUtil.close(in);
        }
    }
}
