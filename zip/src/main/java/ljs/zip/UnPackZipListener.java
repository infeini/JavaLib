package ljs.zip;

import java.util.zip.ZipEntry;

public interface UnPackZipListener {
    /**
     * 准备开始解压
     */
    void unPackStart();

    /**
     * 解压进度更新
     *
     * @param zipEntry 正在操作的实体
     * @param did      已完成文件数
     * @param total    总文件数
     */
    void unPackUpdate(ZipEntry zipEntry, int did, int total);

    /**
     * 解压文件发生异常
     *
     * @param zipEntry 正在操作的实体
     * @param e        异常
     */
    void unPackFail(ZipEntry zipEntry, Exception e);

    /**
     * 解压成功
     */
    void unPackSuccess();

    /**
     * 解压结束
     */
    void unPackEnd();
}
