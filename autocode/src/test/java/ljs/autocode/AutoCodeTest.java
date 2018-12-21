package ljs.autocode;

import org.junit.Test;

public class AutoCodeTest {
    @Test
    public void autoCodePlmWeb() throws Exception {
        AutoCode.run("plm", "localhost", "root", "123456", "zlx");
    }

    @Test
    public void autoCodePLmAndroid() throws Exception {
        AutoCode.run("plm", "localhost", "root", "123456", "com.infeini.plm");
    }

    @Test
    public void autoCodeBaoCheHui() throws Exception {
        AutoCode.run("baochehui", "192.168.1.91", "root", "123456", "zlx");
    }

    @Test
    public void autoCodeTraining() throws Exception {
        AutoCode.run("training");
    }

    @Test
    public void autoSpringSecurity() throws Exception {
        AutoCode.run("spring_security");
    }
}
