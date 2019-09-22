package bluetooth.app.internal.hc05data;

public abstract class HC05Data {

    public abstract boolean canProcess(String data);

    public abstract String strip(String data);

}
