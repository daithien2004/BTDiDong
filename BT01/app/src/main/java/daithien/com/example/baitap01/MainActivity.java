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

        // Gọi phương thức tạo mảng ngẫu nhiên
        ArrayList<Integer> numbers = generateRandomNumbers(10, 1, 100);

        // Phân loại số chẵn và số lẻ
        ArrayList<Integer> evenNumbers = new ArrayList<>();
        ArrayList<Integer> oddNumbers = new ArrayList<>();

        for (int number : numbers) {
            if (number % 2 == 0) {
                evenNumbers.add(number);
            } else {
                oddNumbers.add(number);
            }
        }

        // In ra Log.d
        Log.d(TAG, "Số chẵn: " + evenNumbers);
        Log.d(TAG, "Số lẻ: " + oddNumbers);

        // Ánh xạ các View
        EditText editTextInput = findViewById(R.id.etInput);
        Button buttonProcess = findViewById(R.id.btnXuLy);
        TextView textViewOutput = findViewById(R.id.txtOutput);

        // Xử lý khi nhấn nút
        buttonProcess.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // Lấy chuỗi từ EditText
                String input = editTextInput.getText().toString();

                // Kiểm tra nếu chuỗi rỗng
                if (input.isEmpty()) {
                    Toast.makeText(MainActivity.this, "Vui lòng nhập chuỗi", Toast.LENGTH_SHORT).show();
                    return;
                }

                // Tách chuỗi thành các từ
                String[] words = input.split(" ");

                // Đảo ngược thứ tự các từ
                StringBuilder reversed = new StringBuilder();
                for (int i = words.length - 1; i >= 0; i--) {
                    reversed.append(words[i]).append(" ");
                }

                // Loại bỏ khoảng trắng thừa ở cuối
                String result = reversed.toString().trim();

                // Chuyển chuỗi đảo ngược thành chữ in hoa
                String upperReversed = result.toUpperCase();  // Chuyển result (String) thành chữ in hoa

                // Hiển thị kết quả lên TextView
                textViewOutput.setText(upperReversed);

                // Hiển thị thông báo Toast
                Toast.makeText(MainActivity.this, "Chuỗi đảo ngược và in hoa: " + upperReversed, Toast.LENGTH_SHORT).show();
            }
        });

    }

    // Hàm tạo mảng số ngẫu nhiên
    private ArrayList<Integer> generateRandomNumbers(int size, int min, int max) {
        ArrayList<Integer> numbers = new ArrayList<>();
        Random random = new Random();

        for (int i = 0; i < size; i++) {
            int randomNumber = random.nextInt((max - min) + 1) + min; // Sinh số ngẫu nhiên trong phạm vi từ min đến max
            numbers.add(randomNumber);
        }

        return numbers;
    }
}