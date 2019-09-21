package bluetooth.app;

import android.view.View;
import android.widget.Button;

import java.io.IOException;

public abstract class CommandButtonWidget {

    private Button button;
    private Command command;
    private DeviceManager deviceManager;

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

    public void enable() {
        button.setEnabled(true);
    }

    public void disable() {
        button.setEnabled(false);
    }

    protected abstract Command getCommand();


}
