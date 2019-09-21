package bluetooth.app.internal.hc05commands;


import java.io.IOException;

import bluetooth.app.Command;

public abstract class HC05Command extends Command {
    private static final String SET = "set";
    private static final Integer WAIT = 200;

    protected void prepare() throws IOException {
        send(SET);
        delay();

    }

    public void execute() throws IOException {
        prepare();
        send(getCommandText());
        delay();
    }

    private void delay() {
        try {
            Thread.sleep(WAIT);
        } catch (InterruptedException e) {
            // could not sleep...
        }
    }

    /**
     * The text for the command
     *
     * @return the text
     */
    protected abstract String getCommandText();


}
