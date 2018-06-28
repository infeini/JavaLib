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
        OutputStream writerStream;
        while ((writerStream = shell.writerStream) == null)
            ThreadUtil.wait(shell);

        while (true) {

            Command nowCommand = shell.nowCommand;
            if (nowCommand == null)
                ThreadUtil.wait(this);
            else {

                //start mark
                try {
                    writerStream.write(format("echo " + nowCommand.startMark));
                    writerStream.flush();
                } catch (IOException e) {
                    e.printStackTrace();
                }

                //run
                try {
                    writerStream.write(format(nowCommand.cmd));
                    writerStream.flush();
                } catch (IOException e) {
                    e.printStackTrace();
                }

                //end mark
                try {
                    writerStream.write(format("echo " + nowCommand.endMark));
                    writerStream.flush();
                } catch (IOException e) {
                    e.printStackTrace();
                }

                ThreadUtil.wait(this);
            }
        }
    }

    byte[] format(String cmd) {
        return (cmd + System.lineSeparator()).getBytes();
    }
}
