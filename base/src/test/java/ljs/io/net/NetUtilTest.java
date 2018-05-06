package ljs.io.net;

import java.io.File;

public class NetUtilTest {
    public static void main(String[] args) {
        DownloadListener downloadListener = new DownloadListener() {
            @Override
            public void downloadStart() {
                System.out.println("准备开始下载文件");
            }

            @Override
            public void downloadUpdate(long did, long total) {
                System.out.println("已完成:" + did * 100 / total);
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
        HttpUtil.downloadHttp("http://192.168.1.88:8081/update.zip", new File("update.zip"), 2000, true, downloadListener);
        System.out.println("任务添加完成");
    }
}
