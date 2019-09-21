package bluetooth.app.internal.hc05commands;

/**
 * Command for GREEN LED
 */
public class LEDGreenCommand extends HC05Command {
    @Override
    protected String getCommandText() {
        return "3";
    }
}
