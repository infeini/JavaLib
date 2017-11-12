package ljs.reflect;

import java.io.File;
import java.io.IOException;
import java.nio.file.*;
import java.nio.file.attribute.BasicFileAttributes;
import java.util.ArrayList;
import java.util.List;

public class ClassUtils
{
    /**
     * 通过类文件路径获取类
     */
    public static Class getClass(File classFile)
    {
        if (classFile.getName().endsWith(".class"))
        {
            String classPath = ClassUtils.class.getResource("/").getPath();
            String className = classFile.getAbsolutePath().substring(0, classPath.length());
            try
            {
                return Class.forName(className);
            } catch (ClassNotFoundException e)
            {
                e.printStackTrace();
                return null;
            }
        } else
            return null;
    }

    /**
     * 获取当前classPath路径
     */
    public String getClassPath()
    {
        return getClass().getResource("/").getPath();
    }

    /**
     * 获取包下所有类
     *
     * @param packageName 包名
     * @param scanSub     是否扫描子包
     */
    public static List<Class> getAllClass(String packageName, boolean scanSub)
    {
        List<Class> classes = new ArrayList<>();
        String packagePath = ClassLoader.getSystemClassLoader().getResource(packageName.replaceAll("\\.", "/")).getFile();

        if (scanSub)
            try
            {
                Files.walkFileTree(Paths.get(packagePath), new FileVisitor<Path>()
                {
                    @Override
                    public FileVisitResult preVisitDirectory(Path dir, BasicFileAttributes attrs) throws IOException
                    {
                        return FileVisitResult.CONTINUE;
                    }

                    @Override
                    public FileVisitResult visitFile(Path file, BasicFileAttributes attrs) throws IOException
                    {
                        Class clasz = ClassUtils.getClass(file.toFile());
                        if (clasz != null)
                            classes.add(clasz);
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
                        exc.printStackTrace();
                        return FileVisitResult.CONTINUE;
                    }
                });
            } catch (IOException e)
            {
                e.printStackTrace();
            }
        else
            for (File file : new File(packagePath).listFiles())
            {
                String fileName = file.getName();
                if (fileName.endsWith(".class"))
                {
                    String simpleclassName = fileName.substring(0, fileName.length() - 6);
                    String className = packageName + "." + simpleclassName;
                    try
                    {
                        classes.add(Class.forName(className));
                    } catch (ClassNotFoundException e)
                    {
                        e.printStackTrace();
                    }
                }
            }
        return classes;
    }
}
