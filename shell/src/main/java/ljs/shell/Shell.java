package ljs.shell;

import ljs.exception.KnowException;
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
    private final String START_MARK = "cmd_start:" + FLAG;
    //命令结束标记
    private final String END_MARK = "cmd_end:" + FLAG;
    //是否已经关闭
    private boolean closed = false;
    //是否正在运行
    private boolean running = false;
    //是否正在读取
    private boolean reading = false;

    private Runtime runtime = Runtime.getRuntime();
    private Process process;
    private BufferedWriter writer;
    private BufferedReader reader;
    private BufferedReader error;
    private Command command;
    private final String ShellEncoding = "GBK";// 终端默认字符编码

    class ReadThread extends Thread {
        ReadThread() {
            super("ljs_shell:read_thread");
        }

        @Override
        public void run() {
            try {
                while (true) {
                    if (command == null || command.isFinished())
                        synchronized (Shell.this) {
                            Shell.this.wait();
                        }
                    if (closed)
                        break;
                    String line = reader.readLine();
                    if (line == null) {
                        reading = false;
                        running = false;
                        command.setRunning(false);
                        synchronized (Shell.this) {
                            Shell.this.wait();
                        }
                        break;
                    } else if (START_MARK.contains(line))
                        reading = true;
                    else if (END_MARK.contains(line)) {
                        running = false;
                        reading = false;
                        command.setRunning(false);
                        command.setFinished(true);
                        command.commandFinish();
                    } else
                        command.commandOutput(line);
                }
            } catch (Exception e) {
            } finally {
                close();
            }
        }
    }

    class WriteThread extends Thread {
        WriteThread() {
            super("ljs_shell:write_thread");
        }

        @Override
        public void run() {
            try {
                while (true) {
                    if (command == null)
                        synchronized (Shell.this) {
                            Shell.this.wait();
                        }
                    if (closed)
                        break;
                    else if (command == null || command.isRunning())
                        synchronized (Shell.this) {
                            try {
                                Shell.this.wait();
                            } catch (InterruptedException e) {
                                e.printStackTrace();
                            }
                        }
                    else {
                        running = true;
                        command.setRunning(true);
                        writer.write("echo " + START_MARK + "\n");
                        String[] cmds = command.getCmds();
                        for (String cmd : cmds)
                            writer.write(cmd + (cmd.endsWith("\n") ? "" : "\n"));
                        writer.write("echo " + END_MARK + "\n");
                        writer.flush();
                    }
                }
            } catch (Exception e) {
                e.printStackTrace();
            } finally {
                close();
            }
        }
    }

    class ErrorReadThread extends Thread {
        ErrorReadThread() {
            super("ljs_shell:error_read_thread");
        }

        @Override
        public void run() {
            try {
                String line;
                synchronized (Shell.this) {
                    Shell.this.wait();
                }
                while ((line = error.readLine()) != null) {
                    if (command != null && !command.isInterrupted()) {
                        command.setInterrupted(true);
                        command.commandInterrupted(line);
                    }
                }
            } catch (IOException | InterruptedException e) {
                e.printStackTrace();
            }
        }
    }

    private ReadThread readThread;
    private WriteThread writeThread;
    private ErrorReadThread errorReadThread;

    /**
     * @param initCommand 初始参数
     */
    public Shell(String initCommand) throws IOException, InterruptedException {
        process = runtime.exec(initCommand);
        writer = new BufferedWriter(new OutputStreamWriter(process.getOutputStream(), ShellEncoding));
        reader = new BufferedReader(new InputStreamReader(process.getInputStream(), ShellEncoding));
        error = new BufferedReader(new InputStreamReader(process.getErrorStream(), ShellEncoding));

        readThread = new ReadThread();
        readThread.join();

        writeThread = new WriteThread();
        writeThread.join();

        errorReadThread = new ErrorReadThread();
        errorReadThread.join();

        readThread.start();
        errorReadThread.start();
        writeThread.start();
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
        IOUtil.close(error);
        process.destroy();
    }

    /**
     * 执行命令
     *
     * @param command 需要执行的命令
     */
    public void execute(Command command) throws KnowException {
        if (closed)
            throw new KnowException("该终端已被关闭");
        if (reading)
            throw new KnowException("该终端正在读取");
        if (running)
            throw new KnowException("该终端正在执行");
        this.command = command;
        command.commandStart();
        synchronized (this) {
            notifyAll();
        }
    }
}
