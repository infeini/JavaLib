package ljs.shell;

import java.io.*;
import java.util.List;

public class ReadThread extends StreamThread {

    public ReadThread(Shell shell) {
        super(shell);
    }

    @Override
    public void run() {

        InputStream readerStream = shell.getReaderStream();

        while (true) {


            List<Command> commandPool = shell.getCommandPool();

            if (readerStream == null) break;

            else if (commandPool.isEmpty()) wait(shell);

            Command command = commandPool.get(0);
            try {
                InputStreamReader inputStreamReader = new InputStreamReader(readerStream, command.encoding);

                inputStreamReader.close();

                BufferedReader reader = new BufferedReader();

                reader.close();
                String line;

                while ((line = reader.readLine()) != null) {

                    if (command.startMark.equals(line)) command.start();

                    else if (command.endMark.equals(line)) command.end();

                    else command.out(line);
                }
            } catch (UnsupportedEncodingException e) {
                command.error(e);
            } catch (IOException e) {
                command.error(e);
            }
        }
    }
}
