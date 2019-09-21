package bluetooth.app;

import android.view.View;
import android.widget.Button;

import java.io.IOException;

/**
 * Button widget with command capabilities.
 */
public abstract class CommandButtonWidget {

    private Button button;
    private Command command;
    private DeviceManager deviceManager;

    /**
     * @param manager - the device manager
     * @param button  - the button
     */
    public CommandButtonWidget(DeviceManager manager, Button button) {
        this.button = button;
        this.deviceManager = manager;
        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                try {
                    deviceManager.executeCommand(getCommand());
                } catch (IOException e) {
                    // TODO
                }
            }
        });
    }

    /**
     * Enabled this widget
     */
    public void enable() {
        button.setEnabled(true);
    }

    /**
     * Disables this widget
     */
    public void disable() {
        button.setEnabled(false);
    }

    /**
     * Retrieves the command for this widget
     *
     * @return - the command
     */
    protected abstract Command getCommand();


}
