package ljs.shell;

import ljs.exception.KnowException;
import ljs.io.IOUtil;
import ljs.lib.StringUtils;
import ljs.os.OsUtils;
import ljs.task.ThreadUtil;

import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.nio.charset.Charset;
import java.util.ArrayList;
import java.util.List;

/**
 * android终端
 */
public class Shell {

    private final String mark = StringUtils.getRandString(5);

    String initMark = "inited:" + mark;

    Runtime runtime;

    String initCmd;

    StringBuffer welcome = new StringBuffer();

    boolean inited;

    ShellListener shellListener;

    //当前shell命令队列
    Command nowCommand;

    Process process;

    OutputStream writerStream;

    InputStream readerStream;

    InputStream errorStream;

    String encoding = Charset.defaultCharset().name();

    ReadThread readThread;

    ErrorReadThread errorReadThread;

    WriteThread writeThread;

    private ShellErrorHandler errorHandler;

    public Shell(String initCmd, String encoding, ShellListener shellListener) throws KnowException {

        this.runtime = Runtime.getRuntime();

        this.initCmd = initCmd;

        this.shellListener = shellListener;

        if (encoding != null)
            this.encoding = encoding;

        try {
            process = runtime.exec(initCmd);

            writerStream = process.getOutputStream();
            writerStream.write(WriteThread.format("echo " + initMark));
            writerStream.flush();

            readerStream = process.getInputStream();

            errorStream = process.getErrorStream();

            readThread = new ReadThread(this);
            readThread.start();

            errorReadThread = new ErrorReadThread(this);
            errorReadThread.start();

            writeThread = new WriteThread(this);
            writeThread.start();
        } catch (IOException e) {
            if (shellListener != null) shellListener.onCreateFail(e.getMessage());
            else throw new KnowException("创建进程失败");
        }
    }

    public static Shell newAndroidShell() throws KnowException {
        return newAndroidShell(false, null);
    }

    public static Shell newAndroidShell(ShellListener shellListener) throws KnowException {
        return newAndroidShell(false, shellListener);
    }

    public static Shell newAndroidShell(boolean isRoot) throws KnowException {
        return newAndroidShell(isRoot, null);
    }

    public static Shell newAndroidShell(boolean isRoot, ShellListener shellListener) throws KnowException {
        return newAndroidShell(isRoot, null, shellListener);
    }

    public static Shell newAndroidShell(boolean isRoot, String encoding, ShellListener shellListener) throws KnowException {
        return new Shell(isRoot ? "su" : "sh", encoding, shellListener);
    }

    public static Shell newShell() throws KnowException {
        return newShell(null, null);
    }

    public static Shell newShell(ShellListener shellListener) throws KnowException {
        return newShell(null, shellListener);
    }

    public static Shell newShell(String encoding) throws KnowException {
        return newShell(encoding, null);
    }

    public static Shell newShell(String encoding, ShellListener shellListener) throws KnowException {
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
        return new Shell(initCmd, encoding, shellListener);
    }

    /**
     * 关闭Shell
     */
    public void close() {

        IOUtil.close(errorStream, writerStream, readerStream);

        errorStream = null;

        writerStream = null;

        readerStream = null;

        ThreadUtil.notify(readThread);
        ThreadUtil.notify(errorReadThread);
        ThreadUtil.notify(writeThread);

        process.destroy();

        process = null;

        runtime.freeMemory();

        runtime = null;
    }

    /**
     * 执行命令
     *
     * @param command 需要执行的命令
     */
    public synchronized void execute(Command command) {
        if (process != null) {
            //等待以前的命令执行完成
            while (nowCommand != null) ThreadUtil.wait(this);

            nowCommand = command;

            ThreadUtil.notify(readThread);
            ThreadUtil.notify(errorReadThread);
            ThreadUtil.notify(writeThread);
        }
    }

    void sendWriteError(Throwable e) {
        if (writerStream != null) sendError(e);
    }

    void sendReadError(Throwable e) {
        if (readerStream != null) sendError(e);
    }

    void sendErrorReadError(Throwable e) {
        if (errorStream != null) sendError(e);
    }

    void sendError(Throwable e) {
        if (errorHandler != null) errorHandler.error(e);
    }

    public void setErrorHandler(ShellErrorHandler errorHandler) {
        this.errorHandler = errorHandler;
    }
}
