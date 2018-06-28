package ljs.shell;

import ljs.task.ThreadUtil;

import java.io.*;

public class ReadThread extends StreamThread {

    public ReadThread(Shell shell) {
        super(shell);
        setName("shell read");
    }

    @Override
    public void run() {

        InputStream readerStream;

        while ((readerStream = shell.readerStream) == null)
            ThreadUtil.wait(this);

        BufferedReader reader = null;
        try {
            reader = new BufferedReader(new InputStreamReader(readerStream, shell.encoding));
        } catch (UnsupportedEncodingException e) {
            e.printStackTrace();
        }

        while (true) {


            String line;

            try {

                while ((line = reader.readLine()) != null) {

                    Command nowCommand = shell.nowCommand;

                    if (nowCommand != null) {
                        if (nowCommand.startMark.equals(line))
                            nowCommand.start();

                        else if (nowCommand.endMark.equals(line)) {

                            nowCommand.end();

                            shell.nowCommand = null;

                            ThreadUtil.notifyAll(shell);
                        } else
                            nowCommand.out(line);
                    }
                }
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }
}
