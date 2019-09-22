package bluetooth.app.internal.hc05commands;

/**
 * Returns text: prefix:H and a digit for each hour and ends with H
 * E.g: H123456789123456789123456H
 */
public class HistoryCommand extends HC05Command {
    @Override
    protected String getCommandText() {
        return "ist"+1;//1-7
    }
}
