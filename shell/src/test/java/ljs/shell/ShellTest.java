package ljs.shell;

import ljs.exception.KnowException;
import ljs.task.SleepHandler;
import ljs.task.ThreadUtil;
import org.junit.Before;
import org.junit.Test;

import java.io.File;
import java.util.ArrayList;
import java.util.List;

public class ShellTest {

    Shell shell;

    @Before
    public void setUp() throws KnowException {
        shell = Shell.newShell();
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
            cmds.add("mvn source:jar install -Dmaven.test.skip=true");
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

    @Test
    public void installAl() throws InterruptedException {
        shell.execute(new Command("cd ..") {

            @Override
            public void out(String line) {
                System.out.println(line);
            }

            @Override
            public void error(String errorLine) {
                System.err.println(errorLine);
            }
        });
        shell.execute(new Command("mvn clean source:jar install -DskipTests") {
            @Override
            public void out(String line) {
                System.out.println(line);
            }

            @Override
            public void error(String errorLine) {
                System.err.println(errorLine);
            }
        });
    }

    @Test
    public void closeShellTest() {
        shell.execute(new Command("ping localhost") {
            @Override
            public void out(String line) {
                System.out.println(line);
            }

            @Override
            public void error(String errorLine) {
                super.error(errorLine);
                System.out.println(errorLine);
            }
        });
        new Thread(() -> {
            ThreadUtil.sleep(2000, laveMillis -> {
                if (laveMillis % 1000 == 0)
                    System.out.println(laveMillis);
            });
            shell.close();
        }).start();
        ThreadUtil.wait(this);
    }

    @Test
    public void executeTest() {
        shell.execute(new Command("ping localhost") {
            @Override
            public void out(String line) {
                System.out.println(line);
            }

            @Override
            public void error(String errorLine) {
                super.error(errorLine);
                System.out.println(errorLine);
            }
        });
        ThreadUtil.wait(this);
    }

    @Test
    public void restartTest() {

        Command command = new Command("ping localhost") {
            @Override
            public void out(String line) {
                System.out.println(line);
                if (line.contains("127")) {
                    shell.close();
                    try {
                        shell = Shell.newShell();
                        System.out.println("重新执行");
                        shell.execute(this);
                    } catch (KnowException e) {
                        System.out.println(e.getMessage());
                    }
                }
            }

            @Override
            public void error(String errorLine) {
                super.error(errorLine);
                System.out.println(errorLine);
            }
        };

        shell.execute(command);

        ThreadUtil.wait(this);
    }
}
