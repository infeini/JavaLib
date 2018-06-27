package ljs.shell;

import ljs.exception.KnowException;
import ljs.io.IOUtil;
import ljs.os.OsUtils;

import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.util.ArrayList;
import java.util.List;

/**
 * android终端
 */
public class Shell {

    //是否已经关闭
    private boolean closed = false;

    private Runtime runtime;

    private String initCmd;

    //当前shell命令队列
    private List<Command> commandPool = new ArrayList<>();

    private Process process;

    private OutputStream writerStream;

    private InputStream readerStream;

    private InputStream errorStream;

    public Shell(String initCmd) throws KnowException {

        this.runtime = Runtime.getRuntime();

        this.initCmd = initCmd;

        try {
            process = runtime.exec(initCmd);

            writerStream = process.getOutputStream();

            readerStream = process.getInputStream();

            errorStream = process.getErrorStream();

            new ReadThread(this).start();

            new ErrorReadThread(this).start();

            new WriteThread(this).start();
        } catch (IOException e) {
            throw new KnowException("创建进程失败");
        }
    }

    public List<Command> getCommandPool() {
        return commandPool;
    }

    OutputStream getWriterStream() {
        return writerStream;
    }

    InputStream getErrorStream() {
        return errorStream;
    }

    InputStream getReaderStream() {
        return readerStream;
    }

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
    public void execute(Command command) throws KnowException {

        if (closed)
            throw new KnowException("该终端已被关闭");
        else if (command.isRunning())
            throw new KnowException("该命令正在运行");

    }
}
