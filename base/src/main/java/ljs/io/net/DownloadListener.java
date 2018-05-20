package ljs.io.net;

/**
 * 下载进度监听器
 */
public abstract class DownloadListener {

    public static final int TOTAL = 0;
    public static final int DID = 1;
    public static final int SPEED = 2;

    /**
     * 准备下载
     */
    abstract void downloadStart();

    /**
     * 进度发生更新
     *
     * @param progress long[]{已下载字节数progress[DownloadListener.TOTAL],总字节数progress[DownloadListener.DID]}
     */
    abstract void downloadUpdate(long[] progress);

    /**
     * 下载成功
     */
    abstract void downloadSuccess();

    /**
     * 下载失败
     *
     * @param e 异常
     */
    abstract void downloadFail(Exception e);

    /**
     * 下载结束
     */
    abstract void downloadEnd();
}
