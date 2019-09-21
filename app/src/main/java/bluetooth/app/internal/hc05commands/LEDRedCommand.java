package bluetooth.app.internal.hc05commands;

public class LEDRedCommand extends HC05Command {

    @Override
    protected String getCommandText() {
        return "2";
    }
}
