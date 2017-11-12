package test.lib;

import ljs.io.IOUtil;
import ljs.io.file.FileUtils;
import org.junit.Test;

import java.io.File;
import java.io.FileOutputStream;
import java.io.OutputStream;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class Md5UtilTest
{
    Map<String, List<File>> map = new HashMap<>();

    @Test
    public void test() throws Exception
    {
        List<File> files = FileUtils.list(new File("D:\\"));
        for (File file : files)
        {
            String md5 = ljs.lib.Md5Util.getMd5(file);
            List<File> md5Files = map.get(md5);
            if (md5Files == null)
            {
                md5Files = new ArrayList<>();
                map.put(md5, md5Files);
            }
            md5Files.add(file);
        }
        StringBuffer buffer = new StringBuffer();
        for (String key : map.keySet())
        {
            List<File> filesTemp = map.get(key);
            if (filesTemp.size() == 1)
                continue;
            buffer.append("md5:" + key + "\n");
            for (File fileTemp : filesTemp)
                buffer.append("\t" + fileTemp.getAbsolutePath() + "\n");
            buffer.append("\n\n");
        }
        {
            OutputStream out = new FileOutputStream(new File("1.txt"), false);
            out.write(buffer.toString().getBytes("UTF-8"));
            IOUtil.close(out);
        }
    }
}
