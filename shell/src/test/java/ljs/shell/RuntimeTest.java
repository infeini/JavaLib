package ljs.shell;

import org.junit.Test;

import java.io.IOException;

public class RuntimeTest {
    @Test
    public void testProgress() throws IOException {
        Runtime runtime = Runtime.getRuntime();
        runtime.exec("bash");
        runtime.exec("bash");
    }
}
