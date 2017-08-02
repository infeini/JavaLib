package ljs.io;

import java.io.*;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.concurrent.TimeoutException;

/**
 * IO工具类
 *
 * @author https://github.com/LiuJiangshan
 */
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
     * @return 成功:true,失败:false
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
     * @param ofFile   待转换的文件对象
     * @param encoding 编码方式
     * @return 转换后的字符串缓存对象, 转换失败返回null
     */
    public static StringBuffer toString(File ofFile, String encoding)
    {
        try
        {
            return toString(new FileInputStream(ofFile), encoding, true);
        } catch (FileNotFoundException e)
        {
            e.printStackTrace();
            return null;
        }
    }

    /**
     * 将IO流转换为string
     *
     * @param in       待转换的IO流对象
     * @param encoding 编码方式
     * @param close    是否关闭流
     * @return 转换后的字符串缓存对象
     */
    public static StringBuffer toString(InputStream in, String encoding, boolean close)
    {
        StringBuffer stringBuffer = new StringBuffer();

        if (in == null) ;
        else
        {
            BufferedReader reader = null;
            String str = null;
            try
            {
                reader = new BufferedReader(new InputStreamReader(in, encoding));
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
     * @param url      http url路径
     * @param encoding 编码方式
     * @param timeOut  超时时间(毫秒)
     * @return http文本资源, 失败返回null
     * @throws IOException 异常
     */
    public static StringBuffer toString(URL url, String encoding, Integer timeOut) throws IOException
    {
        if (timeOut == null || timeOut < 0)
            timeOut = 5000;
        if (url == null)
            return null;
        else
        {
            HttpURLConnection httpURLConnection = null;
            BufferedReader reader = null;
            try
            {
                httpURLConnection = (HttpURLConnection) url.openConnection();
                httpURLConnection.setConnectTimeout(timeOut);
                httpURLConnection.setReadTimeout(timeOut);
                int responseCode = httpURLConnection.getResponseCode();
                if (responseCode != HttpURLConnection.HTTP_OK)
                    throw new IOException("服务器响应码:" + responseCode);
                return IOUtil.toString(httpURLConnection.getInputStream(), "UTF-8", true);
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

    /**
     * 将输入流写入输出流
     *
     * @param in  输入流
     * @param out 输出流
     * @throws IOException 发生IO异常
     */
    public static void write(InputStream in, OutputStream out) throws IOException
    {
        byte[] buffer = new byte[2048];
        int total;
        while ((total = in.read(buffer)) != -1)
        {
            out.write(buffer, 0, total);
        }
        out.flush();
    }
}
