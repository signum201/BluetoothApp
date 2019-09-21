package bluetooth.app;


import android.bluetooth.BluetoothAdapter;
import android.bluetooth.BluetoothDevice;

import java.io.IOException;
import java.util.HashSet;
import java.util.Set;
import java.util.UUID;

public class DeviceManager {
    Set<DeviceDescriptor> devices = new HashSet<>();
    private DeviceDescriptor activeDevice;
    private BluetoothAdapter adapter;


    public DeviceManager(BluetoothAdapter adapter) {
        this.adapter = adapter;
        // init bonded devices
        addPairedDevices(adapter.getBondedDevices());
    }

    public boolean isBluetoothEnabled(){
        return adapter.isEnabled();
    }

    public void startDiscovery(){
        if (adapter.isDiscovering()){
            cancelDiscovery();
        }
        adapter.startDiscovery();
    }

    public void executeCommand(Command command) throws IOException {
        if (hasActiveDevice()) {
            command.setDevice(activeDevice);
            command.execute();
        }
    }

    private void addPairedDevices(Set<BluetoothDevice> pairedDevices) {
        for (BluetoothDevice btDevice : pairedDevices) {
            addPairedDevice(btDevice);
        }
    }


    public BluetoothDevice getPairedByName(String name) {
        for (DeviceDescriptor descriptor : devices) {
            if (name.equals(descriptor.getName())) {
                return descriptor._device;
            }
        }
        return null;
    }

    private void addPairedDevice(BluetoothDevice btDevice) {
        devices.add(new DeviceDescriptor(btDevice));

    }

    public void setActive(BluetoothDevice btDevice) {
        this.activeDevice = new DeviceDescriptor(btDevice);
        devices.add(activeDevice);

    }

    public UUID getUID() {
        if (hasActiveDevice()) {
            return activeDevice._device.getUuids()[0].getUuid();
        }
        return null;
    }


    public boolean isPaired(final BluetoothDevice btDevice) {
        for (DeviceDescriptor device : devices) {
            if (device.getAddress().equals(btDevice.getAddress())) {
                return true;
            }
        }
        return false;
    }

    public boolean hasActiveDevice() {
        return activeDevice != null;
    }

    public void cancelDiscovery() {
        adapter.cancelDiscovery();
    }

    public String connect() throws IOException {
        if (hasActiveDevice()) {
            activeDevice.connect();
            return "Connected to: "+activeDevice.getName();
        }
        return null;
    }

    public void disconnect() throws IOException {
        cancelDiscovery();
        if (hasActiveDevice()) {
            activeDevice.disconnect();
            activeDevice = null;
        }
    }

    public String read() throws IOException {
        if (hasActiveDevice()) {
            return activeDevice.read();
        }
        return new String("Not active device found!");
    }
}
