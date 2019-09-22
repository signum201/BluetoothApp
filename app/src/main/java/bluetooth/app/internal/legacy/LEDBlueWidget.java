package bluetooth.app.internal.legacy;

import android.widget.Button;

import bluetooth.app.Command;
import bluetooth.app.CommandButtonWidget;
import bluetooth.app.DeviceManager;

/**
 * Widget for Blue LED command
 */
public class LEDBlueWidget extends CommandButtonWidget {
    /**
     * Constructor
     *
     * @param manager - the device manager
     * @param button  - the button
     */
    public LEDBlueWidget(DeviceManager manager, Button button) {
        super(manager, button);
    }

    @Override
    protected Command getCommand() {
        return new LEDBlueCommand();
    }
}
