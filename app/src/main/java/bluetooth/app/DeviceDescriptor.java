package bluetooth.app;


import android.bluetooth.BluetoothDevice;
import android.bluetooth.BluetoothSocket;

import org.jetbrains.annotations.NotNull;

import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.util.Objects;

public class DeviceDescriptor {
    private String name;
    private String address;
    BluetoothDevice _device;
    private BluetoothSocket socket;


    public DeviceDescriptor(@NotNull BluetoothDevice btDevice) {
        this(btDevice.getName(), btDevice.getAddress());
        this._device = btDevice;
    }


    public DeviceDescriptor(String name, String address) {
        this.name = name;
        this.address = address;
    }

    public String getName() {
        return name;
    }

    public String getAddress() {
        return address;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        DeviceDescriptor that = (DeviceDescriptor) o;
        return Objects.equals(name, that.name) &&
                address.equals(that.address);
    }

    @Override
    public int hashCode() {
        return Objects.hash(name, address);
    }

    public void connect() throws IOException {
        socket = _device.createInsecureRfcommSocketToServiceRecord(_device.getUuids()[0].getUuid());
        if (socket.isConnected()) {
            socket.close();
        }
        // connect
        socket.connect();
    }

    public void disconnect() throws IOException {
        if (socket == null || !socket.isConnected()) {
            throw new IOException("You must connect first.");
        }
        socket.close();
    }

    public String read() throws IOException {
        if (socket == null || !socket.isConnected()) {
            throwConnectionException();
        }
        InputStream inStream = socket.getInputStream();
        int byteSize = inStream.available();
        byte[] content = new byte[byteSize];
        String message = new String(content, "UTF-8");
        return message;

    }

    public void write(String message) throws IOException {
        if (socket == null || !socket.isConnected()) {
            throwConnectionException();
        }
        OutputStream outStream = socket.getOutputStream();
        outStream.write(message.getBytes());
    }

    private void throwConnectionException() throws IOException {
        throw new IOException("You must connect first.");
    }
}
