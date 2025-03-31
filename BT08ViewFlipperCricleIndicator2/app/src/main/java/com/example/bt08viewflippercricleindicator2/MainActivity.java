package com.example.bt08viewflippercricleindicator2;

import android.os.Bundle;
import android.os.Handler;
import android.util.Log;
import android.widget.Toast;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.viewpager2.widget.ViewPager2;

import com.example.bt08viewflippercricleindicator2.model.ImagesSlider;
import com.example.bt08viewflippercricleindicator2.model.MessageModel;
import com.example.bt08viewflippercricleindicator2.retrofit.ApiService;
import com.example.bt08viewflippercricleindicator2.retrofit.RetrofitClient;

import java.util.ArrayList;
import java.util.List;

import me.relex.circleindicator.CircleIndicator3;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class MainActivity extends AppCompatActivity {
    private ViewPager2 viewPager2;
    private CircleIndicator3 circleIndicator3;
    private List<ImagesSlider> imagesList1;

    private ImagesViewPager2Adapter adapter;

    //autorun
    private Handler handler = new Handler();
    private Runnable runnable = new Runnable() {
        @Override
        public void run() {
            if(viewPager2.getCurrentItem() == imagesList1.size() - 1) {
                viewPager2.setCurrentItem(0);
            } else {
                viewPager2.setCurrentItem(viewPager2.getCurrentItem() + 1);
            }
        }
    };

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_main);

        viewPager2 = findViewById(R.id.viewpager2);
        circleIndicator3 = findViewById(R.id.circle_indicator3);
        imagesList1 = new ArrayList<>();

        adapter = new ImagesViewPager2Adapter(imagesList1);
        viewPager2.setAdapter(adapter);
        circleIndicator3.setViewPager(viewPager2);

        loadImageSlider();

        //lắng nghe viewpager chuyển trang
        viewPager2.registerOnPageChangeCallback(new ViewPager2.OnPageChangeCallback() {
            @Override
            public void onPageSelected(int position) {
                super.onPageSelected(position);
                handler.removeCallbacks(runnable);
                handler.postDelayed(runnable, 3000);
            }
        });

        //viewpager2 transformers
        // viewPager2.setPageTransformer(new ZoomOutPageTransformer());
        viewPager2.setPageTransformer(new DepthPageTransformer());
    }

    private void loadImageSlider() {
        ApiService apiService = RetrofitClient.getApiService();
        Call<MessageModel> call = apiService.LoadImageSlider(1);

        call.enqueue(new Callback<MessageModel>() {
            @Override
            public void onResponse(Call<MessageModel> call, Response<MessageModel> response) {
                if (response.isSuccessful() && response.body() != null && response.body().isSuccess()) {
                    imagesList1.clear();
                    imagesList1.addAll(response.body().getResult());
                    adapter.notifyDataSetChanged();
                    circleIndicator3.setViewPager(viewPager2);
                } else {
                    Toast.makeText(MainActivity.this, "Lỗi tải ảnh", Toast.LENGTH_SHORT).show();
                }
            }

            @Override
            public void onFailure(Call<MessageModel> call, Throwable t) {
                Log.e("MainActivity", "Lỗi kết nối API", t);
                Toast.makeText(MainActivity.this, "Không thể tải ảnh", Toast.LENGTH_SHORT).show();
            }
        });
    }
}