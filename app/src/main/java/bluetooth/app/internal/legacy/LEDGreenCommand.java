package bluetooth.app.internal.legacy;

import bluetooth.app.internal.hc05commands.HC05Command;

/**
 * Command for GREEN LED
 */
public class LEDGreenCommand extends HC05Command {
    @Override
    protected String getCommandText() {
        return "3";
    }
}
