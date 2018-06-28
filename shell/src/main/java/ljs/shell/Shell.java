package ljs.shell;

import ljs.exception.KnowException;
import ljs.io.IOUtil;
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

    Runtime runtime;

    String initCmd;

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

    public Shell(String initCmd, String encoding) throws KnowException {

        this.runtime = Runtime.getRuntime();

        this.initCmd = initCmd;

        if (encoding != null)
            this.encoding = encoding;

        try {
            process = runtime.exec(initCmd);

            writerStream = process.getOutputStream();

            readerStream = process.getInputStream();

            errorStream = process.getErrorStream();

            readThread = new ReadThread(this);
            readThread.start();

            errorReadThread = new ErrorReadThread(this);
            errorReadThread.start();

            writeThread = new WriteThread(this);
            writeThread.start();
        } catch (IOException e) {
            throw new KnowException("创建进程失败");
        }
    }

    public static Shell newAndroidShell() throws KnowException {
        return newAndroidShell(false);
    }

    public static Shell newAndroidShell(boolean isRoot) throws KnowException {
        return newAndroidShell(isRoot, null);
    }

    public static Shell newAndroidShell(boolean isRoot, String encoding) throws KnowException {
        return new Shell(isRoot ? "su" : "bash", encoding);
    }

    public static Shell newShell() throws KnowException {
        return newShell(null);
    }

    public static Shell newShell(String encoding) throws KnowException {
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
        return new Shell(initCmd, encoding);
    }

    /**
     * 关闭Shell
     */
    public void close() {

        IOUtil.close(errorStream, writerStream, readerStream);

        errorStream = null;

        writerStream = null;

        readerStream = null;

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
    public synchronized void execute(Command command) throws InterruptedException {

        //等待以前的命令执行完成
        while (nowCommand != null) ThreadUtil.wait(this);

        nowCommand = command;

        ThreadUtil.notify(readThread);
        ThreadUtil.notify(errorReadThread);
        ThreadUtil.notify(writeThread);

        //等待当前的命令执行完成
        while (nowCommand != null) ThreadUtil.wait(this);
    }
}
