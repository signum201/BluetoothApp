package bluetooth.app.internal.legacy;

import bluetooth.app.internal.hc05commands.HC05Command;

/**
 * Command for BLUE LED
 */
public class LEDBlueCommand extends HC05Command {
    @Override
    protected String getCommandText() {
        return "1";
    }
}
