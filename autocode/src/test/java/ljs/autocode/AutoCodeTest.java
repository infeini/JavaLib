package ljs.autocode;

import org.junit.Test;

public class AutoCodeTest {
    AutoCode autoCode = new AutoCode();

    @Test
    public void autoCodePLM() throws Exception {
        autoCode.run("plm", "192.168.1.91", "root", "123456");
    }

    @Test
    public void autoCodeBaoCheHui() throws Exception {
        autoCode.run("baochehui", "192.168.1.91", "root", "123456");
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
