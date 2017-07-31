package ljs.io.net;

import java.io.File;
import java.io.FileOutputStream;
import java.io.InputStream;
import java.io.OutputStream;
import java.net.HttpURLConnection;
import java.net.URL;

import static ljs.io.IOUtil.close;

/**
 * net工具类
 *
 * @author https://github.com/LiuJiangshan
 */
public class HttpUtil
{
    /**
     * 通过http协议下载文件,支持进度显示
     *
     * @param url              网络文件url
     * @param saveAs           本地文件存储路径
     * @param downloadListener 下载进度监听器
     */
    public static void downloadHttp(String url, File saveAs, Integer timeOut, DownloadListener downloadListener)
    {
        if (timeOut == null || timeOut < 0)
            timeOut = 5000;
        HttpURLConnection httpConnection = null;
        OutputStream out = null;
        InputStream in = null;
        try
        {
            out = new FileOutputStream(saveAs);
            httpConnection = (HttpURLConnection) new URL(url).openConnection();
            httpConnection.setConnectTimeout(timeOut);
            httpConnection.setReadTimeout(timeOut);
            int responseCode = httpConnection.getResponseCode();
            if (responseCode != 200)
                if (downloadListener != null)
                    throw new Exception("服务器返回响应码:" + responseCode);
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
                    downloadListener.onUpdate(did, total);
            }
            out.flush();
            if (downloadListener != null)
                downloadListener.onOk();
        } catch (Exception e)
        {
            e.printStackTrace();
            if (downloadListener != null)
                downloadListener.onFail(e);
        } finally
        {
            close(out);
            close(in);
            if (httpConnection != null)
                httpConnection.disconnect();
            if (downloadListener != null)
                downloadListener.onEnd();
        }
    }
}
