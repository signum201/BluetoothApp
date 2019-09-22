package bluetooth.app.internal.legacy;

import bluetooth.app.internal.hc05commands.HC05Command;

/**
 * Command for LED OFF
 */
public class LEDOffCommand extends HC05Command {


    @Override
    protected String getCommandText() {
        return "0";
    }
}
