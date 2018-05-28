package ljs.shell;

import org.junit.Test;

import java.io.IOException;

public class RuntimeTest {
    @Test
    public void testProgress() throws IOException {
        Runtime runtime = Runtime.getRuntime();
        Process process = runtime.exec("bash");
    }
}
