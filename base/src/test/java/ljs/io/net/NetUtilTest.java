package ljs.io.net;

import org.junit.Test;

import java.io.File;

public class NetUtilTest {
    @Test
    public void downloadTest() {
        DownloadListener downloadListener = new DownloadListener() {
            @Override
            public void downloadStart() {
                System.out.println("准备开始下载文件");
            }

            @Override
            public void downloadUpdate(long did, long total) {
                System.out.println("已下载:" + did / 1024 + " KB,剩余:" + (total - did) / 1024 + " KB");
            }

            @Override
            public void downloadSuccess() {
                System.out.println("ok");
            }

            @Override
            public void downloadFail(Exception e) {
                System.out.println("fail:" + e.getMessage());
            }

            @Override
            public void downloadEnd() {
                System.out.println("end");
            }
        };
        HttpUtil.downloadHttp("https://github.com/LiuJiangshan/hex/raw/master/ls/0/arm64-v8a/ls", new File("update.zip"), downloadListener);
        System.out.println("任务添加完成");
    }
}
