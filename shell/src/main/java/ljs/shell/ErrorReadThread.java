package ljs.shell;

import java.io.BufferedReader;
import java.io.IOException;

public class ErrorReadThread extends StreamThread {

    public ErrorReadThread(Shell shell) {
        super(shell);
        setName("shell error");
    }

    @Override
    public void run() {

        BufferedReader reader = getBufferedReader(shell.errorStream);

        if (reader == null) return;

        while (shell.errorStream != null) {

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
                shell.sendErrorReadError(new Exception("读取错误流发生异常", e));
            }
        }
    }
}
