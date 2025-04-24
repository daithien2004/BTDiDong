package com.example.bt092;

import android.bluetooth.BluetoothAdapter;
import android.bluetooth.BluetoothDevice;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.os.Build;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.app.ActivityCompat;
import androidx.core.content.ContextCompat;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

import java.util.ArrayList;
import java.util.Set;

public class MainActivity extends AppCompatActivity {

    Button btnPaired;
    ListView listDanhSach;
    public static int REQUEST_BLUETOOTH = 1;
    //Bluetooth
    private BluetoothAdapter myBluetooth = null;
    private Set<BluetoothDevice> pairedDevices;
    public static String EXTRA_ADDRESS = "device_address";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        btnPaired = (Button) findViewById(R.id.btnTimthietbi);
        listDanhSach=(ListView) findViewById(R.id.listTb);

        if (android.os.Build.VERSION.SDK_INT >= android.os.Build.VERSION_CODES.S) {
            if (ActivityCompat.checkSelfPermission(this, android.Manifest.permission.BLUETOOTH_CONNECT) != PackageManager.PERMISSION_GRANTED) {
                ActivityCompat.requestPermissions(this, new String[]{android.Manifest.permission.BLUETOOTH_CONNECT}, 100);
                return;
            }
        }

        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.S) {
            if (ContextCompat.checkSelfPermission(this, android.Manifest.permission.BLUETOOTH_CONNECT)
                    != PackageManager.PERMISSION_GRANTED) {
                ActivityCompat.requestPermissions(this,
                        new String[]{android.Manifest.permission.BLUETOOTH_CONNECT},
                        1001);
            }
        }

        myBluetooth = BluetoothAdapter.getDefaultAdapter();
        if (myBluetooth == null) {
            Toast.makeText(getApplicationContext(), "Thiết bị Bluetooth chưa bật", Toast.LENGTH_LONG).show();
            finish();
        } else if (!myBluetooth.isEnabled()){
            Intent turnBTon = new Intent(BluetoothAdapter.ACTION_REQUEST_ENABLE);
            if (ActivityCompat.checkSelfPermission(this,
            android.Manifest.permission.BLUETOOTH_CONNECT) != PackageManager.PERMISSION_GRANTED) {
                Toast.makeText(getApplicationContext(), "Thiết bị Bluetooth chưa bật", Toast.LENGTH_LONG).show();
            }
            Toast.makeText(getApplicationContext(), "Thiết bị Bluetooth đã bật", Toast.LENGTH_LONG).show();

            startActivityForResult(turnBTon, REQUEST_BLUETOOTH);
        }

        btnPaired.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                    pairedDevicesList();
                }
            });
        }

    //viet ham
    private void pairedDevicesList() {
        if (ActivityCompat.checkSelfPermission(this, android.Manifest.permission.BLUETOOTH_CONNECT) == PackageManager.PERMISSION_GRANTED) {
            pairedDevices = myBluetooth.getBondedDevices();
            ArrayList list = new ArrayList();

            if (pairedDevices.size() > 0) {
                for (BluetoothDevice bt : pairedDevices) {
                    if (ActivityCompat.checkSelfPermission(this, android.Manifest.permission.BLUETOOTH_CONNECT) == PackageManager.PERMISSION_GRANTED) {
                        Toast.makeText(getApplicationContext(), "Danh sách thiết bị Bluetooth đã bật", Toast.LENGTH_LONG).show();
                        list.add(bt.getName() + "\n" + bt.getAddress()); //Get the device's name and the address
                    }
                }
            } else {
                Toast.makeText(getApplicationContext(), "Không tìm thấy thiết bị kết nối.", Toast.LENGTH_LONG).show();
            }

            final ArrayAdapter adapter = new ArrayAdapter(this, android.R.layout.simple_list_item_1, list);
            listDanhSach.setAdapter(adapter);
            listDanhSach.setOnItemClickListener(myListClickListener);

        }
    }

    private AdapterView.OnItemClickListener myListClickListener = new AdapterView.OnItemClickListener()
    {
        public void onItemClick (AdapterView<?> av, View v, int arg2, long arg3)
        {

            String info = ((TextView) v).getText().toString();
            String address = info.substring(info.length() - 17);
            Intent i= new Intent(MainActivity.this, ControlActivity.class);
            i.putExtra(EXTRA_ADDRESS, address); //this will be received at BlueControl (class) Activity
            startActivity(i);
        }
    };

    @Override
    public void onRequestPermissionsResult(int requestCode, String[] permissions, int[] grantResults) {
        super.onRequestPermissionsResult(requestCode, permissions, grantResults);
        if (requestCode == 100) {
            if (grantResults.length > 0 && grantResults[0] == PackageManager.PERMISSION_GRANTED) {
                // Quyền đã được cấp, tiếp tục gọi pairedDevicesList nếu cần
                pairedDevicesList();
            } else {
                Toast.makeText(this, "Cần cấp quyền Bluetooth để tiếp tục", Toast.LENGTH_SHORT).show();
            }
        }
    }
}