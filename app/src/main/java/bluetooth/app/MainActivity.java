package bluetooth.app;

import android.Manifest;
import android.annotation.TargetApi;
import android.bluetooth.BluetoothAdapter;
import android.bluetooth.BluetoothDevice;
import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.IntentFilter;
import android.os.Build;
import android.os.Bundle;
import android.util.Log;
import android.widget.Button;
import android.widget.CompoundButton;
import android.widget.Toast;
import android.widget.ToggleButton;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import bluetooth.app.internal.LEDBlueWidget;
import bluetooth.app.internal.LEDGreenWidget;
import bluetooth.app.internal.LEDOffWidget;
import bluetooth.app.internal.LEDRedWidget;


public class MainActivity extends AppCompatActivity {
    private static final String TAG = "MainActivity";
    private ToggleButton connection;

    private static final Integer BT_CONNECT_REQ = 100;
    private static final String DEVICE_NAME = "HC05";//"DESKTOP-SN993KP";
    private DeviceManager deviceManager;
    private List<CommandButtonWidget> commandWidgets = new ArrayList<>();


    // Create a BroadcastReceiver for ACTION_FOUND.
    private final BroadcastReceiver deviceFoundReceiver = new BroadcastReceiver() {
        public void onReceive(Context context, Intent intent) {
            String action = intent.getAction();
            if (BluetoothDevice.ACTION_FOUND.equals(action)) {
                if (deviceManager.hasActiveDevice()) {
                    return;
                }
                // Discovery has found a device. Get the BluetoothDevice
                // object and its info from the Intent.
                BluetoothDevice device = intent.getParcelableExtra(BluetoothDevice.EXTRA_DEVICE);


                if (device.getName().equals(DEVICE_NAME)) {
                    deviceManager.cancelDiscovery();
                    device.createBond();

                }

            }
        }
    };

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        setContentView(R.layout.activity_main);
        BluetoothAdapter adapter = BluetoothAdapter.getDefaultAdapter();
        if (adapter == null) {
            new AlertDialog.Builder(this)
                    .setTitle("Not compatible")
                    .setMessage("Your phone does not support Bluetooth")
                    .setPositiveButton("Exit", new DialogInterface.OnClickListener() {
                        public void onClick(DialogInterface dialog, int which) {
                            System.exit(0);
                        }
                    })
                    .setIcon(android.R.drawable.ic_dialog_alert)
                    .show();
        }
        deviceManager = new DeviceManager(adapter);
        // get UI elements
        connection = (ToggleButton) findViewById(R.id.buttonConnection);

        // Register for broadcasts when a device is discovered.
        IntentFilter deviceFoundFilter = new IntentFilter(BluetoothDevice.ACTION_FOUND);
        registerReceiver(deviceFoundReceiver, deviceFoundFilter);
        // Register for bonding state
        IntentFilter bondingFilter = new IntentFilter(BluetoothDevice.ACTION_BOND_STATE_CHANGED);
        registerReceiver(bondStateReceiver, bondingFilter);
        // checks permissions
        checkBTPermissions();
        // sets-up the UI
        setupUI();
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        try {
            deviceManager.disconnect();
        } catch (IOException e) {
            e.printStackTrace();
        }
        // unregister receivers
        unregisterReceiver(deviceFoundReceiver);
        unregisterReceiver(bondStateReceiver);
    }


    private void setupUI() {
        connection.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                if (isChecked) {
                    pairOrDiscover();
                    updateWidgets(true);

                } else {
                    updateWidgets(false);
                    try {
                        deviceManager.disconnect();
                    } catch (IOException e) {
                        Toast.makeText(MainActivity.this, e.getMessage(), Toast.LENGTH_LONG).show();
                    }

                }
            }
        });
        commandWidgets.add(new LEDOffWidget(deviceManager, (Button) findViewById(R.id.btnOFF)));
        commandWidgets.add(new LEDRedWidget(deviceManager, (Button) findViewById(R.id.btnRED)));
        commandWidgets.add(new LEDGreenWidget(deviceManager, (Button) findViewById(R.id.btnGREEN)));
        commandWidgets.add(new LEDBlueWidget(deviceManager, (Button) findViewById(R.id.btnBLUE)));
        // all buttons are disabled at start
        updateWidgets(false);
    }

    /**
     * Updates contents of command widgets
     *
     * @param enabledState - the enabled state
     */
    private void updateWidgets(boolean enabledState) {
        for (CommandButtonWidget widget : commandWidgets) {
            if (enabledState) {
                widget.enable();
            } else {
                widget.disable();
            }
        }
    }

    private void pairOrDiscover() {

        // if not enabled, enable it
        if (!deviceManager.isBluetoothEnabled()) {
            Intent enableBtIntent = new Intent(BluetoothAdapter.ACTION_REQUEST_ENABLE);
            startActivityForResult(enableBtIntent, BT_CONNECT_REQ);
        }

        BluetoothDevice alreadyPaired = deviceManager.getPairedByName(DEVICE_NAME);
        if (alreadyPaired != null) {
            // make device active and try to send some data to it
            deviceManager.setActive(alreadyPaired);
            String message;
            try {
                message = deviceManager.connect();
            } catch (IOException e) {
                message = e.getMessage();

            }
            Toast.makeText(this, message, Toast.LENGTH_LONG).show();
        } else {

            deviceManager.startDiscovery();
        }

    }


    private BroadcastReceiver bondStateReceiver = new BroadcastReceiver() {
        @Override
        public void onReceive(Context context, Intent intent) {
            if (intent.getAction() == BluetoothDevice.ACTION_BOND_STATE_CHANGED) {
                BluetoothDevice mDevice = intent.getParcelableExtra(BluetoothDevice.EXTRA_DEVICE);
                //3 cases:
                //case1: bonded already
                if (mDevice.getBondState() == BluetoothDevice.BOND_BONDED) {
                    Log.d(TAG, "BroadcastReceiver: BOND_BONDED.");
                    deviceManager.setActive(mDevice);
                    String message;
                    try {
                        message = deviceManager.connect();
                    } catch (IOException e) {
                        message = e.getMessage();

                    }
                    Toast.makeText(MainActivity.this, message, Toast.LENGTH_LONG).show();
                }
                //case2: creating a bone
                if (mDevice.getBondState() == BluetoothDevice.BOND_BONDING) {
                    Log.d(TAG, "BroadcastReceiver: BOND_BONDING.");
                    deviceManager.setActive(mDevice);

                }
                //case3: breaking a bond
                if (mDevice.getBondState() == BluetoothDevice.BOND_NONE) {
                    Log.d(TAG, "BroadcastReceiver: BOND_NONE.");
                }
            }
        }
    };

    /**
     * This method is required for all devices running API23+
     * Android must programmatically check the permissions for bluetooth. Putting the proper permissions
     * in the manifest is not enough.
     * <p>
     * NOTE: This will only execute on versions > LOLLIPOP because it is not needed otherwise.
     */
    @TargetApi(Build.VERSION_CODES.M)
    private void checkBTPermissions() {
        if (Build.VERSION.SDK_INT > Build.VERSION_CODES.LOLLIPOP) {
            int permissionCheck = checkSelfPermission("Manifest.permission.ACCESS_FINE_LOCATION");
            permissionCheck += checkSelfPermission("Manifest.permission.ACCESS_COARSE_LOCATION");
            if (permissionCheck != 0) {

                this.requestPermissions(new String[]{Manifest.permission.ACCESS_FINE_LOCATION, Manifest.permission.ACCESS_COARSE_LOCATION}, 1001); //Any number
            }
        }
    }


}
