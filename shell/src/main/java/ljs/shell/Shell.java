package ljs.shell;

import ljs.io.IOUtil;
import ljs.lib.Md5Util;
import ljs.task.ThreadUtil;

import java.io.*;

/**
 * android终端
 */
public class Shell {
    private final String FLAG = Md5Util.getRandMd5();
    //命令开始标记
    private final String START_MARK = "ljs_cmd_mark_start:" + FLAG;
    //命令结束标记
    private final String END_MARK = "ljs_cmd_mark_end:" + FLAG;

    //是否已经关闭
    private boolean closed = false;
    //是否正在运行
    private boolean running = false;
    //是否正在读取
    private boolean reading = false;

    private Runtime runtime;
    private Process process;
    private BufferedWriter writer;
    private BufferedReader reader;
    private BufferedReader error;
    private Command command;
    private final String DefaultCharset = "GBK";// Charset.defaultCharset().name();

    private Shell(String initCommand) throws IOException {
        runtime = Runtime.getRuntime();
        process = runtime.exec(initCommand);
        writer = new BufferedWriter(new OutputStreamWriter(process.getOutputStream(), DefaultCharset));
        reader = new BufferedReader(new InputStreamReader(process.getInputStream(), DefaultCharset));
        error = new BufferedReader(new InputStreamReader(process.getErrorStream(), DefaultCharset));
        new Thread(() -> {
            try {
                while (true) {
                    if (command == null || command.finish)
                        synchronized (Shell.this) {
                            Shell.this.wait();
                        }
                    if (closed)
                        break;
                    String line = reader.readLine();
                    if (line == null) {
                        reading = false;
                        running = false;
                        command.running = false;
                        synchronized (Shell.this) {
                            Shell.this.wait();
                        }
                        break;
                    } else if (START_MARK.contains(line))
                        reading = true;
                    else if (END_MARK.contains(line)) {
                        running = false;
                        reading = false;
                        command.running = false;
                        command.finish = true;
                        command.commandFinish();
                    } else
                        command.commandOutput(line);
                }
            } catch (Exception e) {
            } finally {
                close();
            }
        }, "ljs_shell_read_thread").start();
        new Thread(() -> {
            try {
                while (true) {
                    if (command == null)
                        synchronized (Shell.this) {
                            Shell.this.wait();
                        }
                    if (closed)
                        break;
                    else if (command == null || command.running)
                        synchronized (Shell.this) {
                            try {
                                Shell.this.wait();
                            } catch (InterruptedException e) {
                                e.printStackTrace();
                            }
                        }
                    else {
                        running = true;
                        command.running = true;
                        writer.write("echo " + START_MARK + "\n");
                        writer.write(command.getCmd() + "\n");
                        writer.write("echo " + END_MARK + "\n");
                        writer.flush();
                    }
                }
            } catch (Exception e) {
                e.printStackTrace();
            } finally {
                close();
            }
        }, "ljs_shell_write_thread").start();
        new Thread(() -> {
            try {
                String line;
                synchronized (Shell.this) {
                    Shell.this.wait();
                }
                while ((line = error.readLine()) != null) {
                    if (command != null && !command.interrupted) {
                        command.interrupted = true;
                        command.commandInterrupted(line);
                    }
                }
            } catch (IOException | InterruptedException e) {
                e.printStackTrace();
            }
        }, "ljs_shell_error_read_thread").start();
    }

    /**
     * 获取Android终端命令行
     *
     * @param initCmd 初始参数
     */
    public static Shell newInstance(String initCmd) throws IOException {
        return new Shell(initCmd);
    }

    /**
     * 关闭Shell
     */
    public void close() {
        closed = true;
        running = false;
        reading = false;
        IOUtil.close(writer);
        IOUtil.close(reader);
        process.destroy();
    }

    /**
     * 执行命令，不会堵塞当前线程
     *
     * @param command 需要执行的命令
     */
    public synchronized void addCommand(Command command) throws Exception {
        addCommand(command, false);
    }

    /**
     * 执行命令
     *
     * @param command 需要执行的命令
     * @param join    是否让当前线程等待命令结束
     */
    public void addCommand(Command command, boolean join) throws Exception {
        if (closed)
            throw new Exception("该终端已被关闭");
        if (reading)
            throw new Exception("该终端正在读取");
        if (running)
            throw new Exception("该终端正在执行");
        this.command = command;
        command.commandStart();
        synchronized (Shell.this) {
            Shell.this.notifyAll();
        }
        if (join)
            while (command.isRunning())
                ThreadUtil.sleep(100);
    }
}
