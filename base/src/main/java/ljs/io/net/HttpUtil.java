package ljs.io.net;

import ljs.exception.KnowException;
import ljs.task.ThreadUtil;

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
public class HttpUtil {

    /**
     * 通过http协议下载文件,支持进度监听
     *
     * @param url              网络文件url
     * @param saveAs           本地文件存储路径
     * @param downloadListener 下载进度监听器
     */
    public static void downloadHttp(String url, File saveAs, DownloadListener downloadListener) {
        downloadHttp(url, saveAs, 5000, downloadListener);
    }

    /**
     * 通过http协议下载文件,支持进度监听
     *
     * @param url              网络文件url
     * @param saveAs           本地文件存储路径
     * @param timeOut          超时时间
     * @param downloadListener 下载进度监听器
     */
    public static void downloadHttp(String url, File saveAs, int timeOut, DownloadListener
            downloadListener) {
        if (timeOut < 0) timeOut = 5000;
        int finalTimeOut = timeOut;

        if (downloadListener != null)
            downloadListener.downloadStart();
        HttpURLConnection httpConnection = null;
        OutputStream out = null;
        InputStream in = null;
        try {
            out = new FileOutputStream(saveAs);
            httpConnection = (HttpURLConnection) new URL(url).openConnection();
            httpConnection.setConnectTimeout(finalTimeOut);
            httpConnection.setReadTimeout(finalTimeOut);
            int responseCode = httpConnection.getResponseCode();
            if (responseCode != HttpURLConnection.HTTP_OK)
                if (downloadListener != null)
                    throw new KnowException("服务器返回响应码:" + responseCode);
            in = httpConnection.getInputStream();
            byte[] buffer = new byte[2048];
            long[] progress = {httpConnection.getContentLength(), 0};
            final int total = 0;
            final int did = 1;
            int read;
            Thread progressThread = new Thread(() -> {
                while (true) {
                    if (downloadListener != null) downloadListener.downloadUpdate(progress[did], progress[total]);
                    ThreadUtil.sleep(1000);
                }
            });
            progressThread.join();
            progressThread.start();
            while ((read = in.read(buffer)) != -1) {
                out.write(buffer, 0, read);
                progress[did] += read;
            }
            out.flush();
            if (downloadListener != null) downloadListener.downloadSuccess();
        } catch (Exception e) {
            if (downloadListener != null) downloadListener.downloadFail(e);
        } finally {
            close(out);
            close(in);
            if (httpConnection != null) httpConnection.disconnect();
            if (downloadListener != null) downloadListener.downloadEnd();
        }
    }
}
