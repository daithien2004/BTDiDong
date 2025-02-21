package com.example.demoluyentap;

import android.os.Bundle;
import android.widget.CompoundButton;
import android.widget.Switch;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.constraintlayout.widget.ConstraintLayout;

import java.util.Random;

public class MainActivity extends AppCompatActivity {
    private int[] backgroundImages = {
            R.drawable.bg1,
            R.drawable.bg2,
            R.drawable.bg3,
    };
    private ConstraintLayout constraintLayout;
    private Switch sw;
    private Random ran = new Random();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_main);

        constraintLayout = findViewById(R.id.main);
        sw = findViewById(R.id.switch1);

        changeBackground();

        sw.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                changeBackground();
            }
        });
    }

    private void changeBackground() {
        int randomImage = backgroundImages[ran.nextInt(backgroundImages.length)];
        constraintLayout.setBackgroundResource(randomImage);
    }
}
