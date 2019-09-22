package bluetooth.app.internal.legacy;

import android.widget.Button;

import bluetooth.app.Command;
import bluetooth.app.CommandButtonWidget;
import bluetooth.app.DeviceManager;

/**
 * Widget for Green LED command
 */
public class LEDGreenWidget extends CommandButtonWidget {
    /**
     * Constructor
     *
     * @param manager - the device manager
     * @param button  - the button
     */
    public LEDGreenWidget(DeviceManager manager, Button button) {
        super(manager, button);
    }

    @Override
    protected Command getCommand() {
        return new LEDGreenCommand();
    }
}
