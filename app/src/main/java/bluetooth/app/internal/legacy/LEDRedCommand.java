package bluetooth.app.internal.legacy;

import bluetooth.app.internal.hc05commands.HC05Command;

/**
 * Command for RED LED
 */
public class LEDRedCommand extends HC05Command {

    @Override
    protected String getCommandText() {
        return "2";
    }
}
