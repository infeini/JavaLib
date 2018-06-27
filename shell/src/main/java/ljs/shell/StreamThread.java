package ljs.shell;

public class StreamThread extends Thread {

    Shell shell;

    public StreamThread(Shell shell) {
        this.shell = shell;
    }

    void wait(Object lock) {
        synchronized (lock) {
            try {
                lock.wait();
            } catch (InterruptedException e) {
            }
        }
    }

    void notifyAll(Object lock) {
        synchronized (lock) {
            lock.notifyAll();
        }
    }
}
