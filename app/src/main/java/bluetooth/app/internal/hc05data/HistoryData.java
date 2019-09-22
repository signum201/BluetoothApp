package bluetooth.app.internal.hc05data;

public class HistoryData extends HC05Data {
    @Override
    public boolean canProcess(String data) {
        return data.startsWith("H") && data.endsWith("H");
    }

    @Override
    public String strip(String data) {
        return null;
    }
}
