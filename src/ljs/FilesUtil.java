package ljs;

import java.io.File;
import java.io.IOException;
import java.nio.file.*;
import java.nio.file.attribute.BasicFileAttributes;
import java.util.ArrayList;
import java.util.List;

/**
 * file工具类
 *
 * @author https://github.com/LiuJiangshan
 */
public class FilesUtil
{
    /**
     * 获取一个路径相对于另一个路径的相对路径
     *
     * @param rootDir    根路径
     * @param targetPath 需要计算的路径
     * @return 返回相对路径
     */
    public static String getRelativePath(File rootDir, File targetPath) throws IOException
    {
        rootDir = rootDir.getCanonicalFile();
        targetPath = targetPath.getCanonicalFile();
        if (rootDir.isFile())
            rootDir = rootDir.getParentFile();

        String relativePath = null;
        if (rootDir.getAbsolutePath().equals(targetPath.getAbsolutePath()))
            relativePath = "";
        else
        {
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
     */
    public static List<File> list(File dir) throws IOException
    {
        if (dir.isFile())
            dir = dir.getParentFile();
        dir = dir.getCanonicalFile();

        List<File> files = new ArrayList<>();
        FileVisitor finder = new FileVisitor<Path>()
        {
            @Override
            public FileVisitResult preVisitDirectory(Path dirPath, BasicFileAttributes attrs) throws IOException
            {
                File dirFile = dirPath.toFile();
                File[] dirFiles = dirFile.listFiles();
                if (files == null) ;
                else if (dirFile.getAbsolutePath().equals(dirPath.toFile().getAbsolutePath())) ;
                else if (dirFiles.length == 0)
                    files.add(dirFile);
                return FileVisitResult.CONTINUE;
            }

            @Override
            public FileVisitResult visitFile(Path file, BasicFileAttributes attrs) throws IOException
            {
                files.add(file.toFile());
                return FileVisitResult.CONTINUE;
            }

            @Override
            public FileVisitResult visitFileFailed(Path file, IOException exc) throws IOException
            {
                exc.printStackTrace();
                return FileVisitResult.CONTINUE;
            }

            @Override
            public FileVisitResult postVisitDirectory(Path dir, IOException exc) throws IOException
            {
                return FileVisitResult.CONTINUE;
            }
        };
        Files.walkFileTree(Paths.get(dir.getAbsolutePath()), finder);
        return files;
    }
}
