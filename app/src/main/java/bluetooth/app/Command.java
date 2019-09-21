package bluetooth.app;

import java.io.IOException;

/**
 * Generic command for sending data to device
 */
public abstract class Command {
    // the device descriptor on which the command will run
    protected DeviceDescriptor device;

    /**
     * Sets the device descriptor on which the command will run
     *
     * @param device - the device
     */
    protected void setDevice(DeviceDescriptor device) {
        this.device = device;
    }

    /**
     * Sends a message to the device
     *
     * @param message - the message
     * @throws IOException exception if there are connection issues
     */
    public void send(String message) throws IOException {
        device.write(message);
    }

    /**
     * Executes the command
     *
     * @throws IOException if there are connection issues
     */
    public abstract void execute() throws IOException;
}
