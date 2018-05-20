package ljs.autocode;

import org.junit.Test;

public class AutoCodeTest {
    AutoCode autoCode = new AutoCode();

    @Test
    public void autoCodePlmWeb() throws Exception {
        autoCode.run("plm", "localhost", "root", "123456789", "zlx");
    }

    @Test
    public void autoCodePLmAndroid() throws Exception {
        autoCode.run("plm", "localhost", "root", "123456789", "com.infeini.plm");
    }

    @Test
    public void autoCodeBaoCheHui() throws Exception {
        autoCode.run("baochehui", "192.168.1.91", "root", "123456", "zlx");
    }

    @Test
    public void autoCodeTraining() throws Exception {
        autoCode.run("training");
    }

    @Test
    public void autoSpringSecurity() throws Exception {
        autoCode.run("spring_security");
    }
}
