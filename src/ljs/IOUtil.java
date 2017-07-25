package ljs;

import java.io.*;

public class IOUtil
{
    /**
     * 从文件读取对象
     *
     * @param file 读取目标文件路径
     * @return 反序列化后的对象, 失败返回null
     */
    public static Object toObj(File file)
    {
        Object result = null;
        if (file == null) ;
        else
        {
            ObjectInputStream in = null;
            try
            {
                in = new ObjectInputStream(new FileInputStream(file));
                result = in.readObject();
            } catch (Exception e)
            {
                e.printStackTrace();
            } finally
            {
                close(in);
            }
        }
        return result;
    }

    /**
     * 将对象写入文件
     *
     * @param object 待写入的对象
     * @param toFile 写入文件路径
     * @return 成功返回true, 否则返回false
     */
    public static boolean toFile(Object object, File toFile)
    {
        boolean result = false;
        ObjectOutputStream out = null;
        try
        {
            out = new ObjectOutputStream(new FileOutputStream(toFile));
            out.writeObject(object);
            out.flush();
        } catch (Exception e)
        {
            e.printStackTrace();
        } finally
        {
            close(out);
        }
        return result;
    }

    /**
     * 关闭资源
     *
     * @param closeable 待关闭的资源
     */
    public static void close(Closeable closeable)
    {
        if (closeable != null)
            try
            {
                closeable.close();
            } catch (IOException e)
            {
                e.printStackTrace();
            }
    }
}
