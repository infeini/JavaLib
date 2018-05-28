package ljs.shell;

import ljs.exception.KnowException;

/**
 * 执行的命令对象
 */
public abstract class Command {
    // 终端字符编码
    public String Encoding = "GBK";
    //命令是否运行
    private boolean running = false;
    //命令是否中断
    private boolean interrupted = false;
    //命令是否完成
    private boolean finished = false;
    //命令是否正在读取
    private boolean reading = false;

    private String[] cmds;

    public Command(String cmd) {
        this.cmds = new String[]{cmd};
    }

    public Command(String[] cmds) {
        this.cmds = cmds;
    }

    public boolean isRunning() {
        return running;
    }

    public void setRunning(boolean running) {
        this.running = running;
    }

    public void setReading(boolean reading) {
        this.reading = reading;
    }

    public boolean isReading() {
        return reading;
    }

    public boolean isInterrupted() {
        return interrupted;
    }

    public void setInterrupted(boolean interrupted) {
        this.interrupted = interrupted;
    }

    public boolean isFinished() {
        return finished;
    }

    public void setFinished(boolean finished) {
        this.finished = finished;
    }

    /**
     * 获取命令
     */
    public String[] getCmds() {
        return cmds;
    }

    /**
     * 开始执行命令
     */
    public abstract void commandStart();

    /**
     * 命令输出
     */
    public abstract void commandOutput(String line);

    /**
     * 命令输出错误
     */
    public abstract void commandOutputError(String line);

    /**
     * 命令正常完成
     */
    public abstract void commandFinish();

    /**
     * 命令发生错误
     */
    public abstract void commandError(Throwable throwable);

    /**
     * 中断命令
     */
    public void interrupted() {
        if (running) interrupted = true;
    }

    /**
     * 命令是否结束
     *
     * @return boolean
     */
    public boolean isStop() {
        return interrupted || finished;
    }

    /**
     * 重置命令
     */
    public void restore() throws KnowException {
        if (running) throw new KnowException("该命令正在执行不能重置");
        else {
            interrupted = false;
            finished = false;
        }
    }
}
