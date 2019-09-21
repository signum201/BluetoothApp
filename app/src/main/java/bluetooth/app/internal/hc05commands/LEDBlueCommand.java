package bluetooth.app.internal.hc05commands;

/**
 * Command for BLUE LED
 */
public class LEDBlueCommand extends HC05Command {
    @Override
    protected String getCommandText() {
        return "1";
    }
}
