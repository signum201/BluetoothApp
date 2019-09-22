package bluetooth.app.internal.legacy;

import android.widget.Button;

import bluetooth.app.Command;
import bluetooth.app.CommandButtonWidget;
import bluetooth.app.DeviceManager;

/**
 * Widget for LED off command
 */
public class LEDOffWidget extends CommandButtonWidget {
    /**
     * Constructor
     *
     * @param manager - the device manager
     * @param button  - the button
     */
    public LEDOffWidget(DeviceManager manager, Button button) {
        super(manager, button);
    }

    @Override
    protected Command getCommand() {
        return new LEDOffCommand();
    }
}
