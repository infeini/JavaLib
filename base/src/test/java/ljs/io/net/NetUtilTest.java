package ljs.io.net;

import org.junit.Test;

import java.io.File;

public class NetUtilTest {
    @Test
    public void downloadTest() {
        String url = "https://www.baidu.com/img/bd_logo.png";
        File saveAs = new File(new File("target"), "bd_logo.png");
        DownloadListener downloadListener = new DownloadListener() {
            @Override
            public void downloadStart() {
                System.out.println("准备开始下载文件");
            }

            @Override
            public void downloadUpdate(long[] progress) {
                System.out.println("已下载:" + progress[DownloadListener.DID] / 1024 + " KB,剩余:" + (progress[DownloadListener.TOTAL] - progress[DownloadListener.DID]) / 1024 + " KB,下载速度:" + progress[DownloadListener.SPEED] / (1024f * 1024f) + " MB/秒");
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
        HttpUtil.downloadHttp(url, saveAs, 250, 5000, downloadListener);
    }
}
