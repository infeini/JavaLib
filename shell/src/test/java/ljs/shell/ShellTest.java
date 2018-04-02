package ljs.shell;

import org.junit.Test;

public class ShellTest {
    @Test
    public void exeCmdTest() throws Exception {
        System.out.println(System.getProperty("os.name"));
        Shell shell = Shell.newInstance("cmd");
        shell.addCommand(new Command("pwd\necho 你好\ncd ..\nrun\n") {
            @Override
            public void commandOutput(String line) {
                System.out.println(line);
            }
        }, true);
    }
}
