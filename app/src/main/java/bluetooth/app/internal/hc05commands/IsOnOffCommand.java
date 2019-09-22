package bluetooth.app.internal.hc05commands;

public class IsOnOffCommand extends HC05Command {
    @Override
    protected String getCommandText() {
        return "ison";
    }
}
