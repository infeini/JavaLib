package ljs.shell;

public class StreamThread extends Thread {

    Shell shell;

    public StreamThread(Shell shell) {
        this.shell = shell;
    }
}
