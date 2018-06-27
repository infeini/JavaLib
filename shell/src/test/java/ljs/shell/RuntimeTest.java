package ljs.shell;

import org.junit.Before;
import org.junit.Test;

import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;

public class RuntimeTest {

    Process process;

    @Before
    public void setUp() throws IOException {

        Runtime runtime = Runtime.getRuntime();

        process = runtime.exec("bash");
    }

    @Test
    public void test1() {

        System.out.println(process.getInputStream() == process.getInputStream());

    }

    @Test
    public void testProgress() throws IOException {

        InputStream in = process.getInputStream();

        OutputStream out = process.getOutputStream();

        out.write("ls\n".getBytes());
        out.flush();

        byte[] bs = new byte[2048];

        int read = in.read(bs);

        System.out.println(new String(bs, 0, read));
    }
}
