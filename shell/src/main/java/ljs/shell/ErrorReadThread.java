package ljs.shell;

import java.io.InputStream;

public class ErrorReadThread extends StreamThread {

    InputStream errorStream;

    public ErrorReadThread(Shell shell) {

        super(shell);

        errorStream = shell.getErrorStream();
    }
}
