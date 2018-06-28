package ljs.shell;

import ljs.task.ThreadUtil;

import java.io.*;

public class ErrorReadThread extends StreamThread {

    public ErrorReadThread(Shell shell) {
        super(shell);
        setName("shell error");
    }

    @Override
    public void run() {

        InputStream errorStream;

        while ((errorStream = shell.errorStream) == null)
            ThreadUtil.wait(this);

        BufferedReader reader = null;
        try {
            reader = new BufferedReader(new InputStreamReader(errorStream, shell.encoding));
        } catch (UnsupportedEncodingException e) {
            e.printStackTrace();
        }

        while (true) {

            String line;

            try {

                while ((line = reader.readLine()) != null) {

                    Command nowCommand = shell.nowCommand;

                    if (nowCommand != null)
                        nowCommand.error(line);
                }
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }
}
