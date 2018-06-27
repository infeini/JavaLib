package ljs.shell;

import java.io.IOException;
import java.io.OutputStream;
import java.util.List;

public class WriteThread extends StreamThread {

    public WriteThread(Shell shell) {
        super(shell);
    }

    @Override
    public void run() {

        while (true) {

            wait(shell);

            List<Command> commandPool = shell.getCommandPool();

            OutputStream writeStream = shell.getWriterStream();

            if (commandPool.isEmpty()) wait(shell);
            else if (writeStream == null) break;
            else {
                Command command = commandPool.get(0);

                try {

                    writeStream.write(command.startMark.getBytes(command.encoding));

                    writeStream.write(command.getCmd().getBytes(command.encoding));

                    writeStream.write(command.endMark.getBytes(command.encoding));

                } catch (IOException e) {
                    command.error(e);
                }
            }
        }
    }
}
