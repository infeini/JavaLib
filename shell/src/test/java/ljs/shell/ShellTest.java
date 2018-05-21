package ljs.shell;

import ljs.exception.KnowException;
import ljs.lib.ListUtils;
import ljs.os.OsUtils;
import ljs.task.ThreadUtil;
import org.junit.Before;
import org.junit.Test;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class ShellTest {
    Shell shell;

    @Before
    public void setUp() throws KnowException, IOException, InterruptedException {
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
        shell = new Shell(initCmd);
    }

    void exeCmd(String[] cmds) throws KnowException, InterruptedException {
        shell.execute(new Command(cmds) {
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
                System.err.println("中断:" + line);
            }

            @Override
            public void commandFinish() {
                System.out.println("finish");
                synchronized (ShellTest.this) {
                    ShellTest.this.notifyAll();
                }
            }
        });
        synchronized (this) {
            wait();
        }
    }

    boolean havePom(File dir) {
        boolean havePom = false;
        if (dir.isDirectory())
            for (File file : dir.listFiles()) {
                if (file.isFile() && "pom.xml".equals(file.getName().toLowerCase())) {
                    havePom = true;
                    break;
                }
            }
        return havePom;
    }

    @Test
    public void mvnCleanInstall() throws Exception {
        List<String> cmds = new ArrayList<>();
        for (File file : getProjectHome()) {
            //cd path
            cmds.add("cd " + file.getAbsolutePath());
            cmds.add("echo 进入目录:" + file.getName());
            //mvn clean
            cmds.add("echo 开始清理编译缓存:" + file.getName());
            cmds.add("mvn clean");
            cmds.add("echo 开始安装至本地Maven仓库:" + file.getName());
            //install
            cmds.add("mvn source:jar install");
            cmds.add("echo 安装完成:" + file.getName());
        }
        exeCmd(ListUtils.toArray(cmds, String.class));
    }

    List<File> getProjectHome() {
        List<File> homes = new ArrayList<>();
        File baseHome = new File("").getAbsoluteFile().getParentFile();
        for (File file : baseHome.listFiles()) {
            if (havePom(file))
                homes.add(file);
        }
        return homes;
    }
}
