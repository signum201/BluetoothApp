package bluetooth.app.internal.hc05commands;

/**
 * Resets the reservoir
 */
public class ResetReservoirCommand extends HC05Command {
    @Override
    protected String getCommandText() {
        return "rstb";
    }
}
