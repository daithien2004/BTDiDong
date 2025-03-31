package com.example.bt08slideimageswithviewflipper;

import android.os.Bundle;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.ImageView;
import android.widget.ViewFlipper;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;

import com.bumptech.glide.Glide;

import java.util.ArrayList;
import java.util.List;


public class ViewFlipperActivity extends AppCompatActivity {
    private ViewFlipper viewFlipperMain;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_viewflipper);
        viewFlipperMain = findViewById(R.id.viewFlipperMain);
        ActionViewFlipperMain();
    }
    private void ActionViewFlipperMain() {
//        List<String> arrayListFlipper = new ArrayList<>();
//        arrayListFlipper.add("http://app.iostar.vn:8081/appfoods/flipper/guangcao.png");
//        arrayListFlipper.add("http://app.iostar.vn:8081/appfoods/flipper/coffee.jpg");
//        arrayListFlipper.add("http://app.iostar.vn:8081/appfoods/flipper/companypizza.jpg");
//        arrayListFlipper.add("http://app.iostar.vn:8081/appfoods/flipper/themonjon.jpeg");
//
//        for (int i = 0; i < arrayListFlipper.size(); i++) {
//            ImageView imageView = new ImageView(getApplicationContext());
//            Glide.with(getApplicationContext()).load(arrayListFlipper.get(i)).into(imageView);
//            imageView.setScaleType(ImageView.ScaleType.FIT_XY);
//            viewFlipperMain.addView(imageView);
//        }
        List<Integer> arrayListFlipper = new ArrayList<>();
        arrayListFlipper.add(R.drawable.image);
        arrayListFlipper.add(R.drawable.image);
        arrayListFlipper.add(R.drawable.image);
        arrayListFlipper.add(R.drawable.image);

        for (int imageRes : arrayListFlipper) {
            ImageView imageView = new ImageView(getApplicationContext());
            imageView.setImageResource(imageRes);
            imageView.setScaleType(ImageView.ScaleType.FIT_XY);
            viewFlipperMain.addView(imageView);
        }


        viewFlipperMain.setFlipInterval(3000);
        viewFlipperMain.setAutoStart(true);

        // Thiết lập animation cho Flipper
        Animation in = AnimationUtils.loadAnimation(getApplicationContext(), R.anim.slide_in_right);
        Animation out = AnimationUtils.loadAnimation(getApplicationContext(), R.anim.slide_out_right);
        viewFlipperMain.setInAnimation(in);
        viewFlipperMain.setOutAnimation(out);
    }
}