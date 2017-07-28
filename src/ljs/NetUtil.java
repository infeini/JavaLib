package ljs;

import java.io.File;
import java.io.FileOutputStream;
import java.io.InputStream;
import java.io.OutputStream;
import java.net.HttpURLConnection;
import java.net.URL;

import static ljs.IOUtil.close;

/**
 * net工具类
 *
 * @author https://github.com/LiuJiangshan
 */
public class NetUtil
{
    /**
     * 下载进度监听器
     */
    public interface DownloadListener
    {
        /**
         * 进度发生更新
         *
         * @param did   已下载字节数
         * @param total 总字节数
         */
        void update(long did, long total);
    }

    /**
     * 下载文件,支持进度显示
     *
     * @param url              网络文件url
     * @param saveAs           本地文件存储路径
     * @param downloadListener 下载进度监听器
     * @return 成功:true,失败:false
     */
    public static boolean download(String url, File saveAs, DownloadListener downloadListener)
    {
        boolean result = false;
        HttpURLConnection httpConnection = null;
        OutputStream out = null;
        InputStream in = null;
        try
        {
            out = new FileOutputStream(saveAs);
            httpConnection = (HttpURLConnection) new URL(url).openConnection();
            in = httpConnection.getInputStream();
            byte[] buffer = new byte[1024];
            long total = httpConnection.getContentLength();
            long did = 0L;
            int read;
            while ((read = in.read(buffer)) != -1)
            {
                out.write(buffer, 0, read);
                did += read;
                if (downloadListener != null)
                    downloadListener.update(did, total);
            }
            out.flush();
            result = true;
        } catch (Exception e)
        {
            e.printStackTrace();
        } finally
        {
            close(out);
            close(in);
            if (httpConnection != null)
                httpConnection.disconnect();
        }
        return result;
    }
}
