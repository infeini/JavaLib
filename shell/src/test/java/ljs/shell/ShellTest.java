package ljs.shell;

import ljs.exception.KnowException;
import ljs.io.IOUtil;
import ljs.lib.ListUtils;
import ljs.task.ThreadUtil;
import org.junit.Before;
import org.junit.Test;

import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.nio.charset.Charset;
import java.util.ArrayList;
import java.util.List;

public class ShellTest {

    Shell shell;

    @Before
    public void setUp() throws KnowException {
        shell = Shell.newShell();
    }

    @Test
    public void test() throws IOException {

        Process process = Runtime.getRuntime().exec("bash");

        OutputStream out = process.getOutputStream();

        InputStream in = process.getInputStream();

        out.write("ls\n".getBytes());
        out.flush();

        out.write("ls\n".getBytes());
        out.flush();

        System.out.println(IOUtil.toString(in, "GBK", false).toString());
        System.out.println(IOUtil.toString(in, "GBK", false).toString());
        System.out.println(IOUtil.toString(in, "GBK", false).toString());

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

    int taskCount = 0;

    @Test
    public void mvnCleanInstall() {

        for (File file : getProjectHome()) {
            List<String> cmds = new ArrayList<>();
            //cd path
            cmds.add("cd " + file.getAbsolutePath());
            cmds.add("echo 进入目录:" + file.getName());
            //mvn clean
            cmds.add("echo 清理构建目录:" + file.getName());
            cmds.add("mvn clean");
            cmds.add("echo 发布至本地Maven仓库:" + file.getName());
            //install
            cmds.add("mvn source:jar install");
            cmds.add("echo 发布完成:" + file.getName());

            new Thread(() -> {
                Shell shell = null;
                try {
                    shell = Shell.newShell();
                } catch (KnowException e) {
                    e.printStackTrace();
                }
                for (String cmd : cmds) {
                    Command command = new Command(cmd) {
                        @Override
                        public void start() {
                            System.err.println("start 😆:" + cmd);
                            taskCount++;
                        }

                        @Override
                        public void out(String line) {
                            System.out.println("cmd out 🚒🚒:" + line);
                        }

                        @Override
                        public void error(String errorLine) {
                            System.err.println("cmd error 😂:" + errorLine);
                        }

                        @Override
                        public void end() {
                            System.err.println("cmd end 😜");
                            taskCount--;
                            ThreadUtil.notify(ShellTest.this);
                        }
                    };
                    try {
                        shell.execute(command);
                    } catch (Exception e) {
                        e.printStackTrace();
                        System.err.println("执行失败 😂");
                        System.exit(0);
                    }
                }
            }).start();
        }
        ThreadUtil.sleep(1000);
        while (taskCount != 0) {
            System.err.println("当前任务数量:" + taskCount + " 😤");
            ThreadUtil.wait(ShellTest.this);
        }
        System.err.println("执行完成 👌");
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
