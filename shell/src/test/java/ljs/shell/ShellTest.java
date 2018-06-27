package ljs.shell;

import ljs.exception.KnowException;
import ljs.io.IOUtil;
import ljs.lib.ListUtils;
import org.junit.Before;
import org.junit.Test;

import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
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

    int count;

    @Test
    public void mvnCleanInstall() throws Exception {
        for (File file : getProjectHome()) {
            List<String> cmds = new ArrayList<>();
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
            new Thread(() -> {
                try {
                    count++;
                    count--;
                    synchronized ((ShellTest.this)) {
                        ShellTest.this.notifyAll();
                    }
                } catch (Exception e) {
                    e.printStackTrace();
                    System.out.println(e);
                }
            }).start();
        }
        while (count != 0) {
            synchronized (this) {
                wait();
            }
        }
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
