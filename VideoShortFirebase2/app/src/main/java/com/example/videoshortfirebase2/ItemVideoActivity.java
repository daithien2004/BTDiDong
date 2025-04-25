package com.example.videoshortfirebase2;

import android.os.Bundle;
import android.view.Window;
import android.view.WindowManager;
import android.webkit.WebChromeClient;
import android.webkit.WebSettings;
import android.webkit.WebView;

import androidx.appcompat.app.AppCompatActivity;

import java.util.ArrayList;

public class ItemVideoActivity extends AppCompatActivity {
    WebView webView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        // Fullscreen
        requestWindowFeature(Window.FEATURE_NO_TITLE);
        getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN, WindowManager.LayoutParams.FLAG_FULLSCREEN);

        setContentView(R.layout.item_video);
        webView = findViewById(R.id.webview);

        String videoUrl = getIntent().getStringExtra("video_url"); // nhận từ intent
        String videoId = extractYoutubeId(videoUrl);
        String embedUrl = "https://www.youtube.com/embed/" + videoId + "?autoplay=1&playsinline=1&vq=small";

        WebSettings webSettings = webView.getSettings();
        webSettings.setJavaScriptEnabled(true);
        webSettings.setLoadWithOverviewMode(true);
        webSettings.setUseWideViewPort(true);
        webSettings.setMediaPlaybackRequiresUserGesture(false);

        webView.setWebChromeClient(new WebChromeClient());
        webView.loadUrl(embedUrl);
    }

    // Hàm lấy video ID từ URL YouTube
    private String extractYoutubeId(String url) {
        String pattern = "(?<=watch\\?v=|youtu.be/|embed/)[^#\\&\\?]*";
        java.util.regex.Pattern compiledPattern = java.util.regex.Pattern.compile(pattern);
        java.util.regex.Matcher matcher = compiledPattern.matcher(url);
        if (matcher.find()) {
            return matcher.group();
        }
        return null;
    }
}