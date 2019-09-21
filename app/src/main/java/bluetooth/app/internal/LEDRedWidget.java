package bluetooth.app.internal;

import android.widget.Button;

import bluetooth.app.Command;
import bluetooth.app.CommandButtonWidget;
import bluetooth.app.DeviceManager;
import bluetooth.app.internal.hc05commands.LEDRedCommand;

/**
 * Widget for Red LED command
 */

public class LEDRedWidget extends CommandButtonWidget {
    /**
     * Constructor
     *
     * @param manager - the device manager
     * @param button  - the button
     */
    public LEDRedWidget(DeviceManager manager, Button button) {
        super(manager, button);
    }

    @Override
    protected Command getCommand() {
        return new LEDRedCommand();
    }
}
