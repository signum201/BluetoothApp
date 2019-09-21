package bluetooth.app;

import android.bluetooth.BluetoothClass;

import java.io.IOException;

public abstract class Command {

    protected DeviceDescriptor device;

    protected void setDevice(DeviceDescriptor device){
        this.device=device;
    }

    public void send(String message) throws IOException {
        device.write(message);
    }
    public abstract void execute() throws IOException;
}
