package bluetooth.app.internal.hc05commands;

/**
 * Command for RED LED
 */
public class LEDRedCommand extends HC05Command {

    @Override
    protected String getCommandText() {
        return "2";
    }
}
