package bluetooth.app.internal;

import android.widget.Button;

import bluetooth.app.Command;
import bluetooth.app.CommandButtonWidget;
import bluetooth.app.DeviceManager;
import bluetooth.app.internal.hc05commands.LEDBlueCommand;

public class LEDBlueWidget extends CommandButtonWidget {
    public LEDBlueWidget(DeviceManager manager, Button button) {
        super(manager, button);
    }

    @Override
    protected Command getCommand() {
        return new LEDBlueCommand();
    }
}
