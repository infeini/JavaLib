package test;

import org.junit.jupiter.api.Test;

import java.io.File;
import java.io.IOException;

public class ZipUtil
{
    @Test
    public void toZip() throws IOException
    {
        ljs.io.file.ZipUtil.toZip(new File("D:\\Users\\LiuJiangshan\\Desktop\\ZIP文件"), new File("D:\\Users\\LiuJiangshan\\Desktop\\ZIP文件.zip"));
    }

    @Test
    public void unZip() throws IOException
    {
        ljs.io.file.ZipUtil.unZip(new File("D:\\Users\\LiuJiangshan\\Desktop\\ZIP文件.zip"), new File("D:\\Users\\LiuJiangshan\\Desktop\\"));
    }

    class Obj
    {
        boolean wait = false;
        Runnable doRunnable = new Runnable()
        {
            @Override
            public void run()
            {
                while (true)
                {
                    if (wait)
                        synchronized (Obj.this)
                        {
                            try
                            {
                                Obj.this.wait();
                            } catch (InterruptedException e)
                            {
                                e.printStackTrace();
                            }
                        }
                }
            }
        };
    }

    @Test
    public void test()
    {
    }
}
