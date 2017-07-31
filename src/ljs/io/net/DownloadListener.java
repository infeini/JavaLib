package ljs.io.net;

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
    void onUpdate(long did, long total);

    /**
     * 下载成功
     */
    void onOk();

    /**
     * 下载失败
     *
     * @param e 异常
     */
    void onFail(Exception e);

    /**
     * 下载结束
     */
    void onEnd();
}
