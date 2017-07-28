package test;

import org.junit.jupiter.api.Test;

import java.io.File;

public class NetUtil
{
    @Test
    public void test()
    {
        ljs.NetUtil.download("http://192.168.1.88:8080/update.zip", new File("update.zip"), new ljs.NetUtil.DownloadListener()
        {
            @Override
            public void update(long did, long total)
            {
                System.out.println("已完成:" + did * 100 / total);
            }
        });
    }
}
