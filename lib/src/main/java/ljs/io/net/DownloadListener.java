package ljs.io.net;

/**
 * 下载进度监听器
 */
public interface DownloadListener {
    /**
     * 准备下载
     */
    void downloadStart();

    /**
     * 进度发生更新
     *
     * @param did   已下载字节数
     * @param total 总字节数
     */
    void downloadUpdate(long did, long total);

    /**
     * 下载成功
     */
    void downloadSuccess();

    /**
     * 下载失败
     *
     * @param e 异常
     */
    void downloadFail(Exception e);

    /**
     * 下载结束
     */
    void downloadEnd();
}
