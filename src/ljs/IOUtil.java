package ljs;

import java.io.*;
import java.net.HttpURLConnection;
import java.net.URL;

public class IOUtil
{
    /**
     * 从文件读取对象
     *
     * @param ofFile 读取目标文件路径
     * @return 反序列化后的对象, 失败返回null
     */
    public static Object toObj(File ofFile)
    {
        Object result = null;
        if (ofFile == null) ;
        else
        {
            ObjectInputStream in = null;
            try
            {
                in = new ObjectInputStream(new FileInputStream(ofFile));
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
            result = true;
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
     * 将文件转换为string
     *
     * @param ofFile 待转换的文件对象
     * @return 转换后的字符串缓存对象, 转换失败返回null
     */
    public static StringBuffer toString(File ofFile)
    {
        try
        {
            return toString(new FileInputStream(ofFile), true);
        } catch (FileNotFoundException e)
        {
            e.printStackTrace();
            return null;
        }
    }

    /**
     * 将IO流转换为string,完成后不关闭流
     *
     * @param in 待转换的IO流对象
     * @return 转换后的字符串缓存对象
     */

    public static StringBuffer toString(InputStream in)
    {
        return toString(in, false);
    }

    /**
     * 将IO流转换为string
     *
     * @param in    待转换的IO流对象
     * @param close 是否关闭流
     * @return 转换后的字符串缓存对象
     */
    public static StringBuffer toString(InputStream in, boolean close)
    {
        StringBuffer stringBuffer = new StringBuffer();

        if (in == null)
            ;
        else
        {
            BufferedReader reader = new BufferedReader(new InputStreamReader(in));
            String str = null;
            try
            {
                while ((str = reader.readLine()) != null)
                {
                    stringBuffer.append(str);
                    stringBuffer.append("\n");
                }
            } catch (Exception e)
            {
                e.printStackTrace();
            } finally
            {
                if (close)
                    close(reader);
            }
        }
        return stringBuffer;
    }

    /**
     * 读取文本http资源
     *
     * @param url http url路径
     * @return http文本资源, 失败返回null
     */
    public static StringBuffer toString(URL url)
    {
        if (url == null)
            return null;
        else
        {
            HttpURLConnection httpURLConnection = null;
            BufferedReader reader = null;
            try
            {
                httpURLConnection = (HttpURLConnection) url.openConnection();
                return IOUtil.toString(httpURLConnection.getInputStream());
            } catch (Exception e)
            {
                e.printStackTrace();
                return null;
            } finally
            {
                IOUtil.close(reader);
                httpURLConnection.disconnect();
            }
        }
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
