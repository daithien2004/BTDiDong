package daithien.com.example.baitap01;

import android.os.Bundle;
import androidx.appcompat.app.AppCompatActivity;
import android.util.Log;
import java.util.ArrayList;
import java.util.Scanner;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;
import android.view.Window;
import android.view.WindowManager;
import java.util.Random;

public class MainActivity extends AppCompatActivity {
    private static final String TAG = "ALD";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        requestWindowFeature(Window.FEATURE_NO_TITLE);//will hide the title not the title bar
        this.getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN,
                WindowManager.LayoutParams.FLAG_FULLSCREEN);//int flag, int mask

        setContentView(R.layout.activity_main);

        InChanLe();

        // Ánh xạ các View
        EditText etInput = findViewById(R.id.etInput);
        Button btnXuLy = findViewById(R.id.btnXuLy);
        TextView txtOutput = findViewById(R.id.txtOutput);

        // Xử lý khi nhấn nút
        btnXuLy.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String input = etInput.getText().toString();

                if (input.isEmpty()) {
                    Toast.makeText(MainActivity.this, "Vui lòng nhập chuỗi", Toast.LENGTH_SHORT).show();
                    return;
                }

                String[] words = input.split(" ");

                StringBuilder reversed = new StringBuilder();
                for (int i = words.length - 1; i >= 0; i--) {
                    reversed.append(words[i]).append(" ");
                }

                String result = reversed.toString().trim();

                String upperReversed = result.toUpperCase();  // Chuyển result (String) thành chữ in hoa

                txtOutput.setText(upperReversed);

                Toast.makeText(MainActivity.this, "Chuỗi đảo ngược và in hoa: " + upperReversed, Toast.LENGTH_SHORT).show();
            }
        });

    }

    public void InChanLe () {
        ArrayList<Integer> numbers = TaoSoNgauNhien(10, 1, 100);

        ArrayList<Integer> soChan = new ArrayList<>();
        ArrayList<Integer> soLe = new ArrayList<>();

        for (int number : numbers) {
            if (number % 2 == 0) {
                soChan.add(number);
            } else {
                soLe.add(number);
            }
        }
        Log.d(TAG, "Danh sách số chẵn: " + soChan);
        Log.d(TAG, "Danh sách số lẻ: " + soLe);
    }
    private ArrayList<Integer> TaoSoNgauNhien(int size, int min, int max) {
        ArrayList<Integer> numbers = new ArrayList<>();
        Random random = new Random();
        for (int i = 0; i < size; i++) {
            int soNgauNhien = random.nextInt((max - min) + 1) + min;
            numbers.add(soNgauNhien);
        }
        return numbers;
    }
}