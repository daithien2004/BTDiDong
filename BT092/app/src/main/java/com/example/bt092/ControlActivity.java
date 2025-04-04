package com.example.bt092;

import android.app.ProgressDialog;
import android.bluetooth.BluetoothAdapter;
import android.bluetooth.BluetoothDevice;
import android.bluetooth.BluetoothSocket;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.os.AsyncTask;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageButton;
import android.widget.TextView;
import android.widget.Toast;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.app.ActivityCompat;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

import java.io.IOException;
import java.util.Set;
import java.util.UUID;

public class ControlActivity extends AppCompatActivity {

    ImageButton btnTb1,btnTb2,btnDis;
    TextView txt1,txtMAC;
    BluetoothAdapter myBluetooth = null;
    BluetoothSocket btSocket = null;
    private boolean isBtConnected = false;
    Set<BluetoothDevice> pairedDevices1;
    String address = null;
    private ProgressDialog progress;
    int flaglamp1;
    int flaglamp2;
    //SPP UUID. Look for it
    static final UUID myUUID = UUID.fromString("00001101-0000-1000-8000-00805F9B34FB");

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        Intent newint = getIntent();
        address = newint.getStringExtra(MainActivity.EXTRA_ADDRESS);
        setContentView(R.layout.activity_control);
        //ánh xạ
        btnTb1 = (ImageButton) findViewById(R.id.btnTb1);
        btnTb2 = (ImageButton) findViewById(R.id.btnTb2);
        txt1 = (TextView) findViewById(R.id.textV1);
        txtMAC = (TextView) findViewById(R.id.textViewMAC);
        btnDis = (ImageButton) findViewById(R.id.btnDisc);

        new ConnectBT().execute();
        btnTb1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) { thietTbil(); }
            });

        btnTb2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) { thiettbi7(); }
            });

        btnDis.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) { Disconnect(); }
            });

    }



    //viet hàm
    private void thietTbil() {
        if (btSocket != null) {
            try {
                if (this.flaglamp1 == 0) {
                    this.flaglamp1 = 1;
                    this.btnTb1.setBackgroundResource(R.drawable.me);
                    btSocket.getOutputStream().write("1".toString().getBytes());
                    txt1.setText("Thiết bị số 1 đang bật");
                    return;
                } else {
                    if (this.flaglamp1 != 1) return;
                    {
                        this.flaglamp1 = 0;
                        this.btnTb1.setBackgroundResource(R.drawable.abc);
                        btSocket.getOutputStream().write("A".toString().getBytes());
                        txt1.setText("Thiết bị số 1 đang tắt");
                        return;
                    }
                }
            } catch (IOException e) {

            }
        }
    }

    private void thiettbi7() {
        if (btSocket != null) {
            try {
                if (this.flaglamp2 == 0) {
                    this.flaglamp2 = 1;
                    this.btnTb2.setBackgroundResource(R.drawable.me);
                    btSocket.getOutputStream().write("7".toString().getBytes());
                    txt1.setText("Thiết bị số 7 đang bật");
                    return;
                } else {
                    if (this.flaglamp2 != 1) return;
                    {
                        this.flaglamp2 = 0;
                        this.btnTb2.setBackgroundResource(R.drawable.abc);
                        btSocket.getOutputStream().write("G".toString().getBytes());
                        txt1.setText("Thiết bị số 7 đang tắt");
                        return;
                    }
                }
            } catch (IOException e) {

            }
        }
    }

    private void Disconnect() {
        if (btSocket != null)
        {
            try {
                btSocket.close();
            } catch (IOException e) {

            }
        }
        finish();
    }

    private class ConnectBT extends AsyncTask<Void, Void, Void>
    {
        private boolean ConnectSuccess = true; //if it's here, it's almost connected
        @Override
        protected void onPreExecute() {
            progress = ProgressDialog.show(ControlActivity.this, "Đang kết nối...", "Xin vui lòng đợi!!!");
        }

        @Override
        protected Void doInBackground (Void... devices) //while the progress dialog is shown, the connection is done in background
        {
            try {
                if (btSocket == null || ! isBtConnected) {
                    myBluetooth = BluetoothAdapter.getDefaultAdapter();//get the mobile bluetooth device

                    BluetoothDevice dispositivo = myBluetooth.getRemoteDevice(address);
                    if (ActivityCompat.checkSelfPermission(ControlActivity.this
                            , android.Manifest.permission.BLUETOOTH_CONNECT) != PackageManager.PERMISSION_GRANTED) {
                        btSocket = dispositivo.createInsecureRfcommSocketToServiceRecord(myUUID);
                        BluetoothAdapter.getDefaultAdapter().cancelDiscovery();
                        btSocket.connect();//start connection
                    }
                }
            }
            catch (IOException e)
            {
                ConnectSuccess = false;//if the try failed, you can check the exception here
            }
            return null;
        }

        @Override
        protected void onPostExecute(Void result) //after the doInB
        {
            super.onPostExecute(result);

            if (!ConnectSuccess)
            {
                finish();
            }
            else
            {
                isBtConnected = true;
                pairedDevicesList1();
            }
            progress.dismiss();
        }
    }

    private void pairedDevicesList1() {

        if (ActivityCompat.checkSelfPermission(this
                , android.Manifest.permission.BLUETOOTH_CONNECT) != PackageManager.PERMISSION_GRANTED) {
            pairedDevices1 = myBluetooth.getBondedDevices();

            if (pairedDevices1.size() > 0) {
                for (BluetoothDevice bt : pairedDevices1) {
                    txtMAC.setText(bt.getName() + " - " + bt.getAddress()); //Get the device's name and the address
                }
            } else {
                Toast.makeText(getApplicationContext(), "Không tìm thấy thiết bị kết nối.", Toast.LENGTH_LONG).show();
            }
        }
    }
}