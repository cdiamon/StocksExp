package com.padmitriy.resultant.view.activity;


import android.os.Bundle;
import android.os.Handler;
import android.support.v7.app.AppCompatActivity;

import com.padmitriy.resultant.R;

public class WelcomeActivity extends AppCompatActivity {

    private Handler handler;

    @Override
    protected void onResume() {
        super.onResume();
        handler.postDelayed(splashRunnable, 1500);
    }

    private Runnable splashRunnable = () -> {
        MainActivity.launch(WelcomeActivity.this);
        finish();
    };

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_welcome);
        getWindow().setStatusBarColor(getResources().getColor(android.R.color.transparent));
        handler = new Handler();
    }

    @Override
    protected void onPause() {
        handler.removeCallbacks(splashRunnable);
        super.onPause();
    }
}
