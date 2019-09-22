package bluetooth.app.internal.hc05commands;

public class PumpOffCommand extends HC05Command {
    @Override
    protected String getCommandText() {
        return "poff";
    }
}
