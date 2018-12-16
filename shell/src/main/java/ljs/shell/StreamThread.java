package ljs.shell;

import java.io.BufferedReader;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.UnsupportedEncodingException;

public class StreamThread extends Thread {

    Shell shell;

    public StreamThread(Shell shell) {
        this.shell = shell;
    }

    public BufferedReader getBufferedReader(InputStream in) {
        BufferedReader bufferedReader = null;
        try {
            if (in != null) bufferedReader = new BufferedReader(new InputStreamReader(in, shell.encoding));
        } catch (UnsupportedEncodingException e) {
            shell.sendError(new Exception("未知编码:" + shell.encoding));
            shell.close();
        }
        return bufferedReader;
    }
}
