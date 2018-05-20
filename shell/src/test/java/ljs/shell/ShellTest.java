package ljs.shell;

import ljs.exception.KnowException;
import ljs.os.OsUtils;
import ljs.task.ThreadUtil;
import org.junit.Test;

public class ShellTest {
    @Test
    public void exeCmdTest() throws Exception {
        String initCmd = null;
        switch (OsUtils.osType) {
            case MAC:
                initCmd = "bash";
                break;
            case LINUX:
                initCmd = "bash";
                break;
            case WINDOWS:
                initCmd = "cmd";
                break;
            case UNKNOW:
                throw new KnowException("未知操作系统");
        }
        Shell shell = new Shell(initCmd);
        shell.execute(new Command(new String[]{"mvn clean", "mvn source:jar install"}) {
            @Override
            public void commandStart() {
                System.out.println("start");
            }

            @Override
            public void commandOutput(String line) {
                System.out.println(line);
            }

            @Override
            public void commandInterrupted(String line) {
                System.out.println("中断:" + line);
            }

            @Override
            public void commandFinish() {
                System.out.println("finish");
                System.exit(0);
            }
        });
        while (true) ThreadUtil.sleep(1000);
    }
}
