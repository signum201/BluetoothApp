package bluetooth.app.internal;

import android.widget.Button;

import bluetooth.app.Command;
import bluetooth.app.CommandButtonWidget;
import bluetooth.app.DeviceManager;
import bluetooth.app.internal.hc05commands.LEDOffCommand;

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
