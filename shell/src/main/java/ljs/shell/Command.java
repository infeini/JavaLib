package ljs.shell;

import ljs.exception.KnowException;
import ljs.lib.StringUtils;

/**
 * 执行的命令对象
 */
public abstract class Command {

    private final String mark = StringUtils.getRandString(5);

    public static String getStartMark(String mark) {
        return "cmd_start:" + mark;
    }

    public static String getEndMark(String mark) {
        return "cmd_end:" + mark;
    }

    //命令开始标记
    public final String startMark = getStartMark(mark);

    //命令结束标记
    public final String endMark = getEndMark(mark);

    private final static String defaultEncoding = "GBK";

    // 终端字符编码
    public String encoding;

    //命令是否运行
    private boolean running = false;

    private String cmd;

    public Command(String cmd) throws KnowException {
        this(cmd, defaultEncoding);
    }

    public Command(String cmd, String encoding) throws KnowException {
        if (StringUtils.isEmpty(cmd))
            throw new KnowException("命令不能为空");
        this.cmd = cmd.endsWith("\n") ? cmd : (cmd + "\n");
    }

    public boolean isRunning() {
        return running;
    }

    public void setRunning(boolean running) {
        this.running = running;
    }

    public String getCmd() {
        return cmd;
    }

    /**
     * 开始执行命令
     */
    public abstract void start();

    /**
     * 命令输出
     */
    public abstract void out(String line);

    /**
     * 命令发生错误
     */
    public abstract void error(Throwable throwable);

    /**
     * 命令输出错误
     */
    public abstract void error(String ling);

    /**
     * 命令正常完成
     */
    public abstract void end();

}
