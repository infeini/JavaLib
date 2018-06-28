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

    // 终端字符编码
    public String encoding;

    String cmd;

    public Command(String cmd) {
        this.cmd = cmd;
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
     * 命令输出错误
     */
    public abstract void error(String errorLine);

    /**
     * 命令正常完成
     */
    public abstract void end();

}
