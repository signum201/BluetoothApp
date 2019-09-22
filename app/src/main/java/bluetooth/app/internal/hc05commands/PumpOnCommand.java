package bluetooth.app.internal.hc05commands;

/**
 *
 */
public class PumpOnCommand extends HC05Command {
    @Override
    protected String getCommandText() {
        return "pon";
    }
}
