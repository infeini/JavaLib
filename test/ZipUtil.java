import org.junit.jupiter.api.Test;

import java.io.File;
import java.io.IOException;
import java.lang.reflect.Method;
import java.nio.file.FileSystem;

public class ZipUtil
{
    @Test
    public void toZip() throws IOException
    {
        ljs.ZipUtil.toZip(new File("."), new File("..\\ljslib.zip"));
    }

    @Test
    public void unZip() throws IOException
    {
        ljs.ZipUtil.unZip(new File("ljslib.zip"), new File("zip"));
    }
}
