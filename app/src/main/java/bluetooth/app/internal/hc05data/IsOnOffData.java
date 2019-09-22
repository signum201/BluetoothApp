package bluetooth.app.internal.hc05data;

/**
 * is0 sau is1, after 200 ms
 */
public class IsOnOffData extends HC05Data {
    @Override
    public boolean canProcess(String data) {
        return data.startsWith("is");
    }

    @Override
    public String strip(String data) {
        return null;
    }
}
