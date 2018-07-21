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

                    if (shell.inited) {

                        Command nowCommand = shell.nowCommand;

                        if (nowCommand != null)
                            nowCommand.error(line);
                    } else if (shell.shellListener != null) shell.shellListener.onCreateFail(line);
                }
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }
}
