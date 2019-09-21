package bluetooth.app.internal.hc05commands;

public class LEDGreenCommand extends HC05Command {
    @Override
    protected String getCommandText() {
        return "3";
    }
}
