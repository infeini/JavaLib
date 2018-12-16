package ljs.shell;

import ljs.lib.StringUtils;

import java.util.ArrayList;
import java.util.List;

/**
 * 执行的命令对象
 */
public class Command {

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
    private String encoding;

    private String cmd;

    private List<String> outErrors = new ArrayList<>();

    public Command(String cmd) {
        this.cmd = cmd;
    }

    public String getEncoding() {
        return encoding;
    }

    public String getCmd() {
        return cmd;
    }

    /**
     * 开始执行命令
     */
    public void start() {
    }

    /**
     * 命令输出
     */
    public void out(String line) {
    }

    /**
     * 命令输出错误
     */
    public void error(String errorLine) {
        outErrors.add(errorLine);
    }

    /**
     * 命令正常完成
     */
    public void end() {
    }

    /**
     * 命令是否有错误流输出
     */
    public boolean hasError() {
        return !outErrors.isEmpty();
    }
}
