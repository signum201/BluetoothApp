package bluetooth.app.internal.hc05data;

/**
 * T10#10#10#0/1T
 */
public class SensorData extends HC05Data {
    @Override
    public boolean canProcess(String data) {
        return data.startsWith("T") && data.endsWith("T");
    }

    @Override
    public String strip(String data) {
        return null;
    }
}
