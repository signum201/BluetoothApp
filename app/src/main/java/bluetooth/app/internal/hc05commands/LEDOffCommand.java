package bluetooth.app.internal.hc05commands;

/**
 * Command for LED OFF
 */
public class LEDOffCommand extends HC05Command {


    @Override
    protected String getCommandText() {
        return "0";
    }
}
