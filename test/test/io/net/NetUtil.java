package test.io.net;

import ljs.io.net.DownloadListener;
import ljs.io.net.HttpUtil;
import org.junit.jupiter.api.Test;

import java.io.File;

public class NetUtil
{
    @Test
    public void test()
    {
        HttpUtil.downloadHttp("http://192.168.1.88:8080/update.zip", new File("update.zip"), 2000, new DownloadListener()
        {
            @Override
            public void onUpdate(long did, long total)
            {
                System.out.println("已完成:" + did * 100 / total);
            }

            @Override
            public void onOk()
            {
                System.out.println("ok");
            }

            @Override
            public void onFail(Exception e)
            {
                System.out.println("fail:" + e.getMessage());
            }

            @Override
            public void onEnd()
            {
                System.out.println("end");
            }
        });
    }
}
