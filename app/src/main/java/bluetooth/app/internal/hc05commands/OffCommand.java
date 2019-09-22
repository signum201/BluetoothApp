package bluetooth.app.internal.hc05commands;

/**
 *
 */
public class OffCommand extends HC05Command {
    @Override
    protected String getCommandText() {
        return "off";
    }
}
