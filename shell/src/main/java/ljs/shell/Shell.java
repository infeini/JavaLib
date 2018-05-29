package ljs.shell;

import ljs.exception.KnowException;
import ljs.io.IOUtil;
import ljs.lib.StringUtils;
import ljs.os.OsUtils;

import java.io.*;

/**
 * android终端
 */
public class Shell {
    private static final String FLAG = StringUtils.getRandString(5);
    //命令开始标记
    private static final String START_MARK = "cmd_start:" + FLAG;
    //命令结束标记
    private static final String END_MARK = "cmd_end:" + FLAG;
    //是否已经关闭
    private boolean closed = false;

    private Runtime runtime;

    class ReadThread extends Thread {
        private BufferedReader reader;
        private Command command;

        ReadThread(BufferedReader reader, Command command) {
            super("shell-read");
            this.reader = reader;
            this.command = command;
        }

        @Override
        public void run() {
            try {
                command.setReading(true);
                String line;
                while ((line = reader.readLine()) != null) {
                    if (START_MARK.contains(line)) ;
                    else if (END_MARK.contains(line)) {
                        command.commandFinish();
                        break;
                    } else command.commandOutput(line);
                }
            } catch (IOException e) {
                if (command.isRunning())
                    command.commandError(e);
            } finally {
                command.setReading(false);
                synchronized (command) {
                    command.notifyAll();
                }
            }
        }
    }

    class WriteThread extends Thread {
        private BufferedWriter writer;
        private BufferedReader reader;
        private BufferedReader error;
        private Command command;

        WriteThread(BufferedWriter writer, Command command) {
            super("shell-write");
            this.writer = writer;
            this.command = command;
        }

        @Override
        public void run() {
            try {
                command.setRunning(true);
                writer.write("echo " + START_MARK + "\n");
                String[] cmds = command.getCmds();
                for (String cmd : cmds)
                    writer.write(cmd + (cmd.endsWith("\n") ? "" : "\n"));
                writer.write("echo " + END_MARK + "\n");
                writer.flush();
            } catch (IOException e) {
                command.setInterrupted(true);
                command.commandError(e);
            } finally {
                command.setRunning(false);
                synchronized (command) {
                    command.notifyAll();
                }
            }
        }
    }

    class ErrorReadThread extends Thread {
        private BufferedReader reader;
        private Command command;

        ErrorReadThread(BufferedReader reader, Command command) {
            super("shell-error");
            this.reader = reader;
            this.command = command;
        }

        @Override
        public void run() {
            try {
                String line;
                while ((line = reader.readLine()) != null) command.commandOutputError(line);
            } catch (IOException e) {
                if (command.isInterrupted())
                    command.commandError(e);
            } finally {
                synchronized (command) {
                    command.notifyAll();
                }
            }
        }
    }

    private String initCmd;

    public static Shell newShell() throws KnowException {
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
        return new Shell(initCmd);
    }

    public Shell(String initCmd) {
        this.runtime = Runtime.getRuntime();
        this.initCmd = initCmd;
    }


    /**
     * 关闭Shell
     */
    public void close() {
        closed = true;
        runtime.freeMemory();
    }

    /**
     * 执行命令
     *
     * @param command 需要执行的命令
     */
    public void execute(Command command) throws KnowException, IOException, InterruptedException {
        if (closed)
            throw new KnowException("该终端已被关闭");
        else if (command.isRunning())
            throw new KnowException("该命令正在运行");
        else if (command.isReading())
            throw new KnowException("该命令正在读取执行结果");
        else if (command.isFinished())
            throw new KnowException("该命令已执行成功");
        else if (command.isInterrupted())
            throw new KnowException("该命令已经中断");

        Process process = runtime.exec(initCmd);

        BufferedWriter writer = new BufferedWriter(new OutputStreamWriter(process.getOutputStream(), command.Encoding));
        BufferedReader reader = new BufferedReader(new InputStreamReader(process.getInputStream(), command.Encoding));
        BufferedReader error = new BufferedReader(new InputStreamReader(process.getErrorStream(), command.Encoding));

        ReadThread readThread = new ReadThread(reader, command);
        readThread.join();

        ErrorReadThread errorReadThread = new ErrorReadThread(error, command);
        errorReadThread.join();

        WriteThread writeThread = new WriteThread(writer, command);

        readThread.start();
        errorReadThread.start();
        writeThread.run();

        command.commandStart();

        while (command.isRunning() || command.isReading()) {
            System.out.println(command);
            synchronized (command) {
                command.wait();
            }
        }
        IOUtil.close(writer);
        IOUtil.close(reader);
        IOUtil.close(error);
    }
}
