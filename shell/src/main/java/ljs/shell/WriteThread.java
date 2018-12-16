package ljs.shell;

import ljs.task.ThreadUtil;

import java.io.IOException;
import java.io.OutputStream;

public class WriteThread extends StreamThread {

    public WriteThread(Shell shell) {
        super(shell);
        setName("shell write");
    }

    @Override
    public void run() {
        OutputStream writerStream = shell.writerStream;

        while (shell.writerStream != null) {

            Command nowCommand = shell.nowCommand;

            if (nowCommand == null) ThreadUtil.wait(this);
            else {

                //start mark
                try {
                    writerStream.write(format("echo " + nowCommand.startMark));
                    writerStream.flush();
                } catch (IOException e) {
                    shell.sendWriteError(new Exception("写入命令开始标记失败", e));
                }

                //run
                try {
                    writerStream.write(format(nowCommand.getCmd()));
                    writerStream.flush();
                } catch (IOException e) {
                    shell.sendWriteError(new Exception("写入命令失败:" + nowCommand.getCmd(), e));
                }

                //end mark
                try {
                    writerStream.write(format("echo " + nowCommand.endMark));
                    writerStream.flush();
                } catch (IOException e) {
                    shell.sendWriteError(new Exception("写入命令结束标记失败", e));
                }

                ThreadUtil.wait(this);
            }
        }
    }

    static byte[] format(String cmd) {
        return (cmd + System.lineSeparator()).getBytes();
    }
}
